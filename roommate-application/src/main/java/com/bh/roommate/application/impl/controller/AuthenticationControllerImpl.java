package com.bh.roommate.application.impl.controller;

import com.bh.roommate.application.api.controller.AuthenticationController;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.session.SessionService;
import com.bh.roommate.application.api.service.da.token.TokenService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    public SessionService sessionService;

    private final TokenService tokenService;

    private final ResponseEntity<OperationResponse<String>> badRequest = new ResponseEntity<>(
            new OperationResponse<>(new OperationStatus(
                    Severity.INCOMPLETE_REQUEST,
                    1000000L
            )), HttpStatus.BAD_REQUEST);

    @Override
    @PostMapping("/login")
    public ResponseEntity<OperationResponse<String>> login(@RequestBody UserDto userDto) {
        if (Objects.isNull(userDto))
            return badRequest;

        Authentication auth = manager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );

        OperationResponse<String> operationResponse = makeCall(() -> sessionService.createSession(userDto.getEmail()));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());

    }

    @Override
    @GetMapping("/login/{token}")
    public ResponseEntity<OperationResponse<String>> checkToken(@PathVariable String token) {
        if (Objects.isNull(token))
            return badRequest;

        OperationResponse<String> operationResponse = makeCall(() -> tokenService.checkToken(token));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    private OperationResponse<String> makeCall(Supplier<OperationResponse<String>> handlerCall) {
        try {
            final OperationResponse<String> response = handlerCall.get();

            return new OperationResponse<String>(response.getStatus())
                    .setResponse(response.getResponse())
                    .setHttpStatus(HttpStatus.OK);

        } catch (Exception exception) {
            log.warn("Roommate bh. Ошибка выполнения процесса.", exception);

            return new OperationResponse<String>(new OperationStatus(
                    Severity.ERROR,
                    1000000L)
            )
                    .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
