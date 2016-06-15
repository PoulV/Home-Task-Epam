package com.epam.trenings.dao;

import com.epam.trenings.Utils;
import com.epam.trenings.model.INamed;

/**
 * Created by 1 on 15.06.2016.
 */
public class SQLQueries {
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

    private static String prefix;
    private static StringBuilder queryBuilder = new StringBuilder();
    private static StringBuilder interrogationsBuilder = new StringBuilder();

    public static final String SELECT_MUSICIAN_BY_ID = getQuerySelect(TABLE_MUSICIAN, MUSICIAN_ID);
    public static final String SELECT_ALL_MUSICIANS = getQuerySelectAll(TABLE_MUSICIAN);
    public static final String SELECT_ALL_ALBUM_BY_MUSICIAN_ID = getQuerySelectAllForParentByID(TABLE_ALBUM, ALBUM_ID,
            TABLE_MUSICIAN_ALBUM, MUSICIAN_ID, NAME, GENRE);
    public static final String SELECT_ALL_SONG_BY_ALBUM_ID = getQuerySelectAllForParentByID(TABLE_SONG, SONG_ID,
            TABLE_ALBUM_SONG, ALBUM_ID, NAME, LENGTH);

    public static final String INSERT_MUSICIAN = getQueryInsert(TABLE_MUSICIAN, NAME, MUSICIAN_ID);
    public static final String INSERT_ALBUM = getQueryInsert(TABLE_ALBUM, NAME, GENRE, ALBUM_ID);
    public static final String INSERT_SONG = getQueryInsert(TABLE_SONG, NAME, LENGTH, SONG_ID);
    public static final String INSERT_MUSICIAN_ALBUM = getQueryInsert(TABLE_MUSICIAN_ALBUM, MUSICIAN_ID, ALBUM_ID);
    public static final String INSERT_ALBUM_SONG = getQueryInsert(TABLE_ALBUM_SONG, ALBUM_ID, SONG_ID);

    public static final String UPDATE_MUSICIAN_BY_ID = getQueryUpdateByID(TABLE_MUSICIAN, MUSICIAN_ID, NAME);
    public static final String UPDATE_ALBUM_BY_ID = getQueryUpdateByID(TABLE_ALBUM, ALBUM_ID, NAME, GENRE);
    public static final String UPDATE_SONG_BY_ID = getQueryUpdateByID(TABLE_SONG, SONG_ID, NAME, LENGTH);

    public static final String DELETE_MUSICIAN_DEPENDENCY_BY_ID = getQueryDeleteByID(TABLE_MUSICIAN_ALBUM, MUSICIAN_ID);
    public static final String DELETE_MUSICIAN_BY_ID = getQueryDeleteByID(TABLE_MUSICIAN, MUSICIAN_ID);


    public static String getQuerySelectAll(String table) {
        queryBuilder.setLength(0);
        queryBuilder.append("SELECT * FROM ").append(table);
        return queryBuilder.toString();
    }

    public static String getQuerySelect(String table, String... selectedParams) {
        queryBuilder.setLength(0);
        prefix = "";
        queryBuilder.append("SELECT * FROM ").append(table).append(" WHERE ");
        for (String param : selectedParams) {
            queryBuilder.append(prefix).append(param).append(" = ?");
            prefix = " AND ";
        }
        queryBuilder.append(";");
        return queryBuilder.toString();
    }

    public static String getQueryDeleteByID(String table, String id) {
        queryBuilder.setLength(0);
        queryBuilder.append("DELETE FROM ").append(table)
                .append(" WHERE ").append(id).append(" = ?");
        return queryBuilder.toString();
    }

    public static String getQuerySelectAllForParentByID(String table, String id,
                                                        String otherTable, String otherID,
                                                        String... selectedParams) {
        queryBuilder.setLength(0);
        queryBuilder.append("SELECT ").append(table).append(".").append(id);
        for (String param : selectedParams) {
            queryBuilder.append(", ").append(param);
        }
        queryBuilder.append(" FROM ").append(table).append(", ").append(otherTable)
                .append(" WHERE ").append(table).append(".").append(id)
                .append(" = ").append(otherTable).append(".").append(id)
                .append(" AND ").append(otherTable).append(".").append(otherID)
                .append(" = ?");

        return queryBuilder.toString();
    }

    public static String getQueryInsert(String table, String... insertedParams) {
        String prefix = "";
        queryBuilder.setLength(0);
        interrogationsBuilder.setLength(0);

        queryBuilder.append("INSERT INTO ").append(table).append("(");
        for (String param : insertedParams) {
            queryBuilder.append(prefix).append(param);
            interrogationsBuilder.append(prefix).append("?");
            prefix = ", ";
        }
        queryBuilder.append(") VALUES (").append(interrogationsBuilder.toString()).append(");");
        return queryBuilder.toString();
    }

    public static String getQueryUpdateByID(String table, String id, String... updatedParams) {
        String prefix = "";
        queryBuilder.setLength(0);

        queryBuilder.append("UPDATE ").append(table).append(" SET ");
        for (String param : updatedParams) {
            queryBuilder.append(prefix).append(param).append(" = ?");
            prefix = ", ";
        }
        queryBuilder.append(" WHERE ").append(id).append(" = ?");
        return queryBuilder.toString();
    }

    public static <NAMED extends INamed> String getNameOfPrimaryKey(NAMED objectWithID) {
        switch (objectWithID.getClass().getSimpleName()) {
            case Utils.MUSICIAN:
                return MUSICIAN_ID;
            case Utils.ALBUM:
                return ALBUM_ID;
            case Utils.COMPOSITION:
                return SONG_ID;
            default:
                return "Unknown";
        }

    }

}
