package com.tests.rtlabs.actions.io;

import com.tests.rtlabs.exceptions.CustomIOException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.tests.rtlabs.Utils.LINE_SEPARATOR;
import static com.tests.rtlabs.exceptions.ExceptionUtils.throwCustomIOException;

/**
 * Created by 1 on 01.04.2017.
 */
public class TextReader {
    private static final Logger logger = Logger.getLogger(TextReader.class);

    public static String getStringFromFile(String path2File) throws CustomIOException {
        logger.info("Start read all lines from " + path2File);

        String stringWithAllText = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path2File))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line)
                        .append(LINE_SEPARATOR);
                line = bufferedReader.readLine();
            }

            stringWithAllText = stringBuilder.toString().toLowerCase();
        } catch (IOException ioException) {
            throwCustomIOException(ioException, path2File);
        }
        logger.info("All lines from " + path2File + " has been read.");
        return stringWithAllText;
    }

    public static Set<String> getRows(String path2File) {
        logger.info("Start read all lines from " + path2File);
        Set<String> setRows = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path2File))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                setRows.add(line.toLowerCase());
                line = bufferedReader.readLine();
            }
        } catch (IOException ioException) {
            throwCustomIOException(ioException, path2File);
        }
        logger.info("All lines from " + path2File + " has been read.");
        return setRows;
    }
}
