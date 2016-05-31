package com.epam.trenings.Utils;

import java.util.Map;

/**
 * Created by pava0715 on 31.05.2016.
 */
public class MapPrinter {
    public static void print(String header, Map<String, Long> mapForPrint) {
        System.out.println();
        System.out.println(header);
        for (Map.Entry<String, Long> element : mapForPrint.entrySet()) {
            System.out.println(element.getKey() + " repeat " + element.getValue());
        }
        System.out.println();
    }
}
