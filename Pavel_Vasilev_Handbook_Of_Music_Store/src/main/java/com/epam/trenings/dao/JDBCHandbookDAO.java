package com.epam.trenings.dao;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.INamed;
import com.epam.trenings.model.Musician;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static com.epam.trenings.dao.JDBCUtils.*;
import static com.epam.trenings.dao.SQLQueries.*;

/**
 * Created by Pol on 6/12/2016.
 */
public class JDBCHandbookDAO implements IHandbookDAO {
    private static final Logger logger = Logger.getLogger(JDBCHandbookDAO.class);

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
            logger.info("Successful connect to db");
        } catch (SQLException e) {
            printSQLException("SQLException executed when try connect to db", e);
        }
    }

    @Override
    public Musician getMusicianByID(Integer id) {
        Utils.clearLists(totalMusicianList, totalAlbumList, totalSongList);
        String request;
        ResultSet musiciansResultSet;

        try {
            request = SELECT_MUSICIAN_BY_ID;
            PreparedStatement statement = connection.prepareCall(request);
            statement.setInt(1, id);
            musiciansResultSet = statement.executeQuery();
            createMusiciansFromResultSet(musiciansResultSet);
        } catch (SQLException e) {
            printSQLException("SQLException executed when try get musician by ID = " + id, e);
        }
        return totalMusicianList.get(id);
    }

    @Override
    public List<Musician> getAllMusician() {
        Utils.clearLists(totalMusicianList, totalAlbumList, totalSongList);
        String request;
        ResultSet musiciansResultSet;

        try {
            request = SELECT_ALL_MUSICIANS;
            PreparedStatement statement = connection.prepareCall(request);
            musiciansResultSet = statement.executeQuery();
            createMusiciansFromResultSet(musiciansResultSet);
        } catch (SQLException e) {
            printSQLException("SQLException executed when try get all musicians", e);
        }
        return totalMusicianList;
    }

    @Override
    public void insertMusician(Musician musicianForWrite) {
        createMusician(INSERT_MUSICIAN, musicianForWrite);
        insertOrUpdateAlbums(musicianForWrite);
    }

    @Override
    public void deleteMusician(Integer id) {
        try {
            PreparedStatement statement = connection.prepareCall(DELETE_MUSICIAN_DEPENDENCY_BY_ID);
            statement.setInt(1, id);
            if (statement.execute()) {
                statement = connection.prepareCall(DELETE_MUSICIAN_BY_ID);
                statement.setInt(1, id);
                statement.execute();
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try delete musician by ID = " + id, e);
        }
    }

    @Override
    public void updateMusician(Musician musicianForUpdate) {
        createMusician(UPDATE_MUSICIAN_BY_ID, musicianForUpdate);
        insertOrUpdateAlbums(musicianForUpdate);
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.info("Connection successfully closed");
        } catch (SQLException e) {
            printSQLException("SQLException executed when try close JDBC connection", e);
        }
    }

    private void createMusiciansFromResultSet(ResultSet musiciansResultSet) {
        Musician tempMusician;
        PreparedStatement statement;
        ResultSet albumResultSet;
        try {
            while (musiciansResultSet.next()) {
                tempMusician = getMusician(musiciansResultSet);
                statement = connection.prepareCall(SELECT_ALL_ALBUM_BY_MUSICIAN_ID);
                statement.setInt(1, tempMusician.getId());
                albumResultSet = statement.executeQuery();
                createAlbumsFromResultSet(albumResultSet, tempMusician);
                Utils.putIfNotExist(totalMusicianList, tempMusician);
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try create musicians by resultSet", e);
        }
    }

    private void createAlbumsFromResultSet(ResultSet albumResultSet, Musician parentMusician) {
        try {
            Album tempAlbum;
            PreparedStatement statement;
            ResultSet songResultSet;
            while (albumResultSet.next()) {
                tempAlbum = getAlbum(albumResultSet);
                statement = connection.prepareCall(SELECT_ALL_SONG_BY_ALBUM_ID);
                statement.setInt(1, tempAlbum.getId());
                songResultSet = statement.executeQuery();
                createSongsFromResultSet(songResultSet, tempAlbum);

                Utils.putIfNotExist(totalAlbumList, tempAlbum);
                tempAlbum = Utils.getByID(totalAlbumList, tempAlbum.getId());
                tempAlbum.appendMusiciansID(parentMusician.getId());
                parentMusician.addAlbums(tempAlbum);
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try create albums by resultSet", e);
        }
    }

    private void createSongsFromResultSet(ResultSet songResultSet, Album parentAlbum) {
        try {
            Composition tempSong;
            while (songResultSet.next()) {
                tempSong = getSong(songResultSet);
                Utils.putIfNotExist(totalSongList, tempSong);
                tempSong = Utils.getByID(totalSongList, tempSong.getId());
                tempSong.appendAlbumsID(parentAlbum.getId());
                parentAlbum.addComposition(tempSong);
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try create compositions by resultSet", e);
        }
    }

    private <NAMED extends INamed> Boolean checkIfExists(String tableName, NAMED... objectsWithID) {
        Boolean resultResponse = false;
        String[] idNames = new String[objectsWithID.length];

        for (int current = 0; current < objectsWithID.length; current++) {
            idNames[current] = getNameOfPrimaryKey(objectsWithID[current]);
        }

        try {
            String request = getQuerySelect(tableName, idNames);
            PreparedStatement statement = connection.prepareCall(request);
            for (int current = 0; current < objectsWithID.length; current++) {
                statement.setInt(current + 1, objectsWithID[current].getId());
            }
            ResultSet resultSet = statement.executeQuery();
            resultResponse = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            printSQLException("SQLException executed when try check verify exist for "
                    + objectsWithID.toString() + " in db", e);
        }
        return resultResponse;
    }

    private <NAMED extends INamed> void writeAlbumsDependency(NAMED musician, NAMED album) {
        try {
            Boolean isExist = checkIfExists(TABLE_MUSICIAN_ALBUM, musician, album);
            if (!isExist) {
                PreparedStatement statement = connection.prepareCall(INSERT_MUSICIAN_ALBUM);
                statement.setInt(1, musician.getId());
                statement.setInt(2, album.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try insert or update dependency for "
                    + "musician with id  = " + musician.getId() + "and album with id = " + album.getId(), e);
        }
    }

    private <NAMED extends INamed> void writeSongDependency(NAMED album, NAMED song) {
        try {
            Boolean isExist = checkIfExists(TABLE_ALBUM_SONG, album, song);
            if (!isExist) {
                PreparedStatement statement = connection.prepareCall(INSERT_ALBUM_SONG);
                statement.setInt(1, album.getId());
                statement.setInt(2, song.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            printSQLException("SQLException executed when try insert or update dependency for "
                    + "album with id  = " + album.getId() + "and song with id = " + song.getId(), e);
        }

    }

    public void insertOrUpdateMusician(Musician musicionForWrite) {
        Boolean alreadyExist;
        alreadyExist = checkIfExists(TABLE_MUSICIAN, musicionForWrite);
        if (alreadyExist) {
            createMusician(UPDATE_MUSICIAN_BY_ID, musicionForWrite);
        } else {
            createMusician(INSERT_MUSICIAN, musicionForWrite);
        }
        insertOrUpdateAlbums(musicionForWrite);
    }

    private void insertOrUpdateAlbums(Musician parentMusician) {
        Boolean alreadyExist;
        for (Album currentAlbum : parentMusician.getAlbumList()) {
            alreadyExist = checkIfExists(TABLE_ALBUM, currentAlbum);
            if (alreadyExist) {
                createAlbum(UPDATE_ALBUM_BY_ID, currentAlbum);
            } else {
                createAlbum(INSERT_ALBUM, currentAlbum);
            }
            writeAlbumsDependency(parentMusician, currentAlbum);
            insertOrUpdateSongs(currentAlbum);
        }
    }

    private void insertOrUpdateSongs(Album parentAlbum) {
        Boolean alreadyExist;
        for (Composition currentSong : parentAlbum.getCompositionList()) {
            alreadyExist = checkIfExists(TABLE_SONG, currentSong);
            if (alreadyExist) {
                createComposition(UPDATE_SONG_BY_ID, currentSong);
            } else {
                createComposition(INSERT_SONG, currentSong);
            }
            writeSongDependency(parentAlbum, currentSong);
        }
    }

    private void createMusician(String insertOrUpdateQuery, Musician musicianForWrite) {
        try {
            PreparedStatement statement = connection.prepareCall(insertOrUpdateQuery);
            statement.setString(1, musicianForWrite.getName());
            statement.setInt(2, musicianForWrite.getId());
            statement.execute();
        } catch (SQLException e) {
            printSQLException("SQLException executed when try insert or update musician(id = "
                    + musicianForWrite.getId() + ")", e);
        }
    }

    private void createAlbum(String insertOrUpdateQuery, Album albumForWrite) {
        try {
            PreparedStatement statement = connection.prepareCall(insertOrUpdateQuery);
            statement.setString(1, albumForWrite.getName());
            statement.setString(2, albumForWrite.getGenre());
            statement.setInt(3, albumForWrite.getId());

            statement.execute();
        } catch (SQLException e) {
            printSQLException("SQLException executed when try insert or update album(id = "
                    + albumForWrite.getId() + ")", e);
        }
    }

    private void createComposition(String insertOrUpdateQuery, Composition songForWrite) {
        try {
            PreparedStatement statement = connection.prepareCall(insertOrUpdateQuery);
            statement.setString(1, songForWrite.getName());
            statement.setLong(2, songForWrite.getLength());
            statement.setInt(3, songForWrite.getId());

            statement.execute();
        } catch (SQLException e) {
            printSQLException("SQLException executed when try update composition(id = "
                    + songForWrite.getId() + ")", e);
        }
    }
}

