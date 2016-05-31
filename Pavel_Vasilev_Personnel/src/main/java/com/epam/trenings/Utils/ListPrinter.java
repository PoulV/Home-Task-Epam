package com.epam.trenings.Utils;

import java.util.List;

/**
 * Created by pava0715 on 31.05.2016.
 */
public class ListPrinter {
    public static void print(String header, List<?> listForPrint) {
        System.out.println();
        System.out.println(header);
        for (Object element : listForPrint) {
            System.out.println(element.toString());
        }
        System.out.println();
    }
}
