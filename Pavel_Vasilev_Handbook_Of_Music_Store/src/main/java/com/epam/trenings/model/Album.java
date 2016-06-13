package com.epam.trenings.model;

import com.epam.trenings.Utils;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created by pava0715 on 01.06.2016.
 */
@XmlRootElement(name = "albums")
@XmlType(propOrder = {"id", "name", "genre", "compositionList", "musiciansID"})
public class Album implements INamed {
    private Integer id;
    private String name;
    private String genre;
    private List<Composition> compositionList = new LinkedList<>();
    private Set<Integer> musiciansID = new HashSet<>();
    private static Integer count = 0;

    public Album() {
    }

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
        id = ++count;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "composition")
    @XmlElementWrapper
    public void setCompositionList(List<Composition> compositionList) {
        this.compositionList = compositionList;
    }

    public Set<Integer> getMusiciansID() {
        return musiciansID;
    }
    @XmlElement(name = "musician_id")
    @XmlElementWrapper
    public void setMusiciansID(Set<Integer> musiciansID) {
        this.musiciansID = musiciansID;
    }

    @Override
    public Integer getId() {
        return id;
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

    public void appendMusiciansID(Integer... newMusiciansID) {
        this.musiciansID.addAll(Arrays.asList(newMusiciansID));
    }

    public void addComposition(Composition... newCompositions) {
        for (Composition newComposition : newCompositions) {
            newComposition.getAlbumsID().add(id);
            if (!compositionList.contains(newComposition)) {
                Utils.putIfNotExist(compositionList, newComposition);
            }
        }
    }
}