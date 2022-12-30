package com.bh.roommate.application.api.repository;

import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepositoryResponse<T> {

    private final ProcessStatus status;

    private T response;

    // по необходимости добавлять общие параметры или наследовать данный класс когда нужно добавлять
    // не общие параметры
}
