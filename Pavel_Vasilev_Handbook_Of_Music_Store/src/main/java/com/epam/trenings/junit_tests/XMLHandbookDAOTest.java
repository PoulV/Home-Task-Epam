package com.epam.trenings.junit_tests;

import com.epam.trenings.dao.XMLHandbookDAO;
import com.epam.trenings.dao.factory.IAbstractFactoryDAO;
import com.epam.trenings.dao.factory.XMLFactoryDAO;
import com.epam.trenings.io.XMLTypeLoader;
import com.epam.trenings.model.Album;
import com.epam.trenings.model.Composition;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.List;

/**
 * Created by 1 on 15.06.2016.
 */
public class XMLHandbookDAOTest {
    private static final Logger logger = Logger.getLogger(XMLHandbookDAOTest.class);
    private Handbook handbookForTest;
    private final String PATH = "HandBookForTest.xml";
    private XMLHandbookDAO testedDAO;
    private XMLTypeLoader xmlTypeLoader = new XMLTypeLoader(PATH);
    private IAbstractFactoryDAO factoryDAO = new XMLFactoryDAO(PATH);
    private final static String NAME_FOR_TEST = "NAME_FOR_TEST";

    @Before
    public void upDateEnvironment() {
        handbookForTest = com.epam.trenings.Utils.getFilledHandbook();
        xmlTypeLoader.save(handbookForTest);
        testedDAO = (XMLHandbookDAO) factoryDAO.createHandbookDAO();

        logger.info("test environment has been update!");

    }

    @After
    public void after() {
        logger.info("tested");
    }

    @Test
    public void testGetMusicianByID() {
        Musician testedMusician = testedDAO.getMusicianByID(1);
        Assert.assertEquals(1, testedMusician.getId().intValue());
    }

    @Test
    public void testGetAllMusician() {
        List<Musician> testedListMusicians = testedDAO.getAllMusician();
        Assert.assertEquals(handbookForTest.getMusiciansList().size(), testedListMusicians.size());
    }

    @Test
    public void testInsertMusician() {
        Musician testedMusician = new Musician("Arnold");
        testedMusician.setId(99);
        testedDAO.insertMusician(testedMusician);
        Assert.assertNotNull(testedDAO.getMusicianByID(99));
    }

    @Test
    public void testDeleteMusician() {
        Integer befogDecrement = testedDAO.getAllMusician().size();
        Musician musicianForDelete = testedDAO.getAllMusician().get(1);
        testedDAO.deleteMusician(musicianForDelete.getId());
        Assert.assertEquals((befogDecrement - 1), testedDAO.getAllMusician().size());
    }

    @Test
    public void testUpdateMusician() {
        testedDAO = (XMLHandbookDAO) factoryDAO.createHandbookDAO();
        Musician testedMusician = testedDAO.getAllMusician().get(1);
        testedMusician.setName(NAME_FOR_TEST);
        testedDAO.updateMusician(testedMusician);
        testedDAO = (XMLHandbookDAO) factoryDAO.createHandbookDAO();
        Assert.assertEquals(NAME_FOR_TEST, testedDAO.getMusicianByID(testedMusician.getId()).getName());
    }

    @Test
    public void testGetSumLength() {
        Long testedSum = 0L;
        Musician testedMusician = testedDAO.getAllMusician().get(1);
        for (Album currentAlbum : testedMusician.getAlbumList()) {
            for (Composition currentSong : currentAlbum.getCompositionList()) {
                testedSum += currentSong.getLength();
            }
        }
        Assert.assertEquals(testedSum, testedDAO.getSumLength(testedMusician.getId()));
    }
}
