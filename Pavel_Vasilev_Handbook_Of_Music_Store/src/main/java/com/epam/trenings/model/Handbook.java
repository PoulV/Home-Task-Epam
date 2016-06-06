package com.epam.trenings.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Handbook implements Serializable {
    public Map<String, Musician> mapOfMusicians = new HashMap<>();

    public Handbook() {

    }

    public void addMusician(Musician... newMusicians) {
        for (Musician newMusician : newMusicians) {
            mapOfMusicians.put(newMusician.getName(), newMusician);
        }
    }

    @Override
    public String toString() {
        StringBuffer handbookAsText = new StringBuffer();
        Musician currentMusician;
        Album currentAlbum;
        Composition currentComposition;

        for (Map.Entry<String, Musician> musicianEntry : mapOfMusicians.entrySet()) {
            currentMusician = musicianEntry.getValue();
            handbookAsText.append("\t").append(currentMusician).append("\r\n");
            for (Map.Entry<String, Album> albumEntry : currentMusician.getAlbumMap().entrySet()) {
                currentAlbum = albumEntry.getValue();
                handbookAsText.append("\t\t").append(currentAlbum).append("\r\n");
                for (Map.Entry<String, Composition> compositionEntry : currentAlbum.getCompositionMap().entrySet()) {
                    currentComposition = compositionEntry.getValue();
                    handbookAsText.append("\t\t\t").append(currentComposition).append("\r\n");
                }
            }
        }

        return handbookAsText.toString();
    }
}
