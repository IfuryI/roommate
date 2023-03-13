package com.bh.roommate.application.impl.service.da.session;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.session.SessionRepositoryService;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.session.SessionService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.ProcessStatus;
import com.bh.roommate.application.api.status.Severity;
import com.bh.roommate.application.impl.context.configuration.CustomUserDetails;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SessionServiceImpl implements SessionService {

    private final SessionRepositoryService repository;
    private final UserMapper userMapper;

    @Override
    public OperationResponse<String> createSession(@NonNull String username) {
        RepositoryResponse<String> response = repository.create(username);

        return new OperationResponse<String>(getStatus(response))
            .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<String> findUsernameBySessionId(@NonNull String sessionId) {
        RepositoryResponse<String> response = repository.findUsernameBySessionId(sessionId);

        return new OperationResponse<String>(getStatus(response))
            .setResponse(response.getResponse());
    }

    @Override
    public User getPersonFromSession() {
        return userMapper.convertEntityToModel(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity());
    }

    private OperationStatus getStatus(RepositoryResponse<String> response) {
        OperationStatus status;
        if(response.getStatus() == ProcessStatus.SUCCESS) {

            status = new OperationStatus(Severity.SUCCESS, response.getStatus().getStatusCode())
                    .setStatusCodeDescription(response.getStatus().getCodeDescription());
            log.info(String.format("Успех: %s", response.getResponse()));

        } else {
            status = new OperationStatus(Severity.ERROR, response.getStatus().getStatusCode())
                    .setStatusCodeDescription(response.getStatus().getCodeDescription());
            log.warn(String.format("Произошла проблема: %s", response.getResponse()));

        }
        return status;
    }
}
