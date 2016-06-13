package com.epam.trenings.model;

import com.epam.trenings.Utils;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
@XmlRootElement(name = "musician")
@XmlType(propOrder = {"id", "name", "albumList"})
public class Musician implements INamed {
    private List<Album> albumList = new LinkedList<>();
    private String name;
    private Integer id;
    private static Integer count = 0;

    public Musician() {
    }

    public Musician(String name) {
        this.name = name;
        this.id = ++count;
    }

    @XmlElement(name = "album")
    @XmlElementWrapper
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void addAlbums(Album... newAlbums) {
        for (Album newAlbum : newAlbums) {
            newAlbum.getMusiciansID().add(id);
            if (!albumList.contains(newAlbum)) {
                Utils.putIfNotExist(albumList, newAlbum);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getId() {
        return id;
    }
}