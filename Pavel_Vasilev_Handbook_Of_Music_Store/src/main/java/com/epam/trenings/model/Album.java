package com.epam.trenings.model;

import com.epam.trenings.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Album implements INamed {
    private String name;
    private String genre;
    private List<Composition> compositionList = new LinkedList<>();
    private List<Musician> musicianList = new LinkedList<>();

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<Composition> getCompositionList() {
        return compositionList;
    }

    public List<Musician> getMusicianList() {
        return musicianList;
    }

    public void addComposition(Composition... newCompositions) {
        for (Composition newComposition : newCompositions) {
            newComposition.setAlbums(this);
            if (!compositionList.contains(newComposition)) {
                Utils.putIfNotExist(compositionList, newComposition);
            }
        }
    }

    public void setMusician(Musician... parentMusicians) {
        for (Musician musician : parentMusicians) {
            if (!musicianList.contains(musician)) {
                Utils.putIfNotExist(musicianList, musician);
            }
        }
    }
}