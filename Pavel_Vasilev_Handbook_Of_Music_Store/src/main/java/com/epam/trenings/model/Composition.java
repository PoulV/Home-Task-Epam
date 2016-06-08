package com.epam.trenings.model;

import com.epam.trenings.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Composition implements INamed {
    private List<Album> albumList = new LinkedList<>();
    private String name;
    private Long length;

    public Composition(String name, Long length) {
        this.name = name;
        this.length = length;
    }

    public void setAlbums(Album... parentAlbums) {
        for (Album album : parentAlbums) {
            if (!albumList.contains(album)) {
                Utils.putIfNotExist(albumList, album);
            }
        }
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public Long getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }
}