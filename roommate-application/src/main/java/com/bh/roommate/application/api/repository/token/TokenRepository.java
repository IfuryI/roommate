package com.bh.roommate.application.api.repository.token;

import com.bh.roommate.application.api.model.entity.token.TokenEntity;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Низкоуровневый репозиторий для кастомных запросов в БД
 */
@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByUser(UserEntity user);

    Optional<TokenEntity> findByToken(String token);
}
