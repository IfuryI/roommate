package com.bh.roommate.application.impl.repository.user;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.user.UserRepository;
import com.bh.roommate.application.api.repository.RepositoryResponse;
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
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository repository;

    private final UserMapper mapper;


    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    @Override
    public RepositoryResponse<User> delete(@NonNull Long id) {

        try {
            final Optional<UserEntity> userEntity = repository.findById(id);

            if (userEntity.isPresent()) {

                UserEntity user = userEntity.get();

                repository.delete(user);
                return new RepositoryResponse<>(
                        ProcessStatus.SUCCESS,
                        mapper.convertEntityToModel(user)
                );

            } else {
                log.warn(String.format("Пользователь с id = %s не найден", id));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, new User());
            }
        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_EXIST}
     */
    @Override
    public RepositoryResponse<User> create(@NonNull User user) {
        try {

            final Optional<UserEntity> userEntity = repository.findByEmail(user.getEmail());

            if (userEntity.isPresent()) {
                log.warn(String.format("Пользователь с таким email = %s уже существует", user.getEmail()));
                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_EXIST, new User());
            } else {
                UserEntity savedUser = repository.save(mapper.convertModelToEntity(user));
                return new RepositoryResponse<>(ProcessStatus.SUCCESS, mapper.convertEntityToModel(savedUser));
            }
        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    /**
     * В случае успеха {@link ProcessStatus#SUCCESS}
     * В случае неуспеха {@link ProcessStatus#ENTITY_IS_NOT_EXIST}
     */
    @Override
    public RepositoryResponse<User> findById(@NonNull Long id) {

        try {
            final Optional<UserEntity> userEntity = repository.findById(id);

            if (userEntity.isPresent()) {

                UserEntity user = userEntity.get();

                return new RepositoryResponse<>(
                        ProcessStatus.SUCCESS,
                        mapper.convertEntityToModel(user)
                );

            } else {
                log.warn(String.format("Пользователь с id = %s не найден", id));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, new User());
            }
        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    @Override
    public RepositoryResponse<User> update(@NonNull User user) {
        try {
            final Optional<UserEntity> userEntity = repository.findById(user.getId());

            if (userEntity.isPresent()) {
                UserEntity savedUser = repository.save(mapper.convertModelToEntity(user));
                return new RepositoryResponse<>(ProcessStatus.SUCCESS, mapper.convertEntityToModel(savedUser));
            } else {
                log.warn(String.format("Пользователь с id = %s не найден", user.getId()));

                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_NOT_EXIST, user);
            }
        } catch (Throwable exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    @Override
    public RepositoryResponse<List<User>> findAll() {
        return null;
    }
}
