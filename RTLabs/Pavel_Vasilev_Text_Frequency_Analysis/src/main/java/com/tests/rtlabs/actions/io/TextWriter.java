package com.tests.rtlabs.actions.io;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import static com.tests.rtlabs.Utils.TABULATION;
import static com.tests.rtlabs.exceptions.ExceptionUtils.throwCustomIOException;

/**
 * Created by 1 on 01.04.2017.
 */
public class TextWriter {
    private static final Logger logger = Logger.getLogger(TextWriter.class);

    public static void writeMap2File(Map<String, Integer> mapWords, String path2File) {
        logger.info("Start write Map to " + path2File + ".");

        StringBuilder rowBuilder = new StringBuilder();
        for (Map.Entry<String,Integer> wordFromMap : mapWords.entrySet()) {
            rowBuilder.append(wordFromMap.getKey())
                    .append(TABULATION)
                    .append(wordFromMap.getValue())
                    .append(System.lineSeparator());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path2File, false))) {
            writer.write(rowBuilder.toString());
        } catch (IOException ioException) {
            throwCustomIOException(ioException, path2File);
        }
        logger.info("Map has been written to " + path2File + ".");
    }
}
