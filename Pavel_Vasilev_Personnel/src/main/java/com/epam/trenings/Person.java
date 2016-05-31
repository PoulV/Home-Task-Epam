package com.epam.trenings;

import java.util.Random;

/**
 * Created by Pol on 5/29/2016.
 */
public class Person {
    private Random random = new Random();
    private static final String[] MALE_NAMES = {"JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID",
            "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "CHRISTOPHER", "DANIEL", "PAUL", "MARK", "DONALD",
            "GEORGE", "KENNETH", "STEVEN", "EDWARD", "BRIAN"};
    private static final String[] FEMALE_NAMES = {"MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH", "JENNIFER",
            "MARIA", "SUSAN", "MARGARET", "DOROTHY", "LISA", "NANCY", "KAREN", "BETTY", "HELEN", "SANDRA",
            "DONNA", "CAROL", "RUTH", "SHARON"};

    public enum Gender {
        MALE, FEMALE
    }

    private String name;
    private Integer age;
    private Gender sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Person() {
        Gender sexForNewPerson = Gender.values()[random.nextInt(Gender.values().length)];
        String nameForNewPerson = "NoName";
        switch (sexForNewPerson) {
            case MALE:
                nameForNewPerson = MALE_NAMES[random.nextInt(MALE_NAMES.length)];
                break;
            case FEMALE:
                nameForNewPerson = FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        }

        setName(nameForNewPerson);
        setSex(sexForNewPerson);
        setAge(random.nextInt(99) + 1);
    }

    public Person(String name, Integer age, Gender sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public void print() {
        System.out.println(this);
    }
}
