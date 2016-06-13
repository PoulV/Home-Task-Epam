package com.epam.trenings.dao.factory;

import com.epam.trenings.dao.IHandbookDAO;

/**
 * Created by Pol on 6/12/2016.
 */
public interface IAbstractFactoryDAO {
    IHandbookDAO createHandbookDAO();
}
