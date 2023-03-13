package com.bh.roommate.application.impl.repository.form;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.entity.form.FormEntity;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.model.mapper.FormMapper;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.form.FormRepository;
import com.bh.roommate.application.api.repository.form.FormRepositoryService;
import com.bh.roommate.application.api.repository.user.UserRepository;
import com.bh.roommate.application.api.repository.user.UserRepositoryService;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FormRepositoryServiceImpl implements FormRepositoryService {

    private final FormRepository repository;

    private final FormMapper mapper;


    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    @Override
    public RepositoryResponse<Form> delete(@NonNull Long id) {

        try {
            final Optional<FormEntity> formEntity = repository.findById(id);

            if (formEntity.isPresent()) {

                FormEntity form = formEntity.get();

                repository.delete(form);
                return new RepositoryResponse<>(
                        ProcessStatus.SUCCESS,
                        mapper.convertEntityToModel(form)
                );

            } else {
                log.warn(String.format("Анкета с id = %s не найдена", id));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, new Form());
            }
        } catch (Exception exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_EXIST}
     */
    @Override
    public RepositoryResponse<Form> create(@NonNull Form form) {
        try {

            FormEntity savedForm = repository.save(mapper.convertModelToEntity(form));
            return new RepositoryResponse<>(ProcessStatus.SUCCESS, mapper.convertEntityToModel(savedForm));

        } catch (Exception exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    @Override
    public RepositoryResponse<Form> findById(@NonNull Long id) {

        try {
            final Optional<FormEntity> formEntity = repository.findById(id);

            if (formEntity.isPresent()) {

                FormEntity form = formEntity.get();

                return new RepositoryResponse<>(
                        ProcessStatus.SUCCESS,
                        mapper.convertEntityToModel(form)
                );

            } else {
                log.warn(String.format("Анкета с id = %s не найдена", id));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, new Form());
            }
        } catch (Exception exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    @Override
    public RepositoryResponse<Form> update(@NonNull Form form) {
        try {
            final Optional<FormEntity> formEntity = repository.findById(form.getId());

            if (formEntity.isPresent()) {
                FormEntity savedForm = repository.save(mapper.convertModelToEntity(form));
                return new RepositoryResponse<>(ProcessStatus.SUCCESS, mapper.convertEntityToModel(savedForm));
            } else {
                log.warn(String.format("Анкета с id = %s не найдена", form.getId()));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, form);
            }
        } catch (Exception exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

}
