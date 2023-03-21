package com.bh.roommate.application.impl.repository.user;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.entity.token.TokenEntity;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.RepositoryResponse;
import com.bh.roommate.application.api.repository.token.TokenRepository;
import com.bh.roommate.application.api.repository.user.UserRepository;
import com.bh.roommate.application.api.repository.user.UserRepositoryService;
import com.bh.roommate.application.api.service.mail.MailSendingService;
import com.bh.roommate.application.api.status.ProcessStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;

    private final UserMapper mapper;

    private final MailSendingService mailService;


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
    public RepositoryResponse<User> create(@NonNull User user) {
        try {

            final Optional<UserEntity> userEntity = repository.findByEmail(user.getEmail());

            if (userEntity.isPresent()) {
                log.warn(String.format("Пользователь с таким email = %s уже существует", user.getEmail()));
                return new RepositoryResponse<>(ProcessStatus.ENTITY_IS_EXIST, new User());
            } else {

                // 1. создаем токен для активации профиля по почте
                // 2. сохраняем его в отдельную таблицу с FK на юзера
                // 3. при переходе по ссылке через токен, проверяется токен у пользователя -> если такой существует, активируем поле в таблице пользователя

                UserEntity savedUser = repository.save(mapper.convertModelToEntity(user));
                TokenEntity token = createTokenEntity(savedUser);
                tokenRepository.save(token);

                // отправляем письмо со ссылкой-токеном
                //mailService.send(savedUser.getEmail(), "Подтверждение регистрации на сайте Roommate", createUri(token.getToken()));

                // TODO(вынести все говно выше по отправке сообщения в САЗ, чтобы пользователь на форме регистрации не ждал отправки письма)
                return new RepositoryResponse<>(ProcessStatus.SUCCESS, mapper.convertEntityToModel(savedUser));
            }
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
        } catch (Exception exception) {
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
        } catch (Exception exception) {
            log.error("Ошибка выполнения процесса:\n", exception);
            throw exception;
        }
    }

    @Override
    public RepositoryResponse<List<User>> findAll() {
        return null;
    }

    private TokenEntity createTokenEntity(UserEntity user) {
        String token = UUID.randomUUID().toString();

        TokenEntity entity = new TokenEntity();
        entity.setToken(token);
        entity.setUser(user);

        log.info(String.format("Для пользователя id = %s создан токен = %s", user.getId(), token));
        return entity;
    }

    private String createUri(String token) {
        return token;
    }
}
