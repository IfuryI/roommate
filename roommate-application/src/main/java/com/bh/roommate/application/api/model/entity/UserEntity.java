package com.bh.roommate.application.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность User в БД
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
}
