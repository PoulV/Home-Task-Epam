package com.epam.trenings;

import java.util.Random;

/**
 * Created by Pol on 5/29/2016.
 */
public class Creator {
    private static final String[] MALE_NAMES = {"JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID",
            "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "CHRISTOPHER", "DANIEL", "PAUL", "MARK", "DONALD",
            "GEORGE", "KENNETH", "STEVEN", "EDWARD", "BRIAN"};
    private static final String[] FEMALE_NAMES = {"MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH", "JENNIFER",
            "MARIA", "SUSAN", "MARGARET", "DOROTHY", "LISA", "NANCY", "KAREN", "BETTY", "HELEN", "SANDRA",
            "DONNA", "CAROL", "RUTH", "SHARON"};
    private static final String[] SEX = {"M", "F"};


    private static Random random = new Random();
    public static Person createPerson() {

        String sexForNewPerson = SEX[random.nextInt(SEX.length)];
        String nameForNewPerson = "";
        switch (sexForNewPerson) {
            case ("M"):
                nameForNewPerson = MALE_NAMES[random.nextInt(MALE_NAMES.length)];
                break;
            case ("F"):
                nameForNewPerson = FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        }

        Person newGeneratedPerson = new Person(nameForNewPerson, random.nextInt(90), sexForNewPerson);
        return newGeneratedPerson;
    }
}
