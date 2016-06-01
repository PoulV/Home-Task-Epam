package com.epam.trenings;

import com.epam.trenings.person.FemaleUpdater;
import com.epam.trenings.person.Person;
import com.epam.trenings.person.PersonGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.generate;


/**
 * Created by Pol on 5/29/2016.
 */
public class Runner {
    public static void run() {
        Supplier<Person> personMaker = PersonGenerator::generateRandomPerson;
        Stream<Person> streamOfPerson = generate(personMaker);

        System.out.println("Stream person before calculate average age:");
        Double averageAge = streamOfPerson.limit(10)
                .peek(System.out::println)
                .mapToInt(Person::getAge)
                .average().getAsDouble();
        System.out.println("Average age is " + averageAge);


        streamOfPerson = generate(personMaker);
        System.out.println();
        System.out.println("Stream person before sort:");
        List<Person> sortedPersonList = streamOfPerson.limit(10)
                .peek(System.out::println)
                .sorted((personFirst, personSecond) -> personFirst.getAge().compareTo(personSecond.getAge()))
                .collect(Collectors.toList());
        Utils.printList("Stream person after sort by age:", sortedPersonList);

        System.out.println("Stream person before select repeat person:");
        streamOfPerson = generate(personMaker);
        Map<String, Long> mapOfNames = streamOfPerson.limit(10)
                .peek(System.out::println)
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        Utils.printMap("Repeat person:", mapOfNames);

        System.out.println("Stream person before update female person:");
        streamOfPerson = generate(personMaker);
        List<Person> listPersonToUpdate = streamOfPerson.limit(10)
                .peek(System.out::println)
                .map(FemaleUpdater::updateFemale)
                .collect(Collectors.toList());
        Utils.printList("Stream person with updated female person:", listPersonToUpdate);

        streamOfPerson = generate(personMaker);
        System.out.println();
        System.out.println("Stream person with teen:");
        List<Person> listPersonWithoutTeen = streamOfPerson.limit(20)
                .peek(System.out::println)
                .filter(person ->  person.getAge() > 18 )
                .collect(Collectors.toList());
        Utils.printList("Stream person without teen:", listPersonWithoutTeen);
    }
}
