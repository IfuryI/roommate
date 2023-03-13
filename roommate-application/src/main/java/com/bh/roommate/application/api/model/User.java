package com.bh.roommate.application.api.model;

import com.bh.roommate.application.api.model.entity.form.FormEntity;
import com.bh.roommate.application.api.model.entity.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Модель сущности User
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Long id;

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
