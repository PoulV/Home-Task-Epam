package com.epam.trenings.model;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pava0715 on 01.06.2016.
 */
@XmlRootElement(name = "composition")
@XmlType(propOrder = {"id", "name", "length", "albumsID"})
public class Composition implements INamed {
    private static Integer count = 0;
    private Integer id;
    private String name;

    private Long length;
    private Set<Integer> albumsID = new HashSet<>();

    public Composition() {
    }

    public Composition(String name, Long length) {
        this.name = name;
        this.length = length;
        this.id = ++count;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setLength(Long length) {
        this.length = length;
    }

    public Long getLength() {
        return length;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }


    public Set<Integer> getAlbumsID() {
        return albumsID;
    }

    @XmlElement(name = "album_id")
    @XmlElementWrapper
    public void setAlbumsID(Set<Integer> albumsID) {
        this.albumsID = albumsID;
    }
    public void appendAlbumsID(Integer... newAlbumID) {
        this.albumsID.addAll(Arrays.asList(newAlbumID));
    }
}