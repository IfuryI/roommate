package com.bh.roommate.application.api.model;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель сущности Анкета
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Form {

    private Long id;

    private User owner;

    private Boolean isHavePets;

    private List<PetType> petTypes;

    private Boolean isSmoke;

    private List<SmokeType> smokeTypes;

    private List<LanguageType> languageTypes;

    private String aboutMe;

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

    private Boolean likeSmokers;

    private MeetingType meetingType;
}
