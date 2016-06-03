package com.epam.trenings.io;

import java.io.Serializable;

/**
 * Created by pava0715 on 01.06.2016.
 */
public interface IExportImport <GENERAL_TYPE extends Serializable> {
    GENERAL_TYPE load(String path);
    void save(GENERAL_TYPE objectToImport, String path);
}
