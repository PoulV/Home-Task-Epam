package com.applet.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static com.applet.test.Utils.*;

/**
 * Created by 1 on 28.06.2016.
 */
public class SuiteGenerator {

    private URL backImageUrl;
    private List<URL> allImagesUrl;
    private Random random;


    public SuiteGenerator() {
        try {
            backImageUrl = (new File(BACK_IMG_PATH)).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        allImagesUrl = getAllFilesUrlFromDirectory(FACES_IMG_FOLDER_PATHS);
    }

    public List<Card> getCardSuite(Difficult currentDifficult) {
        List<Card> resultCardSuite = new LinkedList<>();
        long shuffleSeed = System.nanoTime();
        int newCardId;

        for (int current = 0; current < currentDifficult.getCardCount() / 2; current++) {
            newCardId = random.nextInt(allImagesUrl.size());
            resultCardSuite.add(generateCardById(newCardId));
            resultCardSuite.add(generateCardById(newCardId));
        }
        Collections.shuffle(resultCardSuite, new Random(shuffleSeed));
        return resultCardSuite;
    }

    public Card generateCardById(int cardId) {
        URL newCardFace = allImagesUrl.get(cardId);
        return new Card(cardId, newCardFace, backImageUrl);
    }
}
