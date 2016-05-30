package com.epam.trenings.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pol on 5/29/2016.
 */
public class ClassExecutor {
    public static Set<Class> getClassesFromPackege(String packageName) {
        Set<Class> resultSet = new HashSet<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration resources = classLoader.getResources(path);

            URL resource = (URL) resources.nextElement();
            File directory = new File(resource.getFile());
            File[] files = directory.listFiles();
            for (File file : files) {
                resultSet.add(Class.forName(packageName + '.'
                        + file.getName().split("\\.")[0]));
            }
        } catch (ClassNotFoundException noClassException) {
            System.out.println("Can't find any class from " + packageName);
            noClassException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("Input/Output exception when parse " + packageName);
            ioException.printStackTrace();
        }
        return resultSet;
    }
}
