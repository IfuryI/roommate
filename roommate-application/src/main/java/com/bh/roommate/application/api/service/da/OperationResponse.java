package com.bh.roommate.application.api.service.da;

import com.bh.roommate.application.api.status.OperationStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
// по хорошему Т тоже надо ограничить до сериалайза, но данный ответ универсальный для бл и для ответа на ручку
public class OperationResponse<T> implements Serializable {

    private @NonNull OperationStatus status;

    private T response;

    private HttpStatus httpStatus;

    // по необходимости добавлять общие параметры или наследовать данный класс когда нужно добавлять
    // не общие параметры
}
