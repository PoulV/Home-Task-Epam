package com.epam.trenings.dao.factory;

import com.epam.trenings.dao.IHandbookDAO;
import com.epam.trenings.dao.JDBCHandbookDAO;

/**
 * Created by Pol on 6/12/2016.
 */
public class JDBCFactoryDAO implements IAbstractFactoryDAO {
    @Override
    public IHandbookDAO createHandbookDAO() {
        IHandbookDAO resultDAO = new JDBCHandbookDAO();
        return resultDAO;
    }
}
