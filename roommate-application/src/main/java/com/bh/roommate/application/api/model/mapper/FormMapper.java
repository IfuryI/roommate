package com.bh.roommate.application.api.model.mapper;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.model.dto.FormDto;
import com.bh.roommate.application.api.model.entity.form.FormEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности Анкета
 */
// если маппинг поменяется, придется реализовывать интерфейс, сейчас интерфейс во время компиляции генерируется
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FormMapper {

    List<Form> convertListEntityToListModel(List<FormEntity> list);

    List<FormDto> convertListModelToListDto(List<Form> list);

    Form convertEntityToModel(FormEntity entity);

    FormEntity convertModelToEntity(Form model);

    FormDto convertModelToDto(Form model);

    Form convertDtoToModel(FormDto dto);
}
