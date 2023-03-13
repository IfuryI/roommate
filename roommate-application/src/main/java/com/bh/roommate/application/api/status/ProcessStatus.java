package com.bh.roommate.application.api.status;

import lombok.Getter;

@Getter
public enum ProcessStatus {

    SUCCESS(0L, "Процесс завершился с вердиктом успех", Severity.SUCCESS),

    ENTITY_IS_EXIST(10L, "Сущность уже существует", Severity.ERROR),
    ENTITY_IS_NOT_EXIST(20L, "Сущность не существует", Severity.ERROR),

    SESSION_IS_NOT_EXIST(20L, "Сессия не существует", Severity.ERROR),

    ERROR(-1L, "Непредвиденная ошибка процесса", Severity.ERROR),

    ;

    private final Long statusCode;
    private final String codeDescription;
    private  final Severity severity;

    ProcessStatus(Long statusCode, String codeDescription, Severity severity) {
        this.statusCode = statusCode;
        this.codeDescription = codeDescription;
        this.severity = severity;
    }
}
