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

        for (Composition compositionForWrite : readedHandBook.getSongs()) {
            entityHandbook.add(EntityUtils.getEntityFromNamed(compositionForWrite));
            for (Album albumForWrite : readedHandBook.getAlbumsForSong(compositionForWrite)) {
                entityHandbook.add(EntityUtils.getEntityFromNamed(albumForWrite));
                for (Musician musicianForWrite : readedHandBook.getMusicianForAlbum(albumForWrite)) {
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
        List<Album> tempAlbumList = new LinkedList<>();
        for (EntityObject currentEntity : entityHandbook) {
            switch (currentEntity.getEntityType()) {
                case COMPOSITION:
                    lastSong = Utils.getCompositionFromString(currentEntity.getStringViewOfObject());
                    break;
                case ALBUM:
                    if (lastSong != null) {
                        lastAlbum = Utils.getAlbumFromString(currentEntity.getStringViewOfObject());
                        Utils.putIfNotExist(tempAlbumList, lastAlbum);
                        Utils.getByID(tempAlbumList, lastAlbum.getId()).addComposition(lastSong);
                    }
                    break;
                case MUSICIAN:
                    if (lastAlbum != null) {
                        lastMusician = Utils.getMusicianFromString(currentEntity.getStringViewOfObject());
                        Utils.putIfNotExist(resultHandbook.getMusiciansList(), lastMusician);
                        lastMusician.addAlbums(lastAlbum);
                    }
                    break;
            }
        }
        return resultHandbook;
    }

}