package com.epam.trenings.io;

import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

import java.io.*;

import static com.epam.trenings.Utils.*;


/**
 * Created by pava0715 on 01.06.2016.
 */
public class TextTypeLoader implements IExportImport {
    @Override
    public Handbook load(String path) {
        Handbook resultHandbook = new Handbook();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lineFromFile;
            Composition lastComposition = null;
            Album lastAlbum = null;
            Musician lastMusician;

            while ((lineFromFile = reader.readLine()) != null) {
                lineFromFile = lineFromFile.replaceAll(PREF, EMPTY_STRING).trim();
                String typeFromLine = getTypeFromTextView(lineFromFile);
                String lineWithProperty = getLineWithProperty(lineFromFile);
                switch (typeFromLine) {
                    case COMPOSITION:
                        lastComposition = getCompositionFromString(lineWithProperty);
                        putIfNotExist(resultHandbook.compositionList, lastComposition);
                        break;
                    case ALBUM:
                        if (lastComposition != null) {
                            lastAlbum = getAlbumFromString(lineWithProperty);
                            putIfNotExist(resultHandbook.albumList, lastAlbum);
                            getByName(resultHandbook.albumList, lastAlbum.getName()).addComposition(lastComposition);
                        }
                        break;
                    case MUSICIAN:
                        if (!resultHandbook.albumList.isEmpty()) {
                            lastMusician = getMusicianFromString(lineWithProperty);
                            putIfNotExist(resultHandbook.musiciansList, lastMusician);
                            getByName(resultHandbook.musiciansList, lastMusician.getName()).addAlbums(lastAlbum);
                        }
                        break;
                }
            }
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File " + path + " not found in root project directory.");
            fileNotFoundException.printStackTrace();
        } catch (IOException exceptionIO) {
            System.out.println("Input/output exception when try load object.");
            exceptionIO.printStackTrace();
        }
        return resultHandbook;
    }

    @Override
    public void save(Handbook handbookForExport, String path) {
        try {
            FileOutputStream fileOutStream = new FileOutputStream(path);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            for (Composition song : handbookForExport.compositionList) {
                getStringFromNamed(bufferedWriter, song, EOL);
                for (Album album : song.getAlbumList()) {
                    getStringFromNamed(bufferedWriter, album, EOL, PREF);
                    for (Musician musician : album.getMusicianList()) {
                        getStringFromNamed(bufferedWriter, musician, EOL, DOUBLE_PREF);
                    }
                }
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File " + path + " not found in root project directory.");
            fileNotFoundException.printStackTrace();
        } catch (IOException exceptionIO) {
            System.out.println("Input/output exception when try load object.");
            exceptionIO.printStackTrace();
        }
    }
}