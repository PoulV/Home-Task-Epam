package com.epam.trenings.io;

import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static com.epam.trenings.Utils.*;


/**
 * Created by pava0715 on 01.06.2016.
 */
public class TextTypeLoader implements IExportImport {
    private static final Logger logger = Logger.getLogger(TextTypeLoader.class);
    private String path = "";

    public TextTypeLoader(String path) {
        this.path = path;
    }

    @Override
    public Handbook load() {
        Handbook resultHandbook = new Handbook();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lineFromFile;
            Composition lastComposition = null;
            Album lastAlbum = null;
            Musician lastMusician;
            List<Album> tempListAlbum = new LinkedList<>();

            while ((lineFromFile = reader.readLine()) != null) {
                lineFromFile = lineFromFile.replaceAll(PREF, EMPTY_STRING).trim();
                String typeFromLine = getTypeFromTextView(lineFromFile);
                String lineWithProperty = getLineWithProperty(lineFromFile);
                switch (typeFromLine) {
                    case COMPOSITION:
                        lastComposition = getCompositionFromString(lineWithProperty);
                        break;
                    case ALBUM:
                        if (lastComposition != null) {
                            lastAlbum = getAlbumFromString(lineWithProperty);
                            putIfNotExist(tempListAlbum, lastAlbum);
                            getByID(tempListAlbum, lastAlbum.getId()).addComposition(lastComposition);
                        }
                        break;
                    case MUSICIAN:
                        if (lastAlbum != null) {
                            lastMusician = getMusicianFromString(lineWithProperty);
                            putIfNotExist(resultHandbook.getMusiciansList(), lastMusician);
                            lastMusician.addAlbums(tempListAlbum.toArray(new Album[tempListAlbum.size()]));
                        }
                        break;
                }
            }
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("File " + path + " not found in root project directory.", fileNotFoundException);
        } catch (IOException exceptionIO) {
            logger.error("Input/output exception when try load object.", exceptionIO);
        }
        return resultHandbook;
    }

    @Override
    public void save(Handbook handbookForExport) {
        try {
            FileOutputStream fileOutStream = new FileOutputStream(path);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            for (Composition song : handbookForExport.getSongs()) {
                getStringFromNamed(bufferedWriter, song, EOL);
                for (Album album : handbookForExport.getAlbumsForSong(song)) {
                    getStringFromNamed(bufferedWriter, album, EOL, PREF);
                    for (Musician musician : handbookForExport.getMusicianForAlbum(album)) {
                        getStringFromNamed(bufferedWriter, musician, EOL, DOUBLE_PREF);
                    }
                }
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("File " + path + " not found in root project directory.", fileNotFoundException);
        } catch (IOException exceptionIO) {
            logger.error("Input/output exception when try load object.", exceptionIO);
        }
    }
}