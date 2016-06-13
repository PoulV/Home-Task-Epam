package com.epam.trenings.io;

import com.epam.trenings.dao.JDBCHandbookDAO;
import com.epam.trenings.dao.XMLHandbookDAO;
import com.epam.trenings.dao.factory.IAbstractFactoryDAO;
import com.epam.trenings.dao.factory.JDBCFactoryDAO;
import com.epam.trenings.dao.factory.XMLFactoryDAO;
import com.epam.trenings.model.Handbook;
import com.epam.trenings.model.Musician;

/**
 * Created by Pol on 6/10/2016.
 */
public class JDBCTypeLoader implements IExportImport {
    @Override
    public Handbook load() {
        return null;
    }

    @Override
    public void save(Handbook handbookForExport) {
        IAbstractFactoryDAO factoryDAO = new JDBCFactoryDAO();
        JDBCHandbookDAO dao = (JDBCHandbookDAO) factoryDAO.createHandbookDAO();

        handbookForExport.getMusiciansList()
                .stream()
                .forEach(musician -> dao.insertMusician(musician));

    }
}
