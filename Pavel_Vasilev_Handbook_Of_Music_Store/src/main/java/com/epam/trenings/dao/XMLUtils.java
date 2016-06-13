package com.epam.trenings.dao;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Pol on 6/12/2016.
 */
public class XMLUtils {
    public static final String PARAM_ID = "id";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_GENRE = "genre";
    public static final String PARAM_LENGTH = "length";
    public static final String ELEM_MUSICIAN = "musician";
    public static final String ELEM_MUSICIANS_ID = "musiciansID";
    public static final String ELEM_MUSICIAN_ID = "musician_id";
    public static final String ELEM_ALBUM = "album";
    public static final String ELEM_ALBUMS = "albumList";
    public static final String ELEM_ALBUMS_ID = "albumList";
    public static final String ELEM_ALBUM_ID = "album_id";
    public static final String ELEM_COMPOSITION = "composition";
    public static final String ELEM_COMPOSITIONS = "compositionList";

    public static void docToFile(Document doc, String path) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            System.out.println("Exception executed when try write DOM document to xml file");
            e.printStackTrace();
        }
    }
}
