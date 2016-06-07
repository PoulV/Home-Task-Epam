package com.epam.trenings.io;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

import java.io.*;


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
            String lineOfValues;

            Composition lastComposition = null;
            Album lastAlbum = null;
            Musician lastMusician;

            while ((lineFromFile = reader.readLine()) != null) {
                lineFromFile = lineFromFile.replaceAll(Utils.PREF, Utils.EMPTY_STRING).trim();
                lineOfValues = lineFromFile.substring(lineFromFile.indexOf(Utils.TYPE_NAME_SEPARATOR));

                switch (lineFromFile.split(Utils.TYPE_NAME_SEPARATOR)[Utils.ZERO]) {
                    case Utils.COMPOSITION:
                        lastComposition = Utils.getCompositionFromString(lineOfValues);
                        Utils.putIfNotExist(resultHandbook.compositionList, lastComposition);
                        break;
                    case Utils.ALBUM:
                        if (lastComposition != null) {
                            lastAlbum = Utils.getAlbumFromString(lineOfValues);
                            Utils.putIfNotExist(resultHandbook.albumList, lastAlbum);
                            Utils.getByName(resultHandbook.albumList, lastAlbum.getName()).addComposition(lastComposition);
                        }
                        break;
                    case Utils.MUSICIAN:
                        if (!resultHandbook.albumList.isEmpty()) {
                            lastMusician = Utils.getMusicianFromString(lineOfValues);
                            Utils.putIfNotExist(resultHandbook.musiciansList, lastMusician);
                            Utils.getByName(resultHandbook.musiciansList, lastMusician.getName()).addAlbums(lastAlbum);
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
    public void save(Handbook objectToExport, String path) {
        try {
            FileOutputStream fileOutStream = new FileOutputStream(path);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            for (Composition song : objectToExport.compositionList) {
                Utils.writeSongString(bufferedWriter, song, Utils.EMPTY_STRING);
                for (Album album : song.getAlbumList()) {
                    Utils.writeAlbumString(bufferedWriter, album, Utils.PREF);
                    for (Musician musician : album.getMusicianList()) {
                        Utils.writeMusicianString(bufferedWriter, musician, Utils.DOUBLE_PREF);
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
