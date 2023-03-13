package com.bh.roommate.application.api.repository.session;

import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;

public interface SessionRepositoryService {

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ERROR}
     */
    RepositoryResponse<String> create(@NonNull String username);

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#SESSION_IS_NOT_EXIST}
     */
    RepositoryResponse<String> findUsernameBySessionId(@NonNull String sessionId);

}
