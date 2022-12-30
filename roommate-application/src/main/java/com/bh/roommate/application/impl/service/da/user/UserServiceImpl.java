package com.bh.roommate.application.impl.service.da.user;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.user.UserRepositoryService;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.user.UserService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.ProcessStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final UserRepositoryService repository;
    private final UserMapper mapper;

    @Override
    public OperationResponse<User> delete(@NonNull Long id) {
        RepositoryResponse<User> response = repository.delete(id);

        return new OperationResponse<User>(getStatus(response))
                .setResponse(response.getResponse());

    }

    @Override
    public OperationResponse<User> create(@NonNull User user) {
        RepositoryResponse<User> response = repository.create(user);

        return new OperationResponse<User>(getStatus(response))
                .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<User> update(@NonNull User user) {
        RepositoryResponse<User> response = repository.update(user);

        return new OperationResponse<User>(getStatus(response))
                .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<User> getById(@NonNull Long id) {
        RepositoryResponse<User> response = repository.findById(id);

        return new OperationResponse<User>(getStatus(response))
                .setResponse(response.getResponse());
    }

    private OperationStatus getStatus(RepositoryResponse<User> response) {
        OperationStatus status;
        if(response.getStatus() == ProcessStatus.SUCCESS) {

            status = new OperationStatus(Severity.SUCCESS, response.getStatus().getStatusCode())
                    .setStatusCodeDescription(response.getStatus().getCodeDescription());
            log.info(String.format("Успех: %s", response.getResponse().toString()));

        } else {
            status = new OperationStatus(Severity.ERROR, response.getStatus().getStatusCode())
                    .setStatusCodeDescription(response.getStatus().getCodeDescription());
            log.warn(String.format("Произошла проблема: %s", response.getResponse().toString()));

        }
        return status;
    }
}
