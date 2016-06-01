package com.epam.trenings;

import java.util.List;
import java.util.Map;

/**
 * Created by pava0715 on 01.06.2016.
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

    public static void printMap(String header, Map<String, Long> mapForPrint) {
        System.out.println();
        System.out.println(header);
        for (Map.Entry<String, Long> element : mapForPrint.entrySet()) {
            System.out.println(element.getKey() + " repeat " + element.getValue());
        }
        System.out.println();
    }
}
