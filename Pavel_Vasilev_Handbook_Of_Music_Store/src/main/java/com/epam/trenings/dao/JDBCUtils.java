package com.epam.trenings.dao;

import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.INamed;
import com.epam.trenings.model.Musician;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 1 on 13.06.2016.
 */
public class JDBCUtils {
    public static final String TABLE_MUSICIAN = "musicians";
    public static final String TABLE_ALBUM = "albums";
    public static final String TABLE_SONG = "compositions";
    public static final String TABLE_MUSICIAN_ALBUM = "musician_albums";
    public static final String TABLE_ALBUM_SONG = "album_songs";
    public static final String NAME = "name";
    public static final String LENGTH = "length";
    public static final String GENRE = "genre";
    public static final String MUSICIAN_ID = "musician_id";
    public static final String ALBUM_ID = "album_id";
    public static final String SONG_ID = "composition_id";


    public static void insertForMusician(Musician musicianForWrite, Connection connection) {
        try {
            String request = "INSERT INTO " + TABLE_MUSICIAN + "("
                    + MUSICIAN_ID + ", " + NAME
                    + ") VALUES (?, ?);";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, musicianForWrite.getId());
            statement.setString(2, musicianForWrite.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertForAlbum(Album albumForWrite, Connection connection) {
        try {
            String request = "INSERT INTO " + TABLE_ALBUM + "("
                    + ALBUM_ID + ", " + NAME + ", " + GENRE
                    + ") VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, albumForWrite.getId());
            statement.setString(2, albumForWrite.getName());
            statement.setString(3, albumForWrite.getGenre());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertForComposition(Composition songForWrite, Connection connection) {
        try {
            String request = "INSERT INTO " + TABLE_SONG + "("
                    + SONG_ID + ", " + NAME + ", " + LENGTH
                    + ") VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, songForWrite.getId());
            statement.setString(2, songForWrite.getName());
            statement.setLong(3, songForWrite.getLength());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateForMusician(Musician musicianForWrite, Connection connection) {
        try {
            String request = "UPDATE " + TABLE_MUSICIAN
                    + " SET " + NAME + " = ? "
                    + "WHERE " + MUSICIAN_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setString(1, musicianForWrite.getName());
            statement.setInt(2, musicianForWrite.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateForAlbum(Album albumForWrite, Connection connection) {
        try {
            String request = "UPDATE " + TABLE_ALBUM
                    + " SET " + NAME + " = ?, " + GENRE + " = ?"
                    + " WHERE " + ALBUM_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setString(1, albumForWrite.getName());
            statement.setString(2, albumForWrite.getGenre());
            statement.setInt(3, albumForWrite.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateForComposition(Composition songForWrite, Connection connection) {
        try {
            String request = "UPDATE " + TABLE_SONG
                    + " SET " + NAME + " = ?, " + LENGTH + " = ?"
                    + " WHERE " + SONG_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setString(1, songForWrite.getName());
            statement.setLong(2, songForWrite.getLength());
            statement.setInt(3, songForWrite.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkIfExists(INamed objectWithID, String tableName, String primaryKey, Connection connection) {
        Boolean resultResponse = false;
        try {
            String request = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, objectWithID.getId());
            ResultSet resultSet = statement.executeQuery();
            resultResponse = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultResponse;
    }

    public static void writeAlbumsDependency(Integer masterID, Integer slaveID, Connection connection) {
        try {
            String request = "SELECT * FROM " + TABLE_MUSICIAN_ALBUM
                    + " WHERE " + MUSICIAN_ID + " = ? "
                    + " AND " + ALBUM_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, masterID);
            statement.setInt(2, slaveID);
            ResultSet resultSet = statement.executeQuery();
            Boolean isExist = resultSet.isBeforeFirst();
            if (!isExist) {
                request = "INSERT INTO " + TABLE_MUSICIAN_ALBUM + "("
                        + MUSICIAN_ID + ", " + ALBUM_ID
                        + ") VALUES (?, ?)";
                statement = connection.prepareCall(request);
                statement.setInt(1, masterID);
                statement.setInt(2, slaveID);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeSongDependency(Integer masterID, Integer slaveID, Connection connection) {
        try {
            String request = "SELECT * FROM " + TABLE_ALBUM_SONG
                    + " WHERE " + ALBUM_ID + " = ? "
                    + " AND " + SONG_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, masterID);
            statement.setInt(2, slaveID);
            ResultSet resultSet = statement.executeQuery();
            Boolean isExist = resultSet.isBeforeFirst();
            if (!isExist) {
                request = "INSERT INTO " + TABLE_ALBUM_SONG + "("
                        + ALBUM_ID + "," + SONG_ID
                        + ") VALUES (?, ?)";
                statement = connection.prepareCall(request);
                statement.setInt(1, masterID);
                statement.setInt(2, slaveID);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertOrUpdateAlbums(Musician parentMusician, Connection connection) {
        Boolean alreadyExist;
        for (Album currentAlbum : parentMusician.getAlbumList()) {
            alreadyExist = checkIfExists(currentAlbum, TABLE_ALBUM, ALBUM_ID, connection);
            if (alreadyExist) {
                updateForAlbum(currentAlbum, connection);
            } else {
                insertForAlbum(currentAlbum, connection);
            }
            writeAlbumsDependency(parentMusician.getId(), currentAlbum.getId(), connection);
            insertOrUpdateSongs(currentAlbum, connection);
        }
    }

    public static void insertOrUpdateSongs(Album parentAlbum, Connection connection) {
        Boolean alreadyExist;
        for (Composition currentSong : parentAlbum.getCompositionList()) {
            alreadyExist = checkIfExists(currentSong, TABLE_SONG, SONG_ID, connection);
            if (alreadyExist) {
                updateForComposition(currentSong, connection);
            } else {
                insertForComposition(currentSong, connection);
            }
            writeSongDependency(parentAlbum.getId(), currentSong.getId(), connection);
        }
    }
}