package com.bh.roommate.application.api.model.mapper;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.dto.FormDto;
import com.bh.roommate.application.api.model.entity.form.FormEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Маппер для сущности Анкета
 */
// если маппинг поменяется, придется реализовывать интерфейс, сейчас интерфейс во время компиляции генерируется
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FormMapper {

    Form convertEntityToModel(FormEntity entity);

    FormEntity convertModelToEntity(Form model);

    FormDto convertModelToDto(Form model);

    Form convertDtoToModel(FormDto dto);
}
