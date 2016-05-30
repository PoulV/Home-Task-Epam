package com.epam.trenings;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.LinkedList;
import java.util.List;
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

        Double averageAge = streamOfPerson.limit(100)
                .mapToInt(Person::getAge)
                .average().getAsDouble();
        System.out.println("Average age is " + averageAge);


        //collection.stream().sorted((o1, o2) -> -o1.compareTo(o2)).collect(Collectors.toList())
        streamOfPerson = generate(personMaker);
        streamOfPerson.limit(20)
                .peek(Person::print)
                .sorted((personFirst, personSecond) -> {-personFirst.getAge().compareTo(personSecond.getAge())})
                .peek(Person::print)
        .collect(Collectors.toList());




    }
}
