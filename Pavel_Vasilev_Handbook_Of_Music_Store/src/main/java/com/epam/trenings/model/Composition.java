package com.epam.trenings.model;

import com.epam.trenings.Utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Composition implements Serializable, INamed {
    private List<Album> albumList = new LinkedList<>();
    private String name;
    private Long length;

    public Composition(String name, Long length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbums(Album... parentAlbums) {
        for (Album album : parentAlbums) {
            if (!albumList.contains(album)) {
                Utils.putIfNotExist(albumList, album);
            }
        }
    }


}
