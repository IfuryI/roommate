package com.bh.roommate.application.api.controller;

import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.service.da.OperationResponse;
import org.springframework.http.ResponseEntity;

/**
 * Контроллер, отвечающий за аутентификацию и регистрацию пользователей
 */
public interface AuthenticationController {

    ResponseEntity<OperationResponse<String>> login(UserDto userDto);

    ResponseEntity<OperationResponse<String>> checkToken(String token);

}
