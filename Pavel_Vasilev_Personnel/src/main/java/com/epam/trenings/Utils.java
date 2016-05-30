package com.epam.trenings;

import java.util.List;
import java.util.Map;

/**
 * Created by pava0715 on 30.05.2016.
 */
public class Utils {
    public static void printList(String header, List<?> listForPrint) {
        System.out.println();
        System.out.println(header);
        for (Object element : listForPrint) {
            System.out.println(element.toString());
        }
        System.out.println();
    }

    public static void setNameToMap(Person person, Map<String, Integer> resultMap) {
       /* if (resultMap.containsKey(person.getName())) {
            resultMap.put(person.getName(), resultMap.get(person.getName()) + 1);
        } else {
            resultMap.put(person.getName(), 1);
        }*/
    }

    public static void printMap(String header, Map<String, Integer> mapForPrint) {
        System.out.println();
        System.out.println(header);
        for (Map.Entry<String, Integer> element : mapForPrint.entrySet()) {
            System.out.println(element.getKey() + " repeat " + element.getValue());
        }
        System.out.println();
    }


}
