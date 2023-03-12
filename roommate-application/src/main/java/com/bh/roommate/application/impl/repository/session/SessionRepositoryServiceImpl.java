package com.bh.roommate.application.impl.repository.session;

import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.session.SessionRepositoryService;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SessionRepositoryServiceImpl implements SessionRepositoryService {

    private static final Map<String, String> SESSIONS = new HashMap<>();

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ERROR}
     */
    @Override
    public RepositoryResponse<String> create(@NonNull String username) {
        try {
            final String sessionId = generateSessionId();
            SESSIONS.put(sessionId, username);

            return new RepositoryResponse<>(ProcessStatus.SUCCESS, sessionId);

        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#SESSION_IS_NOT_EXIST}
     */
    @Override
    public RepositoryResponse<String> findUsernameBySessionId(@NonNull String sessionId) {

        try {
            String username = SESSIONS.get(sessionId);

            if (username == null) {
                log.warn(String.format("Сессия %s не найдена в хранилище", sessionId));

                return new RepositoryResponse<>(ProcessStatus.SESSION_IS_NOT_EXIST, null);
            }

            return new RepositoryResponse<>(
                ProcessStatus.SUCCESS,
                username
            );
        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    private String generateSessionId() {
        return new String(
            Base64.getEncoder().encode(
                UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
            )
        );
    }
}
