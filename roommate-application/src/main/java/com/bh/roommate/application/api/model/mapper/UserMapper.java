package com.bh.roommate.application.api.model.mapper;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.model.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Маппер для сущности User
 */
// если маппинг поменяется, придется реализовывать интерфейс, сейчас интерфейс во время компиляции генерируется
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User convertEntityToModel(UserEntity entity);

    UserEntity convertModelToEntity(User model);

    UserDto convertModelToDto(User model);

    User convertDtoToModel(UserDto dto);
}
