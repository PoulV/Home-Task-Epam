package com.epam.trenings.dao;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Musician;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static com.epam.trenings.dao.JDBCUtils.*;

/**
 * Created by Pol on 6/12/2016.
 */
public class JDBCHandbookDAO implements IHandbookDAO {
    private List<Album> totalAlbumList = new LinkedList<>();
    private List<Composition> totalSongList = new LinkedList<>();
    private List<Musician> totalMusicianList = new LinkedList<>();
    private Connection connection = null;

    public JDBCHandbookDAO() {
        try {
            String urlForConnection = "jdbc:postgresql://localhost:5432/postgres";

            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "password");
            properties.setProperty("ssl", "false");

            connection = DriverManager.getConnection(urlForConnection, properties);
            System.out.println("Successful connect to db");
        } catch (SQLException e) {
            System.out.println("SQLException executed when try connect to db");
            e.printStackTrace();
        }
    }

    @Override
    public Musician getMusicianByID(Integer id) {
        Utils.clearLists(totalMusicianList, totalAlbumList, totalSongList);
        String request;
        ResultSet musiciansResultSet;

        try {
            request = "SELECT * FROM " + TABLE_MUSICIAN
                    + " WHERE " + MUSICIAN_ID + " = ?";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, id);
            musiciansResultSet = statement.executeQuery();
            createMusiciansFromResultSet(musiciansResultSet);
        } catch (SQLException e) {
            System.out.println("SQLException executed when try get musician by ID = " + id);
            e.printStackTrace();
        }
        return totalMusicianList.get(id);
    }

    @Override
    public List<Musician> getAllMusician() {
        Utils.clearLists(totalMusicianList, totalAlbumList, totalSongList);
        String request;
        ResultSet musiciansResultSet;

        try {
            request = "SELECT * FROM " + TABLE_MUSICIAN;
            PreparedStatement statement = connection.prepareCall(request);
            musiciansResultSet = statement.executeQuery();
            createMusiciansFromResultSet(musiciansResultSet);
        } catch (SQLException e) {
            System.out.println("SQLException executed when try get all musicians");
            e.printStackTrace();
        }
        return totalMusicianList;
    }

    @Override
    public void insertMusician(Musician musicianForWrite) {
        insertForMusician(musicianForWrite, connection);
        insertOrUpdateAlbums(musicianForWrite, connection);
    }

    @Override
    public void deleteMusician(Integer id) {
        try {
            String request = "DELETE FROM " + TABLE_MUSICIAN_ALBUM
                    + " WHERE " + MUSICIAN_ID + " = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, id);
            if (statement.execute()) {
                request = "DELETE FROM " + TABLE_MUSICIAN
                        + " WHERE " + MUSICIAN_ID + " = ?;";
                statement = connection.prepareCall(request);
                statement.setInt(1, id);
                statement.execute();
            }
        } catch (SQLException e) {
            System.out.println("SQLException executed when try delete musician by ID = " + id);
            e.printStackTrace();
        }
    }

    @Override
    public void updateMusician(Musician musicianForUpdate) {
        updateForMusician(musicianForUpdate, connection);
        insertOrUpdateAlbums(musicianForUpdate, connection);
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection successfully closed");
        } catch (SQLException e) {
            System.out.println("SQLException executed when try close JDBC connection");
            e.printStackTrace();
        }
    }

    private void createMusiciansFromResultSet(ResultSet musiciansResultSet) {
        Musician tempMusician;
        String request;
        PreparedStatement statement;
        ResultSet albumResultSet;
        try {
            while (musiciansResultSet.next()) {
                tempMusician = new Musician(musiciansResultSet.getString(NAME));
                tempMusician.setId(musiciansResultSet.getInt(MUSICIAN_ID));
                request = "SELECT " + TABLE_ALBUM + "." + ALBUM_ID + ", " + NAME + ", " + GENRE
                        + " FROM " + TABLE_MUSICIAN_ALBUM + ", " + TABLE_ALBUM
                        + " WHERE " + TABLE_ALBUM + "." + ALBUM_ID
                        + " = " + TABLE_MUSICIAN_ALBUM + "." + ALBUM_ID
                        + " AND " + TABLE_MUSICIAN_ALBUM + "." + MUSICIAN_ID + " = ?;";
                statement = connection.prepareCall(request);
                statement.setInt(1, tempMusician.getId());
                albumResultSet = statement.executeQuery();
                createAlbumsFromResultSet(albumResultSet, tempMusician);
                Utils.putIfNotExist(totalMusicianList, tempMusician);
            }
        } catch (SQLException e) {
            System.out.println("SQLException executed when try create musicians by resultSet");
            e.printStackTrace();
        }
    }

    private void createAlbumsFromResultSet(ResultSet albumResultSet, Musician parentMusician) {
        try {
            Album tempAlbum;
            String request;
            PreparedStatement statement;
            ResultSet songResultSet;
            while (albumResultSet.next()) {
                tempAlbum = new Album(albumResultSet.getString(NAME), albumResultSet.getString(GENRE));
                tempAlbum.setId(albumResultSet.getInt(ALBUM_ID));
                request = "SELECT " + TABLE_SONG + "." + SONG_ID + ", " + NAME + ", " + LENGTH
                        + " FROM " + TABLE_ALBUM_SONG + ", " + TABLE_SONG
                        + " WHERE " + TABLE_SONG + "." + SONG_ID
                        + " = " + TABLE_ALBUM_SONG + "." + SONG_ID
                        + " AND " + TABLE_ALBUM_SONG + "." + ALBUM_ID + " = ?;";
                statement = connection.prepareCall(request);
                statement.setInt(1, tempAlbum.getId());
                songResultSet = statement.executeQuery();
                createSongsFromResultSet(songResultSet, tempAlbum);

                Utils.putIfNotExist(totalAlbumList, tempAlbum);
                tempAlbum = Utils.getByID(totalAlbumList, tempAlbum.getId());
                tempAlbum.appendMusiciansID(parentMusician.getId());
                parentMusician.addAlbums(tempAlbum);
            }
        } catch (SQLException e) {
            System.out.println("SQLException executed when try create albums by resultSet");
            e.printStackTrace();
        }
    }

    private void createSongsFromResultSet(ResultSet songResultSet, Album parentAlbum) {
        try {
            Composition tempSong;
            while (songResultSet.next()) {
                tempSong = new Composition(songResultSet.getString(NAME), songResultSet.getLong(LENGTH));
                tempSong.setId(songResultSet.getInt(SONG_ID));
                Utils.putIfNotExist(totalSongList, tempSong);
                tempSong = Utils.getByID(totalSongList, tempSong.getId());
                tempSong.appendAlbumsID(parentAlbum.getId());
                parentAlbum.addComposition(tempSong);
            }
        } catch (SQLException e) {
            System.out.println("SQLException executed when try create compositions by resultSet");
            e.printStackTrace();
        }
    }

    public void insertOrUpdateMusicion(Musician musicionForWrite) {
        Boolean alreadyExist;
        alreadyExist = checkIfExists(musicionForWrite, TABLE_MUSICIAN, MUSICIAN_ID, connection);
        if (alreadyExist) {
            updateMusician(musicionForWrite);
        } else {
            insertMusician(musicionForWrite);
        }
    }


}

