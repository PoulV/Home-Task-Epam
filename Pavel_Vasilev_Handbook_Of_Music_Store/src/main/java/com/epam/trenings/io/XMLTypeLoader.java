package com.epam.trenings.io;

import com.epam.trenings.Utils;
import com.epam.trenings.dao.XMLHandbookDAO;
import com.epam.trenings.dao.factory.IAbstractFactoryDAO;
import com.epam.trenings.dao.factory.XMLFactoryDAO;
import com.epam.trenings.model.Handbook;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Pol on 6/10/2016.
 */
public class XMLTypeLoader implements IExportImport {
    private String path = "";
    private static final Logger logger = Logger.getLogger(XMLTypeLoader.class);

    public XMLTypeLoader(String path) {
        this.path = path;
    }

    @Override
    public Handbook load() {
        IAbstractFactoryDAO factoryDAO = new XMLFactoryDAO(path);
        XMLHandbookDAO dao = (XMLHandbookDAO) factoryDAO.createHandbookDAO();

        Handbook resultHandbook = new Handbook(dao.getAllMusician());
        return resultHandbook;
    }

    @Override
    public void save(Handbook handbookForExport) {
        try {
            JAXBContext context = JAXBContext.newInstance(Handbook.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(handbookForExport, new File(path));
        } catch (JAXBException e) {
            logger.error("JAXBException catch when try save model to XML", e);
        }
    }
}
