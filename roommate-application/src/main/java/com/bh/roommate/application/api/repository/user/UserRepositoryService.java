package com.bh.roommate.application.api.repository.user;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;

import java.util.List;

public interface UserRepositoryService {

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    RepositoryResponse<User> delete(@NonNull Long id);

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_EXIST}
     */
    RepositoryResponse<User> create(@NonNull User user);

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    RepositoryResponse<User> findById(@NonNull Long id);

    RepositoryResponse<User> update(@NonNull User user);

    RepositoryResponse<List<User>> findAll();
}
