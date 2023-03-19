package com.bh.roommate.application.impl.filter;

import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.session.SessionRepositoryService;
import com.bh.roommate.application.impl.context.configuration.CustomUserDetails;
import com.bh.roommate.application.impl.context.configuration.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionFilter extends OncePerRequestFilter {

    private final SessionRepositoryService sessionRepositoryService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SessionFilter(final SessionRepositoryService sessionRepositoryService,
                         final CustomUserDetailsService customUserDetailsService) {
        this.sessionRepositoryService = sessionRepositoryService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 1. Если в заголовке не найден идентификатор сессии
        if (sessionId == null || sessionId.length() == 0) {
            chain.doFilter(request, response);
            return;
        }

        // 2. Ищем логин по идентификатору сессии
        final RepositoryResponse<String> repositoryResponse = sessionRepositoryService.findUsernameBySessionId(sessionId);

        String username = repositoryResponse.getResponse();
        if (username == null) {
            chain.doFilter(request, response);
            return;
        }

        // 3. Получаем информацию о пользователе по логину
        final CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);

        // 3.1 Аккаунт не активирован
        if (!customUserDetails.isEnabled()) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            customUserDetails,
            null,
            customUserDetails.getAuthorities()
        );

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }
}
