package com.bh.roommate.application.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Сущность User принимаемая на вход API приложения
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private String email;

    private String name;
}
