package com.epam.trenings.dao;

import com.epam.trenings.Utils;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Musician;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.epam.trenings.Utils.clearLists;
import static com.epam.trenings.Utils.getByID;
import static com.epam.trenings.dao.XMLUtils.*;

/**
 * Created by Pol on 6/12/2016.
 */
public class XMLHandbookDAO implements IHandbookDAO {
    private String path;
    private Document doc;
    private List<Album> totalAlbumList = new LinkedList<>();
    private List<Composition> totalSongList = new LinkedList<>();
    private List<Musician> totalMusicianList = new LinkedList<>();

    public XMLHandbookDAO(String path) {
        this.path = path;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(new File(path));
            doc.getDocumentElement().normalize();
        } catch (SAXException e) {
            System.out.println("SAXException executed when try parse xml file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException executed when try read xml file");
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException executed when try parse xml file");
            e.printStackTrace();
        }
    }

    @Override
    public Musician getMusicianByID(Integer id) {
        clearLists(totalMusicianList, totalAlbumList, totalSongList);

        NodeList listMusicianNode = doc.getElementsByTagName(ELEM_MUSICIAN);
        readMusicianFromXml(listMusicianNode, id);

        return getByID(totalMusicianList, id);
    }

    @Override
    public List<Musician> getAllMusician() {
        clearLists(totalMusicianList, totalAlbumList, totalSongList);


        NodeList listMusicianNode = doc.getElementsByTagName(ELEM_MUSICIAN);
        readMusicianFromXml(listMusicianNode);

        return totalMusicianList;
    }

    @Override
    public void insertMusician(Musician musicianForImport) {
        NodeList listMusicianNode = doc.getElementsByTagName(ELEM_MUSICIAN);
        Element elementForInsert = getElementFromMusician(musicianForImport);
        listMusicianNode.item(0).getParentNode()
                .insertBefore(elementForInsert, listMusicianNode.item(0));
        docToFile(doc, path);
    }

    @Override
    public void deleteMusician(Integer id) {
        NodeList listMusicianNode = doc.getElementsByTagName(ELEM_MUSICIAN);
        for (int musicianNum = 0; musicianNum < listMusicianNode.getLength(); musicianNum++) {
            Node musicianNode = listMusicianNode.item(musicianNum);
            if (musicianNode.getNodeType() == Node.ELEMENT_NODE) {
                Element musicianElement = (Element) musicianNode;
                if (musicianElement.getAttribute(PARAM_ID).equals(id.toString())) {
                    musicianElement.getParentNode().removeChild(musicianElement);
                }
            }
        }
        docToFile(doc, path);
    }

    @Override
    public void updateMusician(Musician musicianForUpdate) {
        deleteMusician(musicianForUpdate.getId());
        insertMusician(musicianForUpdate);
    }

    public Long getSumLength(Integer musicianID) {
        Long resultSum = 0L;
        String tempID, songLength;

        NodeList listAlbumNode, listSongNode;

        NodeList listMusicianNode = doc.getElementsByTagName(ELEM_MUSICIAN);
        for (int musicianNum = 0; musicianNum < listMusicianNode.getLength(); musicianNum++) {
            Element musicianElement = (Element) listMusicianNode.item(musicianNum);
            tempID = musicianElement.getAttribute(PARAM_ID);
            if (tempID.equals(musicianID.toString())) {
                listAlbumNode = musicianElement.getElementsByTagName(ELEM_ALBUM);
                for (int albumNum = 0; albumNum < listAlbumNode.getLength(); albumNum++) {
                    Element albumElement = (Element) listAlbumNode.item(albumNum);
                    listSongNode = albumElement.getElementsByTagName(ELEM_COMPOSITION);
                    for (int songNum = 0; songNum < listSongNode.getLength(); songNum++) {
                        Element songElement = (Element) listSongNode.item(songNum);
                        songLength = songElement.getElementsByTagName(PARAM_LENGTH).item(0).getTextContent();
                        resultSum += Long.parseLong(songLength);
                    }
                }

            }
        }
        return resultSum;
    }

    private void readMusicianFromXml(NodeList listMusicianNode) {
        readMusicianFromXml(listMusicianNode, null);
    }

    private void readMusicianFromXml(NodeList listMusicianNode, Integer id) {
        String musicianName, musicianID;
        Musician tempMusician;
        NodeList listAlbumNode;

        for (int musicianNum = 0; musicianNum < listMusicianNode.getLength(); musicianNum++) {
            Node musicianNode = listMusicianNode.item(musicianNum);
            if (musicianNode.getNodeType() == Node.ELEMENT_NODE) {
                Element musicianElement = (Element) musicianNode;
                musicianID = musicianElement.getAttribute(PARAM_ID);
                if ((id != null) && (!musicianID.equals(id.toString()))) {
                    continue;
                }

                musicianName = musicianElement.getElementsByTagName(PARAM_NAME).item(0).getTextContent();
                tempMusician = new Musician(musicianName);

                tempMusician.setId(Integer.parseInt(musicianID));

                listAlbumNode = musicianElement.getElementsByTagName(ELEM_ALBUM);
                readAlbumFromXml(listAlbumNode, tempMusician);

                totalMusicianList.add(tempMusician);
            }
        }
    }

    private void readAlbumFromXml(NodeList listAlbumNode, Musician parentMusician) {
        String albumID, albumName, albumGenre, tempId;
        Album tempAlbum;
        Set<Integer> tempUpperId;
        NodeList listSongNode;

        for (int albumNum = 0; albumNum < listAlbumNode.getLength(); albumNum++) {
            Node albumNode = listAlbumNode.item(albumNum);
            if (albumNode.getNodeType() == Node.ELEMENT_NODE) {
                Element albumElement = (Element) albumNode;
                albumID = albumElement.getAttribute(PARAM_ID);
                albumName = albumElement.getElementsByTagName(PARAM_NAME).item(0).getTextContent();
                albumGenre = albumElement.getElementsByTagName(PARAM_GENRE).item(0).getTextContent();

                tempAlbum = new Album(albumName, albumGenre);
                tempAlbum.setId(Integer.parseInt(albumID));
                NodeList musicianIdNods = albumElement.getElementsByTagName(ELEM_MUSICIAN_ID);
                tempUpperId = new HashSet<>();
                for (int numMusicianID = 0; numMusicianID < musicianIdNods.getLength(); numMusicianID++) {
                    tempId = musicianIdNods.item(numMusicianID).getTextContent();
                    tempUpperId.add(Integer.parseInt(tempId));
                }
                tempAlbum.setMusiciansID(tempUpperId);

                listSongNode = albumElement.getElementsByTagName(ELEM_COMPOSITION);
                readSongsFromXML(listSongNode, tempAlbum);

                totalAlbumList.add(tempAlbum);
                parentMusician.addAlbums(Utils.getByID(totalAlbumList, tempAlbum.getId()));
            }

        }
    }

    private void readSongsFromXML(NodeList listSongNode, Album parentAlbum) {
        String songID, songName, songLength, tempId;
        Composition tempSong;
        Set<Integer> tempUpperId;

        for (int songNum = 0; songNum < listSongNode.getLength(); songNum++) {
            Node songNode = listSongNode.item(songNum);
            if (songNode.getNodeType() == Node.ELEMENT_NODE) {
                Element songElement = (Element) songNode;
                songID = songElement.getAttribute(PARAM_ID);
                songName = songElement.getElementsByTagName(PARAM_NAME).item(0).getTextContent();
                songLength = songElement.getElementsByTagName(PARAM_LENGTH).item(0).getTextContent();

                tempSong = new Composition(songName, Long.parseLong(songLength));
                tempSong.setId(Integer.parseInt(songID));

                NodeList albumIdNods = songElement.getElementsByTagName(ELEM_ALBUM_ID);
                tempUpperId = new HashSet<>();
                for (int numAlbumID = 0; numAlbumID < albumIdNods.getLength(); numAlbumID++) {
                    tempId = albumIdNods.item(numAlbumID).getTextContent();
                    tempUpperId.add(Integer.parseInt(tempId));
                }
                tempSong.setAlbumsID(tempUpperId);

                totalSongList.add(tempSong);
                parentAlbum.addComposition(getByID(totalSongList, tempSong.getId()));
            }
        }
    }

    private Element getElementFromMusician(Musician musician) {
        Element resultElement = doc.createElement(ELEM_MUSICIAN);
        resultElement.setAttribute(PARAM_ID, musician.getId().toString());

        setElementParam(resultElement, PARAM_NAME, musician.getName());
        Element tempAlbumElement = getElementsFromAlbums(musician.getAlbumList());
        resultElement.appendChild(tempAlbumElement);
        return resultElement;
    }

    private Element getElementsFromAlbums(List<Album> albumList) {
        Element resultElement = doc.createElement(ELEM_ALBUMS);
        Element tempAlbumElement;
        Element tempSongsElement;

        for (Album currentAlbum : albumList) {
            tempAlbumElement = doc.createElement(ELEM_ALBUM);
            tempAlbumElement.setAttribute(PARAM_ID, currentAlbum.getId().toString());

            setElementParam(tempAlbumElement, PARAM_NAME, currentAlbum.getName());
            setElementParam(tempAlbumElement, PARAM_GENRE, currentAlbum.getGenre());
            setGroupIntegerParam(tempAlbumElement, ELEM_MUSICIANS_ID, ELEM_MUSICIAN_ID, currentAlbum.getMusiciansID());

            tempSongsElement = getElementsFromSongs(currentAlbum.getCompositionList());
            tempAlbumElement.appendChild(tempSongsElement);

            resultElement.appendChild(tempAlbumElement);
        }
        return resultElement;

    }

    private Element getElementsFromSongs(List<Composition> songList) {
        Element resultElement = doc.createElement(ELEM_COMPOSITIONS);
        Element tempSongElement;

        for (Composition currentSong : songList) {
            tempSongElement = doc.createElement(ELEM_COMPOSITION);
            tempSongElement.setAttribute(PARAM_ID, currentSong.getId().toString());

            setElementParam(tempSongElement, PARAM_NAME, currentSong.getName());
            setElementParam(tempSongElement, PARAM_LENGTH, currentSong.getLength().toString());
            setGroupIntegerParam(tempSongElement, ELEM_ALBUMS_ID, ELEM_ALBUM_ID, currentSong.getAlbumsID());

            resultElement.appendChild(tempSongElement);
        }
        return resultElement;
    }

    private void setGroupIntegerParam(Element currentElement, String groupName, String numName,
                                      Collection<Integer> numsForWrite) {
        Element tempParamElement = doc.createElement(groupName);
        for (Integer currentNum : numsForWrite) {
            setElementParam(tempParamElement, numName, currentNum.toString());
        }
        currentElement.appendChild(tempParamElement);
    }

    private void setElementParam(Element currentElement, String paramName, String paramValue) {
        Element tempParamElement;
        Text tempTextValue;

        tempParamElement = doc.createElement(paramName);
        tempTextValue = doc.createTextNode(paramValue);
        tempParamElement.appendChild(tempTextValue);
        currentElement.appendChild(tempParamElement);
    }


}
