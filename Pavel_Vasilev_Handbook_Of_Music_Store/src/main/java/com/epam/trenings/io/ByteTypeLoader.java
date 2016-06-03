package com.epam.trenings.io;

import java.io.*;

/**
 * Created by pava0715 on 02.06.2016.
 */
public class ByteTypeLoader<GENERAL_TYPE extends Serializable> implements IExportImport<GENERAL_TYPE> {
    @Override
    public GENERAL_TYPE load(String path) {
        GENERAL_TYPE result = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fis);

            result = (GENERAL_TYPE) oin.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(GENERAL_TYPE objectToImport, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(objectToImport);

            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
