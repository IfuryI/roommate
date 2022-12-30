package com.bh.roommate.application.api.status;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationStatus implements Serializable {

    /**
     * Строгий код ответа сервиса:
     *
     * + в случае успешного завершения процесса - {@link Severity#SUCCESS}
     * + в случае <b>любой</b> ошибки, которая влияет на проведение процесса - {@link Severity#ERROR}
     *
     * @see Severity
     */
    private @NonNull Severity severityCode;

    /**
     * Код ответа сервиса: гранулированный код, позволяющий определить более точную причину ошибки, если успех - 0
     */
    private @NonNull Long statusCode;

    /**
     * Тезисное описание кода ответа сервиса
     */
    private String statusCodeDescription = null;

}
