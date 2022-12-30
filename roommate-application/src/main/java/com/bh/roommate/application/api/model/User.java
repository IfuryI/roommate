package com.bh.roommate.application.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Модель сущности User
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Long id;

    private String email;

    private String name;
}
