package com.bh.roommate.application.impl.controller;

import com.bh.roommate.application.api.controller.FormController;
import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.dto.FormDto;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.model.mapper.FormMapper;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.form.FormService;
import com.bh.roommate.application.api.service.da.session.SessionService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("api/v1/form")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FormControllerImpl implements FormController {

    private final FormService formService;
    private final FormMapper mapper;
    private final SessionService sessionService;

    private final ResponseEntity<OperationResponse<FormDto>> badRequest = new ResponseEntity<>(
            new OperationResponse<>(new OperationStatus(
                    Severity.INCOMPLETE_REQUEST,
                    1000000L
            )), HttpStatus.BAD_REQUEST);

    // private final FormValidateService validateService; для валидации сущности анкеты понадобится скорее всего

    @Override
    @GetMapping("/{idUser}/{id}")
    public ResponseEntity<OperationResponse<FormDto>> getForm(@PathVariable String id, @PathVariable String idUser) {

        if (StringUtils.isEmpty(id))
            return badRequest;

        OperationResponse<FormDto> operationResponse = makeCall(() -> formService.getById(Long.parseLong(id), Long.parseLong(idUser)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    @Override
    @PostMapping("/{idUser}")
    public ResponseEntity<OperationResponse<FormDto>> createForm(@RequestBody FormDto formDto) {

        if (Objects.isNull(formDto))
            return badRequest;

        OperationResponse<FormDto> operationResponse = makeCall(() -> formService.create(mapper.convertDtoToModel(formDto)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    @Override
    @PutMapping("/{idUser}/{id}")
    public ResponseEntity<OperationResponse<FormDto>> updateForm(@RequestBody FormDto formDto, @PathVariable String id) {
        if (StringUtils.isEmpty(id))
            return badRequest;

        OperationResponse<FormDto> operationResponse = makeCall(() -> formService.update(mapper.convertDtoToModel(formDto)));

        return new ResponseEntity<>(operationResponse, operationResponse.getHttpStatus());
    }

    private OperationResponse<FormDto> makeCall(Supplier<OperationResponse<Form>> handlerCall) {

        //final Stopwatch watch = Stopwatch.createStarted();

        try {
            // отправка метрики todo(РЕАЛИЗОВАТЬ)
            // reportEvent(Metrics.USER_CONTROLLER_START);

            final OperationResponse<Form> response = handlerCall.get();


            // reportEvent(Metrics.USER_CONTROLLER_SUCCESS);

            return new OperationResponse<FormDto>(response.getStatus())
                    .setResponse(mapper.convertModelToDto(response.getResponse()))
                    .setHttpStatus(HttpStatus.OK);

        } catch (Exception exception) {
            log.warn("Roommate bh. Ошибка выполнения процесса.", exception);

            //reportEvent(Metrics.USER_CONTROLLER_FAIL);
            return new OperationResponse<FormDto>(new OperationStatus(
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
