package com.tests.rtlabs.actions;

import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 01.04.2017.
 */
public class FrequencyAnalyser {

    private static final Logger logger = Logger.getLogger(FrequencyAnalyser.class);

    public static Map<String, Integer> getMapFromSeparatedText(List<String> separatedText) {
        logger.info("Start frequency analyse.");
        Map<String, Integer> frequencyMap = new LinkedHashMap<>();
        Integer count;
        for (String word : separatedText) {
            count = 1;
            if (frequencyMap.containsKey(word)) {
                count = frequencyMap.get(word) + 1;
            }
            frequencyMap.put(word, count);
        }
        logger.info("End frequency analyse.");
        return frequencyMap;
    }
}
