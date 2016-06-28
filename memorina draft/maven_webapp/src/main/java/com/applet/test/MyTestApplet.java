package com.applet.test;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 1 on 27.06.2016.
 */
public class MyTestApplet extends Applet implements ActionListener{
    Image img;
    Card openCard = null;
    java.util.List<Card> cardSuite = new ArrayList<>();

    Difficult currentDifficult;

    @Override
    public void init() {

        setSize(1200, 800);



}

    @Override
    public void paint(Graphics g) {
        int x = 20, y = 50;
        g.drawString("Hello bitchachos!!!", 20, 30);

        try {
            File tempFile = new File("C:/Poul/temp/maven_webapp/images/CardBack.jpg");
            boolean isExist = tempFile.exists();
            URL path2Images = tempFile.toURI().toURL();

            Image img = getImage(path2Images);


            g.drawImage(img, x, y, this);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("AAA");
    }
}
