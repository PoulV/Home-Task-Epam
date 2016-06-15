package com.epam.trenings;

import com.epam.trenings.io.IExportImport;
import com.epam.trenings.model.*;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pol on 6/6/2016.
 */
public class Utils {
    private static final Logger logger = Logger.getLogger(Utils.class);

    public static final String TYPE_NAME_SEPARATOR = ": ";
    public static final String FIELD_VALUE_SEPARATOR = " = ";
    public static final String FIELD_SEPARATOR = ", ";
    public static final String ALBUM = "Album";
    public static final String MUSICIAN = "Musician";
    public static final String COMPOSITION = "Composition";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String GENRE = "genre";
    public static final String LENGTH = "length";
    public static final String EOL = "\r\n";
    public static final String EMPTY_STRING = "";
    public static final String PREF = "\t";
    public static final String DOUBLE_PREF = PREF + PREF;
    public static final Integer ZERO = 0;
    public static final Integer FIRST = 1;
    public static final Integer SECOND = 2;


    public static void printHandbook(String header, Handbook handbookForPrint) {
        logger.info(header);

        StringBuffer handbookAsText = new StringBuffer();

        for (Musician musician : handbookForPrint.getMusiciansList()) {
            getStringFromNamed(handbookAsText, musician, EOL);
            for (Album album : musician.getAlbumList()) {
                getStringFromNamed(handbookAsText, album, EOL, PREF);
                for (Composition song : album.getCompositionList()) {
                    getStringFromNamed(handbookAsText, song, EOL, DOUBLE_PREF);
                }
            }
        }
        logger.info(handbookAsText.toString());
    }

    public static void getStringFromNamed(Appendable buffer, INamed namedForWrite) {
        getStringFromNamed(buffer, namedForWrite, EMPTY_STRING, EMPTY_STRING);
    }

    public static void getStringFromNamed(Appendable buffer, INamed namedForWrite, String endOfLine) {
        getStringFromNamed(buffer, namedForWrite, endOfLine, EMPTY_STRING);
    }

    public static void getStringFromNamed(Appendable buffer, INamed namedForWrite, String endOfLine, String prefix) {
        try {
            switch (namedForWrite.getClass().getSimpleName()) {
                case COMPOSITION:
                    buffer.append(prefix).append(COMPOSITION).append(TYPE_NAME_SEPARATOR);
                    appendNameAndID(buffer, namedForWrite, FIELD_SEPARATOR);
                    appendProperty(buffer, LENGTH, getReadableTime(((Composition) namedForWrite).getLength()), endOfLine);
                    break;
                case ALBUM:
                    buffer.append(prefix).append(ALBUM).append(TYPE_NAME_SEPARATOR);
                    appendNameAndID(buffer, namedForWrite, FIELD_SEPARATOR);
                    appendProperty(buffer, GENRE, ((Album) namedForWrite).getGenre(), endOfLine);
                    break;
                case MUSICIAN:
                    buffer.append(prefix).append(MUSICIAN).append(TYPE_NAME_SEPARATOR);
                    appendNameAndID(buffer, namedForWrite, EOL);
                    break;
            }
        } catch (IOException e) {
            logger.error("Input/output exception when create Named as string", e);
        }
    }

    private static void appendNameAndID(Appendable buffer, INamed named, String end) {
        try {
            buffer.append(ID).append(FIELD_VALUE_SEPARATOR).append(named.getId().toString()).append(FIELD_SEPARATOR).
                    append(NAME).append(FIELD_VALUE_SEPARATOR).append(named.getName()).append(end);
        } catch (IOException e) {
            logger.error("Input/output exception when create Named as string", e);
        }
    }

    private static void appendProperty(Appendable buffer, String propertyName, String propertyValue, String end) {
        try {
            buffer.append(propertyName).append(FIELD_VALUE_SEPARATOR).append(propertyValue).append(end);
        } catch (IOException e) {
            logger.error("Input/output exception when create Named as string", e);
        }
    }

    public static Composition getCompositionFromString(String stringForParse) {

        Integer id = Integer.parseInt(getPropertyValue(stringForParse, ZERO));
        String tempName = getPropertyValue(stringForParse, FIRST);
        Long tempCompositionLength = Utils.getMilliseconds(getPropertyValue(stringForParse, SECOND));
        Composition resultComposition = new Composition(tempName, tempCompositionLength);
        resultComposition.setId(id);
        return resultComposition;
    }

