package com.bh.roommate.application.impl.service.da.form;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.mapper.FormMapper;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.form.FormRepositoryService;
import com.bh.roommate.application.api.repository.user.UserRepositoryService;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.form.FormService;
import com.bh.roommate.application.api.service.da.session.SessionService;
import com.bh.roommate.application.api.service.da.user.UserService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.ProcessStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FormServiceImpl implements FormService {

    private final FormRepositoryService repository;
    private final UserRepositoryService userRepositoryService;
    private final FormMapper mapper;
    private final SessionService sessionService;

    @Override
    public OperationResponse<Form> delete(@NonNull Long id) {
        RepositoryResponse<Form> response = repository.delete(id);

        return new OperationResponse<Form>(getStatus(response))
                .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<Form> create(@NonNull Form form) {
        User user = sessionService.getPersonFromSession();

        form.setOwner(user);
        RepositoryResponse<Form> response = repository.create(form);

        return new OperationResponse<Form>(getStatus(response))
                .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<Form> update(@NonNull Form form) {
        RepositoryResponse<Form> response = repository.update(form);

        return new OperationResponse<Form>(getStatus(response))
                .setResponse(response.getResponse());
    }

    @Override
    public OperationResponse<Form> getById(@NonNull Long id, @NonNull Long idUser) {
        RepositoryResponse<Form> response = repository.findById(id);

        return new OperationResponse<Form>(getStatus(response))
                .setResponse(response.getResponse());
    }

    private OperationStatus getStatus(RepositoryResponse<Form> response) {
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
