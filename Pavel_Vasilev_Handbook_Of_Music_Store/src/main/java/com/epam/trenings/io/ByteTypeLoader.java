package com.epam.trenings.io;

import com.epam.trenings.entities.EntityModel;
import com.epam.trenings.model.Handbook;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by pava0715 on 02.06.2016.
 */
public class ByteTypeLoader implements IExportImport {
    private static final Logger logger = Logger.getLogger(ByteTypeLoader.class);
    private String path = "";
    private EntityModel entityModel = new EntityModel();

    public ByteTypeLoader(String path) {
        this.path = path;
    }

    @Override
    public Handbook load() {
        EntityModel newEntityModel = new EntityModel();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            newEntityModel = (EntityModel) inputStream.readObject();

            inputStream.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("File " + path + " not found in root project directory.", fileNotFoundException);
        } catch (IOException exceptionIO) {
            logger.error("Input/output exception when try load object.", exceptionIO);
        } catch (ClassNotFoundException classNotFoundException) {
            logger.error("Exceptiont when try convert loaded object.", classNotFoundException);
        }
        return newEntityModel.getHandbookFromEntity();
    }

    @Override
    public void save(Handbook handbookForExport) {
        entityModel.fillFrom(handbookForExport);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(entityModel);

            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("File " + path + " not found in root project directory.", fileNotFoundException);
        } catch (IOException exceptionIO) {
            logger.error("Input/output exception when try load object.", exceptionIO);
        }
    }
}