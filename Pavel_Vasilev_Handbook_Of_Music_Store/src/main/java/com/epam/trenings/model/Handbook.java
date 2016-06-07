package com.epam.trenings.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Handbook implements Serializable {
    public List<Musician> musiciansList = new LinkedList<>();
    public List<Album> albumList = new LinkedList<>();
    public List<Composition> compositionList = new LinkedList<>();

    public Handbook() {

    }
}