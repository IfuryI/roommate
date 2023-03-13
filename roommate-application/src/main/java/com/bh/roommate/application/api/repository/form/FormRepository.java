package com.bh.roommate.application.api.repository.form;

import com.bh.roommate.application.api.model.entity.form.FormEntity;
import com.bh.roommate.application.api.model.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Низкоуровневый репозиторий для кастомных запросов в БД
 */
@Repository
public interface FormRepository extends CrudRepository<FormEntity, Long> {
}
