package com.epam.trenings.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Album implements Serializable {
    private String name;
    private String genre;
    private Map<String, Composition> compositionMap = new HashMap<>();
    private Map<String, Musician> musicianMap = new HashMap<>();

    public Album(String name, String genre, Musician... parentMusicians) {
        this.name = name;
        this.genre = genre;
        setMusician(parentMusicians);
    }

    public void addComposition(Composition newComposition) {
        newComposition.setAlbums(this);
        compositionMap.put(newComposition.getName(), newComposition);
    }

    public void setMusician(Musician... parentMusicians) {
        for (Musician musician : parentMusicians) {
            musicianMap.put(musician.getName(), musician);
        }
    }

    public Map<String, Composition> getCompositionMap() {
        return compositionMap;
    }

    public Map<String, Musician> getMusicianMap() {
        return musicianMap;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Album- " +
                "name=" + name +
                ", genre=" + genre;
    }
}
