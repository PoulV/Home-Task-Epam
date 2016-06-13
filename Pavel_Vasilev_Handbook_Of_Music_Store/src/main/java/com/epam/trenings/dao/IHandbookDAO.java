package com.epam.trenings.dao;

import com.epam.trenings.model.Musician;

import java.util.List;

/**
 * Created by Pol on 6/12/2016.
 */
public interface IHandbookDAO {
    Musician getMusicianByID(Integer id);
    List<Musician> getAllMusician();
    void insertMusician(Musician musicianForImport);
    void deleteMusician(Integer id);
    void updateMusician(Musician musicianForUpdate);
    Long getSumLength(Integer musicianID);
}
