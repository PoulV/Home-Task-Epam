package com.tests.rtlabs;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import static com.tests.rtlabs.exceptions.ExceptionUtils.throwCustomIOException;

/**
 * Created by 1 on 01.04.2017.
 */
public class Utils {
    public static final String PROPERY_KEY_INPUT = "inputFilePath";
    public static final String PROPERY_KEY_OUTPUT = "outputFilePath";
    public static final String PROPERY_KEY_STOP_WORD_DICTIONARY = "stopWordDictionary";
    public static final String LINE_SEPARATOR = " ";
    public static final String TABULATION = "\t";
    public static final String REGEX_WORD_SEPORATORS = "[\\s()/\\.\\,-_\\'\\\"\\@\\?!\\:]";
    public static final String PROPERTY_FILE = "src\\main\\resources\\solution.properties";
    public static final String SPACE = " ";

    private static final Logger logger = Logger.getLogger(Utils.class);

    private static Properties property;

    public static void initProperty() {
        logger.info("Start property initiation.");
        property = new Properties();
        try (InputStream input = new FileInputStream(PROPERTY_FILE)) {
            property.load(input);
        } catch (IOException ioException) {
            throwCustomIOException(ioException, PROPERTY_FILE);
        }
        logger.info("Property has been loaded from " + PROPERTY_FILE);
    }

    public static Map<Integer, String> getFequencyMap(Collection<String> separatedText) {
        return null;
    }

    public static String getProperty(String propertyKey) {
        return property.getProperty(propertyKey);
    }


}
