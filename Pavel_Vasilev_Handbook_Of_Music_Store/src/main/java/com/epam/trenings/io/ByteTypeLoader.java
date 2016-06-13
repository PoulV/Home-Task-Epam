package com.epam.trenings.io;

import com.epam.trenings.entities.EntityModel;
import com.epam.trenings.model.Handbook;

import java.io.*;

/**
 * Created by pava0715 on 02.06.2016.
 */
public class ByteTypeLoader implements IExportImport {
    private String path="";
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
            System.out.println("File " + path + " not found in root project directory.");
            fileNotFoundException.printStackTrace();
        } catch (IOException exceptionIO) {
            System.out.println("Input/output exception when try load object.");
            exceptionIO.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Exceptiont when try convert loaded object.");
            classNotFoundException.printStackTrace();
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
            System.out.println("File " + path + " not found in root project directory.");
            fileNotFoundException.printStackTrace();
        } catch (IOException exceptionIO) {
            System.out.println("Input/output exception when try load object.");
            exceptionIO.printStackTrace();
        }
    }
}