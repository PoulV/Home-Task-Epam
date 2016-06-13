package com.epam.trenings.dao;

import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Musician;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Pol on 6/12/2016.
 */
public class JDBCHandbookDAO implements IHandbookDAO {

    private Connection connection = null;

    public JDBCHandbookDAO() {
        try {
            String urlForConnettion = "jdbc:postgresql://localhost:5432/postgres";

            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "password");
            properties.setProperty("ssl", "false");

            connection = DriverManager.getConnection(urlForConnettion, properties);
            System.out.println("Successful connect to db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Musician getMusicianByID(Integer id) {
        return null;
    }

    @Override
    public List<Musician> getAllMusician() {
        return null;
    }

    @Override
    public void insertMusician(Musician musicianForWrite) {
        insertMusician(musicianForWrite);
        for (Album currentAlbum : musicianForWrite.getAlbumList()) {
            writeAlbum(currentAlbum);
            writeAlbumsDependency(musicianForWrite.getId(), currentAlbum.getId());
            for (Composition currentSong : currentAlbum.getCompositionList()) {
                writeComposition(currentSong);
                writeSongDependency(currentAlbum.getId(), currentSong.getId());
            }
        }
    }

    @Override
    public void deleteMusician(Integer id) {

    }

    @Override
    public void updateMusician(Musician musicianForUpdate) {

    }

    @Override
    public Long getSumLength(Integer musicianID) {
        return null;
    }

    private void insertMusician(Musician musicianForWrite) {
        try {
            String request = "INSERT INTO musicians (musician_id , name) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, musicianForWrite.getId());
            statement.setString(2, musicianForWrite.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAlbum(Album albumForWrite) {
        try {
            String request = "INSERT INTO albums (album_id, name, genre) VALUES (?, ?, ?);";
            //String request = "UPDATE albums SET album_id = ?, name = ?, genre = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, albumForWrite.getId());
            statement.setString(2, albumForWrite.getName());
            statement.setString(3, albumForWrite.getGenre());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertComposition(Composition songForWrite) {
        try {

            String request = "INSERT INTO compositions (composition_id, name, length) VALUES (?, ?, ?)";
            //String request = "UPDATE compositions SET composition_id = ?, name = ?, length = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, songForWrite.getId());
            statement.setString(2, songForWrite.getName());
            statement.setLong(3, songForWrite.getLength());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeAlbumsDependency(Integer musicianID, Integer albumID) {
        try {
            //String request = "INSERT INTO musician_albums (musician_id, album_id) VALUES (?, ?);";
            String request = "UPDATE musician_albums SET musician_id = ?, album_id = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, musicianID);
            statement.setInt(2, albumID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void writeSongDependency(Integer albumID, Integer songID) {
        try {
            //String request = "INSERT INTO album_songs (album_id, composition_id) VALUES (?, ?);";
            String request = "UPDATE album_songs SET album_id = ?, composition_id = ?;";
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, albumID);
            statement.setInt(2, songID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection successfully closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

