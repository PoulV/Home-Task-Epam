package com.epam.trenings.io;

import com.epam.trenings.dao.JDBCHandbookDAO;
import com.epam.trenings.dao.factory.IAbstractFactoryDAO;
import com.epam.trenings.dao.factory.JDBCFactoryDAO;
import com.epam.trenings.model.Handbook;

/**
 * Created by Pol on 6/10/2016.
 */
public class JDBCTypeLoader implements IExportImport {
    @Override
    public Handbook load() {
        IAbstractFactoryDAO factoryDAO = new JDBCFactoryDAO();
        JDBCHandbookDAO dao = (JDBCHandbookDAO) factoryDAO.createHandbookDAO();

        Handbook resultHandbook = new Handbook(dao.getAllMusician());
        dao.closeConnection();
        return resultHandbook;
    }

    @Override
    public void save(Handbook handbookForExport) {
        IAbstractFactoryDAO factoryDAO = new JDBCFactoryDAO();
        JDBCHandbookDAO dao = (JDBCHandbookDAO) factoryDAO.createHandbookDAO();

        handbookForExport.getMusiciansList()
                .stream()
                .forEach(musician -> dao.insertOrUpdateMusician(musician));
        dao.closeConnection();
    }
}
