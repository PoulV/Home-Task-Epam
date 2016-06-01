package com.epam.trenings.person;

/**
 * Created by pava0715 on 31.05.2016.
 */
public class FemaleUpdater {
    public static Person updateFemale(Person person) {
        if ((person.getSex().equals(Person.Gender.FEMALE)) && ((person.getAge() - 10) >= 1)) {
            person.setAge(person.getAge() - 10);
        }
        return person;
    }
}
