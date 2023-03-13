package com.bh.roommate.application.api.model.entity.user;

import com.bh.roommate.application.api.model.entity.form.FormEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private Gender gender;

    private Date birthday;

    private String phone;

    private String photo;

    @OneToOne(mappedBy = "owner")
    private FormEntity form;
}
