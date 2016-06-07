package com.epam.trenings.entities;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityModel {
    private List<EntityObject> entityHandbook;

    public EntityModel() {
        entityHandbook = new LinkedList<>();
    }

    public void writeToEntity(Handbook readedHandBook) {
        entityHandbook = new LinkedList<>();
        StringBuffer stringViewOfObject = new StringBuffer();

        for (Composition compositionForWrite : readedHandBook.compositionList) {
            entityHandbook.add(EntityUtils.getEntityFromComposition(compositionForWrite, stringViewOfObject));
        }
        for (Album albumForWrite : readedHandBook.albumList) {
            entityHandbook.add(EntityUtils.getEntityFromAlbum(albumForWrite, stringViewOfObject));
        }

        for (Musician musicianForWrite : readedHandBook.musiciansList) {
            entityHandbook.add(EntityUtils.getEntityFromMusician(musicianForWrite, stringViewOfObject));
        }
    }

    public Handbook readFromEntity() {
        return null;
    }


}
