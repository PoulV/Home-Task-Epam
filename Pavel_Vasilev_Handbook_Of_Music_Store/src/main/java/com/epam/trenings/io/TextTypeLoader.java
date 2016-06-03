package com.epam.trenings.io;

import java.io.*;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class TextTypeLoader<GENERAL_TYPE extends Serializable> implements IExportImport<GENERAL_TYPE> {
    @Override
    public GENERAL_TYPE load(String path) {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("path"));

            // create a new tokenizer
            Reader r = new BufferedReader(new InputStreamReader(ois));
            StreamTokenizer st = new StreamTokenizer(r);

            // print the stream tokens
            boolean eof = false;
            do {

                int token = st.nextToken();
                switch (token) {
                    case StreamTokenizer.TT_EOF:
                        System.out.println("End of File encountered.");
                        eof = true;
                        break;
                    case StreamTokenizer.TT_EOL:
                        System.out.println("End of Line encountered.");
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println("Word: " + st.sval);
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        System.out.println("Number: " + st.nval);
                        break;
                    default:
                        System.out.println((char) token + " encountered.");
                        if (token == '!') {
                            eof = true;
                        }
                }
            } while (!eof);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(GENERAL_TYPE objectToImport, String path) {
        try {
            FileOutputStream fileOutStream = new FileOutputStream(path);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOutStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            bufferedWriter.write("AAAAAAAAAAAAAAAAAAAAA");

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
