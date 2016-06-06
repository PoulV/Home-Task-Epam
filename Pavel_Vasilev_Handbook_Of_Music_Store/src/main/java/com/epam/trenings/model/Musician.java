package com.epam.trenings.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Musician implements Serializable{
    private Map<String, Album> albumMap = new HashMap<>();
    private String name;

    public String getName() {
        return name;
    }

    public Musician(String name) {
        this.name = name;
    }

    public void addAlbum(Album... newAlbums) {
        for (Album newAlbum : newAlbums) {
            newAlbum.setMusician(this);
            albumMap.put(newAlbum.getName(), newAlbum);
        }
    }

    public Map<String, Album> getAlbumMap() {
        return albumMap;
    }

    @Override
    public String toString() {
        return "Musician- name=" + name;
    }

}
