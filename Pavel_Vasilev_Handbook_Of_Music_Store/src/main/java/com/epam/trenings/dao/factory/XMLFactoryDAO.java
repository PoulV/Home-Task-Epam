package com.epam.trenings.dao.factory;

import com.epam.trenings.dao.IHandbookDAO;
import com.epam.trenings.dao.XMLHandbookDAO;

/**
 * Created by Pol on 6/12/2016.
 */
public class XMLFactoryDAO implements IAbstractFactoryDAO {
    private String path;

    public XMLFactoryDAO(String path) {
        this.path = path;
    }

    @Override
    public IHandbookDAO createHandbookDAO() {
        IHandbookDAO resultDAO = new XMLHandbookDAO(path);
        return resultDAO;
    }
}
