package com.bh.roommate.application.impl.service.da.token;

import com.bh.roommate.application.api.model.mapper.UserMapper;
import com.bh.roommate.application.api.repository.token.TokenRepository;
import com.bh.roommate.application.api.repository.user.UserRepositoryService;
import com.bh.roommate.application.api.service.da.OperationResponse;
import com.bh.roommate.application.api.service.da.token.TokenService;
import com.bh.roommate.application.api.status.OperationStatus;
import com.bh.roommate.application.api.status.ProcessStatus;
import com.bh.roommate.application.api.status.Severity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repository;
    private final UserRepositoryService userRepository;

    private final UserMapper userMapper;

    @Override
    public OperationResponse<String> checkToken(String token) {

        var tokenEntity = repository.findByToken(token);

        if (tokenEntity.isPresent()) {
            var userToken = tokenEntity.get();
            userToken.getUser().setIsActiveProfile(true);

            userRepository.update(userMapper.convertEntityToModel(userToken.getUser()));

            log.info(String.format("Пользователь с id = %s успешно активировал профиль", userToken.getUser().getId()));

            return new OperationResponse<String>(new OperationStatus(Severity.SUCCESS, ProcessStatus.SUCCESS.getStatusCode())
                    .setStatusCodeDescription("Успех"))
                    .setResponse("success");
        } else {

            log.warn(String.format("Токен со значением = %s не найден", token));

            return new OperationResponse<String>(new OperationStatus(Severity.ERROR, ProcessStatus.ENTITY_IS_NOT_EXIST.getStatusCode())
                    .setStatusCodeDescription("Ошибка"))
                    .setResponse("error");
        }
    }
}
