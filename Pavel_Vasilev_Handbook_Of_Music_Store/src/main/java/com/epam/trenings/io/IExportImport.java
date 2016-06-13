package com.epam.trenings.io;

import com.epam.trenings.model.Handbook;

/**
 * Created by pava0715 on 01.06.2016.
 */
public interface IExportImport {
    Handbook load();

    void save(Handbook handbookForExport);
}