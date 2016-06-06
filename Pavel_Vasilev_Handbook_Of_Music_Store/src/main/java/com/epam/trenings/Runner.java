package com.epam.trenings;

import com.epam.trenings.io.ByteTypeLoader;
import com.epam.trenings.io.TextTypeLoader;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Runner {
    public static void run() {

        Musician firstMusician = new Musician("firstSinger");
        Musician secondMusician = new Musician("secondSinger");
        Musician thirdMusician = new Musician("thirdSinger");

        Album firstAlbum = new Album("firstAlbum", "jazz", firstMusician, thirdMusician);
        Album secondAlbum = new Album("secondAlbum", "classic", secondMusician, thirdMusician);

        Composition fistComposition = new Composition("firstSong", new Long(5 * 60 * 1000 + 132), firstAlbum);
        Composition secondComposition = new Composition("secondSong", new Long(6 * 60 * 1000 + 132), secondAlbum);
        Composition thirdComposition = new Composition("thirdSong", new Long(10 * 60 * 1000 + 132), firstAlbum, secondAlbum);

        firstAlbum.addComposition(fistComposition);
        firstAlbum.addComposition(thirdComposition);
        secondAlbum.addComposition(secondComposition);
        secondAlbum.addComposition(thirdComposition);

        firstMusician.addAlbum(firstAlbum);
        secondMusician.addAlbum(secondAlbum);
        thirdMusician.addAlbum(firstAlbum);
        thirdMusician.addAlbum(secondAlbum);

        Handbook handbookOfMusicStore = new Handbook();
        handbookOfMusicStore.addMusician(firstMusician, secondMusician, thirdMusician);
        Utils.printHandbook("Handbook of music stroe:", handbookOfMusicStore);

        ByteTypeLoader byteLoader = new ByteTypeLoader();
        byteLoader.save(handbookOfMusicStore, "binaryHandbook.bin");
        Handbook loadedFromBinaryFile = byteLoader.load("binaryHandbook.bin");
        Utils.printHandbook("Handbook loaded from binary file:", loadedFromBinaryFile);

        TextTypeLoader textLoader = new TextTypeLoader();
        textLoader.save(handbookOfMusicStore, "TextOfHandbook.txt");
        Handbook loadedFromTextFile = textLoader.load("TextOfHandbook.txt");
        Utils.printHandbook("Handbook loaded from text file:", loadedFromBinaryFile);
    }
}
