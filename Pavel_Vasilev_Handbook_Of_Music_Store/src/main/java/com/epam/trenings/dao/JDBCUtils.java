package com.epam.trenings.dao;

import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Musician;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.trenings.dao.SQLQueries.*;

/**
 * Created by 1 on 13.06.2016.
 */
public class JDBCUtils {
    private static final Logger logger = Logger.getLogger(JDBCUtils.class);

    public static void printSQLException(String comment, SQLException e) {
        logger.error(comment, e);
        logger.error("SQLState: " + e.getSQLState());
        logger.error("Error Code: " + e.getErrorCode());
        logger.error("Message: " + e.getMessage());

        Throwable t = e.getCause();
        while (t != null) {
            logger.error("Cause: " + t);
            t = t.getCause();
        }
    }

    public static Musician getMusician(ResultSet resultSet) {
        Musician resultMusician = new Musician();
        try {
            resultMusician.setId(resultSet.getInt(MUSICIAN_ID));
            resultMusician.setName(resultSet.getString(NAME));
        } catch (SQLException e) {
            printSQLException("Catch SQLException when parsing resultSet for new Musician", e);
        }
        return resultMusician;
    }

    public static Album getAlbum(ResultSet resultSet) {
        Album resultAlbum = new Album();
        try {
            resultAlbum.setId(resultSet.getInt(ALBUM_ID));
            resultAlbum.setName(resultSet.getString(NAME));
            resultAlbum.setGenre(resultSet.getString(GENRE));
        } catch (SQLException e) {
            printSQLException("Catch SQLException when parsing resultSet for new Album", e);
        }
        return resultAlbum;
    }

    public static Composition getSong(ResultSet resultSet) {
        Composition resultSong = new Composition();
        try {
            resultSong.setId(resultSet.getInt(SONG_ID));
            resultSong.setName(resultSet.getString(NAME));
            resultSong.setLength(resultSet.getLong(LENGTH));
        } catch (SQLException e) {
            printSQLException("Catch SQLException when parsing resultSet for new Composition", e);
        }
        return resultSong;
    }
}