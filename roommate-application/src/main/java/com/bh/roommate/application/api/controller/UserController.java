package com.bh.roommate.application.api.controller;

import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.service.da.OperationResponse;
import org.springframework.http.ResponseEntity;

/**
 * тут обязательно надо заполнить джава доку по всем ручкам с полным описанием
 * что может вернуться, какие могут быть исключительные ситуации
 * ща не вижу смысла этого делать todo(вообще везде джава доку надо написать на интерфейсы)
 */
public interface UserController {

    ResponseEntity<OperationResponse<UserDto>> getUser(String id);

    ResponseEntity<OperationResponse<UserDto>> createUser(UserDto user);

    ResponseEntity<OperationResponse<UserDto>> updateUser(UserDto user, String id);

}
