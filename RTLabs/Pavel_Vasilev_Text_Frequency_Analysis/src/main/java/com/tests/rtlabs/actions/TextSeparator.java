package com.tests.rtlabs.actions;

import com.tests.rtlabs.actions.io.TextReader;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.tests.rtlabs.Utils.*;

/**
 * Created by 1 on 01.04.2017.
 */
public class TextSeparator {

    private static final Logger logger = Logger.getLogger(TextSeparator.class);

    public static List<String> separateText(String text4Separate) {
        logger.info("Start separated function.");

        List<String> listWords = new LinkedList<>();
        String stopWordDictionaryFilePath = getProperty(PROPERY_KEY_STOP_WORD_DICTIONARY);
        Set<String> stopDictionary = TextReader.getRows(stopWordDictionaryFilePath);
        StringBuilder ignoredWordBuilder = new StringBuilder();

        for (String ignoredWord : stopDictionary) {
            ignoredWordBuilder.setLength(0);
            ignoredWordBuilder.append(REGEX_WORD_SEPORATORS)
                    .append(ignoredWord)
                    .append(REGEX_WORD_SEPORATORS);
            text4Separate = text4Separate.replaceAll(ignoredWordBuilder.toString(), "");
        }
        logger.info("Words from stop words dictionary has been removed from text.");

        String[] wordArray = text4Separate.split(REGEX_WORD_SEPORATORS);
        logger.info("All text has been separated by regexp " + REGEX_WORD_SEPORATORS);

        for (String word : wordArray) {
            if (!word.isEmpty()) {
                listWords.add(word);
            } else {
                continue;
            }
        }

        logger.info("End separated function.");
        return listWords;
    }

}
