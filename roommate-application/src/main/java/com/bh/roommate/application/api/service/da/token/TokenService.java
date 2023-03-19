package com.bh.roommate.application.api.service.da.token;

import com.bh.roommate.application.api.service.da.OperationResponse;

public interface TokenService {

    OperationResponse<String> checkToken(String token);
}
