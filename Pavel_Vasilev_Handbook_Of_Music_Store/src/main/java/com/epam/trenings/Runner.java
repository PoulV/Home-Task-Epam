package com.epam.trenings;

import com.epam.trenings.io.ByteTypeLoader;
import com.epam.trenings.io.TextTypeLoader;
import com.epam.trenings.model.Handbook;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Runner {
    public static void run() {
        Handbook handbookOfMusicStore = Utils.getFilledHandbook();
        Utils.printHandbook("Handbook of music stroe:", handbookOfMusicStore);

        TextTypeLoader textLoader = new TextTypeLoader();
        Utils.presentLoadedHandbook(handbookOfMusicStore, textLoader, "HandbookAsText.txt");

        ByteTypeLoader byteLoader = new ByteTypeLoader();
        Utils.presentLoadedHandbook(handbookOfMusicStore, byteLoader, "BinaryHandbook.bin");
    }
}