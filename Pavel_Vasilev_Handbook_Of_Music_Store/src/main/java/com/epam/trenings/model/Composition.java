package com.epam.trenings.model;

import com.epam.trenings.Utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Composition implements Serializable {
    private Map<String, Album> albumMap = new HashMap<>();
    private String name;
    private Long length;

    public String getName() {
        return name;
    }

    public Composition(String name, Long length, Album... parentAlbums) {
        this.name = name;
        this.length = length;
        setAlbums(parentAlbums);
    }

    public void setAlbums(Album... parentAlbums) {
        for (Album album : parentAlbums) {
            albumMap.put(album.getName(), album);
        }
    }

    public Map<String, Album> getAlbumMap() {
        return albumMap;
    }

    @Override
    public String toString() {
        return "Composition- " +
                "name=" + name +
                ", length=" + Utils.getReadableTime(length);
    }
}
