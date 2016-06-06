package com.epam.trenings.io;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

            Composition tempComposition = null;
            String tempCompositionName;
            Long tempCompositionLength;

            Album tempAlbum = null;
            Map<String, Album> tempAlbums = new HashMap<>();
            String tempAlbumName;
            String tempAlbumGenre;
            Map<String, Musician> tempMusicians = new HashMap<>();
            String tempMusicianName;
            Musician tempMusician;

            while ((lineFromFile = reader.readLine()) != null) {
                lineFromFile = lineFromFile.replaceAll("/t", "").trim();
                lineOfValues = lineFromFile.split("-")[1];

                switch (lineFromFile.split("-")[0]) {
                    case "Composition":
                        tempCompositionName = lineOfValues.split(",")[0].split("=")[1];
                        tempCompositionLength = Utils.getMilliseconds(lineOfValues.split(",")[1].split("=")[1]);
                        tempComposition = new Composition(tempCompositionName, tempCompositionLength);
                        break;
                    case "Album":
                        if (tempComposition != null) {
                            tempAlbumName = lineOfValues.split(",")[0].split("=")[1];
                            tempAlbumGenre = lineOfValues.split(",")[1].split("=")[1];
                            if (tempAlbums.containsKey(tempAlbumName)) {
                                tempAlbum = tempAlbums.get(tempAlbumName);
                            } else {
                                tempAlbum = new Album(tempAlbumName, tempAlbumGenre);
                            }
                            tempAlbum.addComposition(tempComposition);
                            tempAlbums.put(tempAlbum.getName(), tempAlbum);
                        }
                        break;
                    case "Musician":
                        if (!tempAlbums.isEmpty()) {
                            tempMusicianName = lineOfValues.split(",")[0].split("=")[1];
                            if (tempMusicians.containsKey(tempMusicianName)) {
                                tempMusician = tempMusicians.get(tempMusicianName);
                            } else {
                                tempMusician = new Musician(tempMusicianName);
                            }
                            tempMusician.addAlbum(tempAlbum);
                            tempMusicians.put(tempMusician.getName(), tempMusician);
                        }
                        break;
                }
            }
            if (!tempMusicians.isEmpty()) {
                resultHandbook.addMusician(tempMusicians.values().toArray(new Musician[tempMusicians.size()]));
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
    public void save(Handbook objectToImport, String path) {
        try {
            FileOutputStream fileOutStream = new FileOutputStream(path);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            Musician currentMusician;
            Album currentAlbum;
            Map<String, Composition> mapOfAllCompositions = new HashMap<>();
            for (Map.Entry<String, Musician> musicianEntry : objectToImport.mapOfMusicians.entrySet()) {
                currentMusician = musicianEntry.getValue();
                for (Map.Entry<String, Album> albumEntry : currentMusician.getAlbumMap().entrySet()) {
                    currentAlbum = albumEntry.getValue();
                    mapOfAllCompositions.putAll(currentAlbum.getCompositionMap());
                }
            }

            Composition currentComposition;
            for (Map.Entry<String, Composition> compositionEntry : mapOfAllCompositions.entrySet()) {
                currentComposition = compositionEntry.getValue();
                bufferedWriter.append(currentComposition.toString()).append("\r\n");
                for (Map.Entry<String, Album> albumEntry : currentComposition.getAlbumMap().entrySet()) {
                    currentAlbum = albumEntry.getValue();
                    bufferedWriter.append("\t").append(currentAlbum.toString()).append("\r\n");
                    for (Map.Entry<String, Musician> musicianEntry : currentAlbum.getMusicianMap().entrySet()) {
                        currentMusician = musicianEntry.getValue();
                        bufferedWriter.append("\t\t").append(currentMusician.toString()).append("\r\n");
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
