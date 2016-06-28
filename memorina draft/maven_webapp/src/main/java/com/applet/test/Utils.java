package com.applet.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by 1 on 28.06.2016.
 */
public class Utils {
    public static final String BACK_IMG_PATH = "images/Back_1.bmp";
    public static final String FACES_IMG_FOLDER_PATHS = "images/faces";


    public static List<URL> getAllFilesUrlFromDirectory(String targetDirectoryPath) {
        List<URL> resultLis = new LinkedList<>();
        File folder = new File(targetDirectoryPath);
        for(File currentFile : folder.listFiles()) {
            try {
                resultLis.add(currentFile.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return resultLis;
    }
}
