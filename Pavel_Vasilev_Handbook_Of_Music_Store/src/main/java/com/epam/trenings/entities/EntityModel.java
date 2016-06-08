package com.epam.trenings.entities;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityModel implements Serializable {
    private List<EntityObject> entityHandbook;

    public EntityModel() {
        entityHandbook = new LinkedList<>();
    }

    public void fillFrom(Handbook readedHandBook) {
        entityHandbook = new LinkedList<>();

        for (Composition compositionForWrite : readedHandBook.compositionList) {
            entityHandbook.add(EntityUtils.getEntityFromNamed(compositionForWrite));
            for (Album albumForWrite : compositionForWrite.getAlbumList()) {
                entityHandbook.add(EntityUtils.getEntityFromNamed(albumForWrite));
                for (Musician musicianForWrite : albumForWrite.getMusicianList()) {
                    entityHandbook.add(EntityUtils.getEntityFromNamed(musicianForWrite));
                }
            }
        }
    }

    public Handbook getHandbookFromEntity() {
        Handbook resultHandbook = new Handbook();
        Composition lastSong = null;
        Album lastAlbum = null;
        Musician lastMusician;
        for (EntityObject currentEntity : entityHandbook) {
            switch (currentEntity.getEntityType()) {
                case COMPOSITION:
                    lastSong = Utils.getCompositionFromString(currentEntity.getStringViewOfObject());
                    Utils.putIfNotExist(resultHandbook.compositionList, lastSong);
                    break;
                case ALBUM:
                    if (lastSong != null) {
                        lastAlbum = Utils.getAlbumFromString(currentEntity.getStringViewOfObject());
                        Utils.putIfNotExist(resultHandbook.albumList, lastAlbum);
                        Utils.getByName(resultHandbook.albumList, lastAlbum.getName()).addComposition(lastSong);
                    }
                    break;
                case MUSICIAN:
                    if (!resultHandbook.albumList.isEmpty()) {
                        lastMusician = Utils.getMusicianFromString(currentEntity.getStringViewOfObject());
                        Utils.putIfNotExist(resultHandbook.musiciansList, lastMusician);
                        Utils.getByName(resultHandbook.musiciansList, lastMusician.getName()).addAlbums(lastAlbum);
                    }
                    break;
            }
        }
        return resultHandbook;
    }

}