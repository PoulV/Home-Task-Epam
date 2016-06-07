package com.epam.trenings.entities;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Musician;

import java.util.List;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityUtils {
    public static EntityObject getEntityFromComposition(Composition targetSong, StringBuffer buffer) {
        buffer.setLength(Utils.ZERO);
        Utils.writeSongString(buffer, targetSong, Utils.EMPTY_STRING);
        List<String> dependenciesToMusician = Utils.getListOfNames(targetSong.getAlbumList());
        EntityObject resultEntity = new EntityObject(buffer.toString(), dependenciesToMusician, null);
        return resultEntity;
    }

    public static EntityObject getEntityFromAlbum(Album targetAlbum, StringBuffer buffer) {
        buffer.setLength(Utils.ZERO);
        Utils.writeAlbumString(buffer, targetAlbum, Utils.EMPTY_STRING);
        List<String> dependenciesToMusician = Utils.getListOfNames(targetAlbum.getMusicianList());
        List<String> dependenciesToSong = Utils.getListOfNames(targetAlbum.getCompositionList());
        EntityObject resultEntity = new EntityObject(buffer.toString(), dependenciesToMusician, dependenciesToSong);
        return resultEntity;
    }

    public static EntityObject getEntityFromMusician(Musician targetMusician, StringBuffer buffer) {
        buffer.setLength(Utils.ZERO);
        Utils.writeMusicianString(buffer, targetMusician, Utils.EMPTY_STRING);
        List<String> dependenciesToSong = Utils.getListOfNames(targetMusician.getAlbumList());
        EntityObject resultEntity = new EntityObject(buffer.toString(), null, dependenciesToSong);
        return resultEntity;
    }

    public static Composition getSongFromEntity(EntityObject entity){
        return null;
    }

    public static Album getAlbunFromEntity(EntityObject entity){
        return null;
    }
    public static Musician getMusicianFromEntity(EntityObject entity){
        return null;
    }
}
