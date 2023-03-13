package com.bh.roommate.application.api.model.entity.token;

import com.bh.roommate.application.api.model.entity.user.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность Token в БД
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activation_tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    // TODO нужно на стороне бд создать задачу по удаления записи по истечению какого то времени (ttl токена)
    @Column(unique = true)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
