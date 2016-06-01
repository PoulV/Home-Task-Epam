package com.epam.trenings.io;

import java.io.Serializable;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class TextTypeLoader<GENERAL_TYPE extends Serializable> implements IExportImport<GENERAL_TYPE>{
    @Override
    public GENERAL_TYPE load(GENERAL_TYPE objectToExport) {
        return null;
    }

    @Override
    public void save(GENERAL_TYPE objectToImport) {

    }
}
