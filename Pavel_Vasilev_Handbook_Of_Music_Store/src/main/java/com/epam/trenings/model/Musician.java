package com.epam.trenings.model;

import com.epam.trenings.Utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Musician implements Serializable, INamed {
    private List<Album> albumList = new LinkedList<>();
    private String name;

    public Musician(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void addAlbums(Album... newAlbums) {
        for (Album newAlbum : newAlbums) {
            newAlbum.setMusician(this);
            if (!albumList.contains(newAlbum)) {
                Utils.putIfNotExist(albumList, newAlbum);
            }
        }
    }


}
