package com.bh.roommate.application.api.controller;

import com.bh.roommate.application.api.model.dto.FormDto;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.service.da.OperationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер, отвечающий за работу с анкетами пользователей
 */
public interface FormController {

    ResponseEntity<OperationResponse<FormDto>> getForm(String id, String idUser);

    ResponseEntity<OperationResponse<FormDto>> createForm(FormDto form);

    ResponseEntity<OperationResponse<FormDto>> updateForm(FormDto form, String id);

}
