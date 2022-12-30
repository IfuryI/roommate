package com.bh.roommate.application.impl.controller;

import com.bh.roommate.application.api.controller.UserController;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.user.UserService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper mapper;

    private final ResponseEntity<OperationResponse<UserDto>> badRequest = new ResponseEntity<>(
            new OperationResponse<>(new OperationStatus(
                    Severity.INCOMPLETE_REQUEST,
                    1000000L
            )), HttpStatus.BAD_REQUEST);

    // private final UserValidateService validateService; для валидации сущности пользователя понадобится скорее всего

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse<UserDto>> getUser(@PathVariable String id) {

        if (StringUtils.isEmpty(id))
            return badRequest;

        OperationResponse<UserDto> operationResponse = makeCall(() -> userService.getById(Long.parseLong(id)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    @Override
    @PostMapping
    public ResponseEntity<OperationResponse<UserDto>> createUser(@RequestBody UserDto user) {

        if (Objects.isNull(user))
            return badRequest;

        OperationResponse<UserDto> operationResponse = makeCall(() -> userService.create(mapper.convertDtoToModel(user)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OperationResponse<UserDto>> updateUser(@RequestBody UserDto user, @PathVariable String id) {
        if (Objects.isNull(user) && StringUtils.isEmpty(id))
            return badRequest;

        OperationResponse<UserDto> operationResponse = makeCall(() -> userService.update(mapper.convertDtoToModel(user)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    private OperationResponse<UserDto> makeCall(Supplier<OperationResponse<User>> handlerCall) {

        //final Stopwatch watch = Stopwatch.createStarted();

        try {
            // отправка метрики todo(РЕАЛИЗОВАТЬ)
            // reportEvent(Metrics.USER_CONTROLLER_START);

            final OperationResponse<User> response = handlerCall.get();


            // reportEvent(Metrics.USER_CONTROLLER_SUCCESS);

            return new OperationResponse<UserDto>(response.getStatus())
                    .setResponse(mapper.convertModelToDto(response.getResponse()))
                    .setHttpStatus(HttpStatus.OK);

        } catch (Exception exception) {
            log.warn("Roommate bh. Ошибка выполнения процесса.", exception);

            //reportEvent(Metrics.USER_CONTROLLER_FAIL);
            return new OperationResponse<UserDto>(new OperationStatus(
                    Severity.ERROR,
                    1000000L)
            )
                    .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // finally { reportMetric(ModuleMetrics.DELIVERY_LOCATION_TIME, watch.elapsed(TimeUnit.MILLISECONDS)); }
    }

//    private void reportEvent(Metrics metric){
//        monitoringService.reportEvent(metric);
//    }
//
//    private void reportMetric(Metrics metric, double value){
//        monitoringService.reportEvent(metric, value);
//    }

}
