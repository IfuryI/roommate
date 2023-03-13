package com.bh.roommate.application.api.model.dto;

import com.bh.roommate.application.api.model.entity.form.FormEntity;
import com.bh.roommate.application.api.model.entity.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Сущность User принимаемая на вход API приложения
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private FormDto form;

    private String email;

    private String password;

    private String role;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Date birthday;

    private String phone;

    private String photo;
}
