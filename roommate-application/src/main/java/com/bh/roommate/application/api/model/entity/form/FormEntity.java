package com.bh.roommate.application.api.model.entity.form;

import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.model.meta.Alcoholic;
import com.bh.roommate.application.api.model.meta.ContactType;
import com.bh.roommate.application.api.model.meta.Currency;
import com.bh.roommate.application.api.model.meta.Guests;
import com.bh.roommate.application.api.model.meta.HouseholdType;
import com.bh.roommate.application.api.model.meta.LanguageType;
import com.bh.roommate.application.api.model.meta.MeetingType;
import com.bh.roommate.application.api.model.meta.Occupation;
import com.bh.roommate.application.api.model.meta.PetType;
import com.bh.roommate.application.api.model.meta.SleepingHabits;
import com.bh.roommate.application.api.model.meta.SmokeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Сущность анкеты пользователя в БД
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "form")
public class FormEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity owner;

    private Boolean isHavePets;

    @ElementCollection
    private List<PetType> petTypes;

    private Boolean isSmoke;

    @ElementCollection
    private List<SmokeType> smokeTypes;

    @ElementCollection
    private List<LanguageType> languageTypes;

    private String aboutMe;

    @ElementCollection
    private List<ContactType> contactTypes;

    private Integer ageFrom;

    private Integer ageTo;

    private Boolean isHaveApartment;

    private String country;

    private String city;

    private Integer priceFrom;

    private Integer priceTo;

    private Currency currency;

    private SleepingHabits sleepingHabits;

    private Alcoholic alcoholic;

    private Guests guests;

    private Occupation occupation;

    private HouseholdType householdType;

    private MeetingType meetingType;

}
