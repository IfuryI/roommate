package com.bh.roommate.application.api.service.da.session;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.service.da.OperationResponse;
import lombok.NonNull;

public interface SessionService {

    OperationResponse<String> createSession(@NonNull String username);

    OperationResponse<String> findUsernameBySessionId(@NonNull String sessionId);

    User getPersonFromSession();
}
