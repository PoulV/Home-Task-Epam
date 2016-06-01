package com.epam.trenings.person;

import java.util.Random;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class PersonGenerator {
    private static Random random = new Random();
    private static final String[] MALE_NAMES = {"JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID",
            "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "CHRISTOPHER", "DANIEL", "PAUL", "MARK", "DONALD",
            "GEORGE", "KENNETH", "STEVEN", "EDWARD", "BRIAN"};
    private static final String[] FEMALE_NAMES = {"MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH", "JENNIFER",
            "MARIA", "SUSAN", "MARGARET", "DOROTHY", "LISA", "NANCY", "KAREN", "BETTY", "HELEN", "SANDRA",
            "DONNA", "CAROL", "RUTH", "SHARON"};

    public static Person generateRandomPerson() {
        Person.Gender sexForNewPerson = Person.Gender.values()[random.nextInt(Person.Gender.values().length)];
        String nameForNewPerson = "NoName";
        switch (sexForNewPerson) {
            case MALE:
                nameForNewPerson = MALE_NAMES[random.nextInt(MALE_NAMES.length)];
                break;
            case FEMALE:
                nameForNewPerson = FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        }
        Integer ageForNewPerson = random.nextInt(99) + 1;
        return new Person(nameForNewPerson, ageForNewPerson, sexForNewPerson);
    }
}
