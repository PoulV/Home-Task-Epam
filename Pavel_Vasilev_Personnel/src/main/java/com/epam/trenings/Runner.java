package com.epam.trenings;

import com.sun.xml.internal.ws.message.Util;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;
import static java.util.stream.Stream.generate;

/**
 * Created by Pol on 5/29/2016.
 */
public class Runner {
    public static void run() {
        Supplier<Person> personMaker = Creator::createPerson;
        Stream<Person> streamOfPerson = generate(personMaker);

        Double averageAge = streamOfPerson.limit(10)
                .mapToInt(Person::getAge)
                .average().getAsDouble();
        System.out.println("Average age is " + averageAge);


        streamOfPerson = generate(personMaker);
        //System.out.println();
        System.out.println("Stream person before sort:");
        List<Person> sortedPersonList = streamOfPerson.limit(10)
                .peek(Person::print)
                .sorted((personFirst, personSecond) -> personFirst.getAge().compareTo(personSecond.getAge()))
                .collect(Collectors.toList());
        Utils.printList("Stream person after sort by age:", sortedPersonList);

        //System.out.println();
        System.out.println("Stream person before select repeat person:");
        Map<String, Integer> mapOfNames = new HashMap<>();
        streamOfPerson = generate(personMaker);
        streamOfPerson.limit(10)
                .peek(Person::print)
                .forEach((person)->{if (mapOfNames.containsKey(person.getName())) {
                    mapOfNames.put(person.getName(), mapOfNames.get(person.getName()) + 1);
                } else {
                    mapOfNames.put(person.getName(), 1);
                }});
        Utils.printMap("Repeat person:", mapOfNames);

        System.out.println("Stream person before update female person:");
        streamOfPerson = generate(personMaker);
        streamOfPerson.limit(10)
                .peek(Person::print)
                .forEach(Creator::updateFemale(person));
        Utils.printMap("Repeat person:", mapOfNames);



    }
}