    public static Album getAlbumFromString(String stringForParse) {
        Integer id = Integer.parseInt(getPropertyValue(stringForParse, ZERO));
        String tempName = getPropertyValue(stringForParse, FIRST);
        String tempAlbumGenre = getPropertyValue(stringForParse, Utils.SECOND);
        Album resultAlbum = new Album(tempName, tempAlbumGenre);
        resultAlbum.setId(id);
        return resultAlbum;
    }

    public static Musician getMusicianFromString(String stringForParse) {
        Integer id = Integer.parseInt(getPropertyValue(stringForParse, ZERO));
        String tempName = getPropertyValue(stringForParse, FIRST);
        Musician resultMusician = new Musician(tempName);
        resultMusician.setId(id);
        return resultMusician;
    }

    private static String getPropertyValue(String propertyString, Integer propertyNumber) {
        String valueOfProperty = propertyString.split(Utils.FIELD_SEPARATOR)[propertyNumber]
                .split(Utils.FIELD_VALUE_SEPARATOR)[FIRST];
        return valueOfProperty;
    }

    public static String getReadableTime(Long milliseconds) {
        Date date = new Date(milliseconds);
        DateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public static Long getMilliseconds(String timeString) {
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            date = sdf.parse("1970-01-01 00:" + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static <NAMED extends INamed> void putIfNotExist(List<NAMED> inputList, NAMED... namedObjects) {
        for (NAMED objectForInsert : namedObjects) {
            boolean isNameExist = false;
            for (NAMED nameContainer : inputList) {
                isNameExist = nameContainer.getId().equals(objectForInsert.getId());
                if (isNameExist) {
                    break;
                }
            }

            if (!isNameExist) {
                inputList.add(objectForInsert);
            }
        }
    }

    public static Handbook getFilledHandbook() {
        Handbook handbookForReturn = new Handbook();

        Musician firstMusician = new Musician("firstSinger");
        Musician secondMusician = new Musician("secondSinger");
        Musician thirdMusician = new Musician("thirdSinger");

        Album firstAlbum = new Album("firstAlbum", "jazz");
        Album secondAlbum = new Album("secondAlbum", "classic");

        Composition firstComposition = new Composition("firstSong", new Long(5 * 60 * 1000 + 132));
        Composition secondComposition = new Composition("secondSong", new Long(6 * 60 * 1000 + 132));
        Composition thirdComposition = new Composition("thirdSong", new Long(10 * 60 * 1000 + 132));

        firstMusician.addAlbums(firstAlbum);
        secondMusician.addAlbums(secondAlbum);
        thirdMusician.addAlbums(firstAlbum, secondAlbum);

        firstAlbum.appendMusiciansID(firstMusician.getId(), thirdMusician.getId());
        secondAlbum.appendMusiciansID(secondMusician.getId(), thirdMusician.getId());
        firstAlbum.addComposition(firstComposition, thirdComposition);
        secondAlbum.addComposition(secondComposition, thirdComposition);

        firstComposition.appendAlbumsID(firstAlbum.getId());
        secondComposition.appendAlbumsID(secondAlbum.getId());
        thirdComposition.appendAlbumsID(firstAlbum.getId(), secondAlbum.getId());

        putIfNotExist(handbookForReturn.getMusiciansList(), firstMusician, secondMusician, thirdMusician);

        return handbookForReturn;
    }


    public static void presentLoadedHandbook(Handbook handbook, IExportImport loader) {
        loader.save(handbook);
        Handbook resultHandBook = loader.load();
        printHandbook("Downloaded by " + loader.getClass().getSimpleName() + ":", resultHandBook);
    }

    public static String getTypeFromTextView(String inputLine) {
        String typeFromLine = inputLine.split(TYPE_NAME_SEPARATOR)[ZERO];
        return typeFromLine;
    }

    public static String getLineWithProperty(String inputLine) {
        String lineWithProperty = inputLine.substring(inputLine.indexOf(TYPE_NAME_SEPARATOR));
        return lineWithProperty;
    }

    public static <WITH_ID extends INamed> WITH_ID getByID(List<WITH_ID> inputList, Integer id) {
        WITH_ID objectWithIdForReturn = null;
        for (WITH_ID objectWithId : inputList) {
            if (objectWithId.getId().equals(id)) {
                objectWithIdForReturn = objectWithId;
                break;
            }
        }
        return objectWithIdForReturn;
    }

    public static void clearLists(List<?>... lists) {
        for (List<?> currentList : lists) {
            currentList.clear();
        }
    }
}