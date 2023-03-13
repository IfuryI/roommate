package com.bh.roommate.application.api.repository.form;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;

import java.util.List;

public interface FormRepositoryService {

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    RepositoryResponse<Form> delete(@NonNull Long id);

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_EXIST}
     */
    RepositoryResponse<Form> create(@NonNull Form form);

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    RepositoryResponse<Form> findById(@NonNull Long id);

    RepositoryResponse<Form> update(@NonNull Form form);
}
