package com.bh.roommate.application.api.repository.user;

import com.bh.roommate.application.api.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Низкоуровневый репозиторий для кастомных запросов в БД
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
