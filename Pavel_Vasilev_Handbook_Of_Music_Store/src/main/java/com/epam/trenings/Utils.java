package com.epam.trenings;

import com.epam.trenings.io.IExportImport;
import com.epam.trenings.model.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Pol on 6/6/2016.
 */
public class Utils {
    public static final String TYPE_NAME_SEPARATOR = ": ";
    public static final String FIELD_VALUE_SEPARATOR = " = ";
    public static final String FIELD_SEPARATOR = ", ";
    public static final String ALBUM = "Album";
    public static final String MUSICIAN = "Musician";
    public static final String COMPOSITION = "Composition";
    public static final String NAME = "name";
    public static final String GENRE = "genre";
    public static final String LENGTH = "length";
    public static final String EOL = "\r\n";
    public static final String EMPTY_STRING = "";
    public static final String PREF = "\t";
    public static final String DOUBLE_PREF = PREF + PREF;
    public static final Integer ZERO = 0;
    public static final Integer FIRST = 1;


    public static void printHandbook(String header, Handbook handbookForPrint) {
        System.out.println(header);

        StringBuffer handbookAsText = new StringBuffer();

        for (Musician musician : handbookForPrint.musiciansList) {
            getStringFromNamed(handbookAsText, musician, EOL);
            for (Album album : musician.getAlbumList()) {
                getStringFromNamed(handbookAsText, album, EOL, PREF);
                for (Composition song : album.getCompositionList()) {
                    getStringFromNamed(handbookAsText, song, EOL, DOUBLE_PREF);
                }
            }
        }
        System.out.println(handbookAsText.toString());
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
                    appendName(buffer, namedForWrite.getName(), FIELD_SEPARATOR);
                    appendProperty(buffer, LENGTH, getReadableTime(((Composition) namedForWrite).getLength()), endOfLine);
                    break;
                case ALBUM:
                    buffer.append(prefix).append(ALBUM).append(TYPE_NAME_SEPARATOR);
                    appendName(buffer, namedForWrite.getName(), FIELD_SEPARATOR);
                    appendProperty(buffer, GENRE, ((Album) namedForWrite).getGenre(), endOfLine);
                    break;
                case MUSICIAN:
                    buffer.append(prefix).append(MUSICIAN).append(TYPE_NAME_SEPARATOR);
                    appendName(buffer, namedForWrite.getName(), EOL);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Input/output exception when create Named as string");
            e.printStackTrace();
        }
    }

    private static void appendName(Appendable buffer, String name, String end) {
        try {
            buffer.append(NAME).append(FIELD_VALUE_SEPARATOR).append(name).append(end);
        } catch (IOException e) {
            System.out.println("Input/output exception when create Named as string");
            e.printStackTrace();
        }
    }

    private static void appendProperty(Appendable buffer, String propertyName, String propertyValue, String end) {
        try {
            buffer.append(propertyName).append(FIELD_VALUE_SEPARATOR).append(propertyValue).append(end);
        } catch (IOException e) {
            System.out.println("Input/output exception when create Named as string");
            e.printStackTrace();
        }
    }

    public static Composition getCompositionFromString(String stringForParse) {
        String tempName = getPropertyValue(stringForParse, Utils.ZERO);
        Long tempCompositionLength = Utils.getMilliseconds(getPropertyValue(stringForParse, Utils.FIRST));
        Composition resultComposition = new Composition(tempName, tempCompositionLength);
        return resultComposition;
    }

    public static Album getAlbumFromString(String stringForParse) {
        String tempName = getPropertyValue(stringForParse, Utils.ZERO);
        String tempAlbumGenre = getPropertyValue(stringForParse, Utils.FIRST);
        Album resultAlbum = new Album(tempName, tempAlbumGenre);
        return resultAlbum;
    }

    public static Musician getMusicianFromString(String stringForParse) {
        String tempName = getPropertyValue(stringForParse, Utils.ZERO);
        Musician resultMusician = new Musician(tempName);
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
                isNameExist = nameContainer.getName().equals(objectForInsert.getName());
                if (isNameExist) {
                    break;
                }
            }

            if (!isNameExist) {
                inputList.add(objectForInsert);
            }
        }
    }

    public static <NAMED extends INamed> NAMED getByName(List<NAMED> inputList, String name) {
        NAMED namedObjectForReturn = null;
        for (NAMED namedObject : inputList) {
            if (namedObject.getName().equals(name)) {
                namedObjectForReturn = namedObject;
                break;
            }
        }
        return namedObjectForReturn;
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

        firstAlbum.setMusician(firstMusician, thirdMusician);
        secondAlbum.setMusician(secondMusician, thirdMusician);
        firstAlbum.addComposition(firstComposition, thirdComposition);
        secondAlbum.addComposition(secondComposition, thirdComposition);

        firstComposition.setAlbums(firstAlbum);
        secondComposition.setAlbums(secondAlbum);
        thirdComposition.setAlbums(firstAlbum, secondAlbum);

        putIfNotExist(handbookForReturn.musiciansList, firstMusician, secondMusician, thirdMusician);
        putIfNotExist(handbookForReturn.albumList, firstAlbum, secondAlbum);
        putIfNotExist(handbookForReturn.compositionList, firstComposition, secondComposition, thirdComposition);

        return handbookForReturn;
    }

    public static void presentLoadedHandbook(Handbook handbook, IExportImport loader, String pathToFile) {
        loader.save(handbook, pathToFile);
        Handbook resultHandBook = loader.load(pathToFile);
        Utils.printHandbook("Downloaded by " + loader.getClass().getSimpleName() + ":", resultHandBook);
    }

    public static String getTypeFromTextView(String inputLine) {
        String typeFromLine = inputLine.split(TYPE_NAME_SEPARATOR)[ZERO];
        return typeFromLine;
    }

    public static String getLineWithProperty(String inputLine) {
        String lineWithProperty = inputLine.substring(inputLine.indexOf(TYPE_NAME_SEPARATOR));
        return lineWithProperty;
    }
}