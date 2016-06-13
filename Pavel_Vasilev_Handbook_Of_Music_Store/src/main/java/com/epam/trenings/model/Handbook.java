package com.epam.trenings.model;

import com.epam.trenings.Utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pava0715 on 01.06.2016.
 */
@XmlRootElement(name = "handbook")
@XmlType(propOrder = {"musiciansList"})
public class Handbook {
    private List<Musician> musiciansList = new LinkedList<>();

    public Handbook() {

    }

    public Handbook(List<Musician> musiciansList) {
        this.musiciansList = musiciansList;
    }

    public List<Musician> getMusiciansList() {
        return musiciansList;
    }

    @XmlElement(name = "musician")
    @XmlElementWrapper
    public void setMusiciansList(List<Musician> musiciansList) {
        this.musiciansList = musiciansList;
    }

    public List<Composition> getSongs() {
        List<Composition> resultSongList = new LinkedList<>();
        Composition[] tempSongs;
        for (Musician musician : musiciansList) {
            for (Album album : musician.getAlbumList()) {
                tempSongs = album.getCompositionList().toArray(new Composition[album.getCompositionList().size()]);
                Utils.putIfNotExist(resultSongList, tempSongs);
            }
        }
        return resultSongList;
    }

    public List<Album> getAlbums() {
        List<Album> resultAlbumList = new LinkedList<>();
        Album[] tempAlbums;
        for (Musician musician : musiciansList) {
            tempAlbums = musician.getAlbumList().toArray(new Album[musician.getAlbumList().size()]);
            Utils.putIfNotExist(resultAlbumList, tempAlbums);
        }
        return resultAlbumList;
    }

    public List<Album> getAlbumsForSong(Composition song) {
        List<Album> resultAlbumList = getAlbums().stream().
                filter(album -> song.getAlbumsID().contains(album.getId())).
                collect(Collectors.toList());
        return resultAlbumList;
    }

    public List<Musician> getMusicianForAlbum(Album album) {
        List<Musician> resultMusicianList = musiciansList.stream().
                filter(musician -> album.getMusiciansID().contains(musician.getId())).
                collect(Collectors.toList());
        return resultMusicianList;
    }
}