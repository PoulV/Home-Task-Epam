package com.applet.test;

import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Created by 1 on 28.06.2016.
 */
public class Card  extends Panel {
    private final URL backImageURL;
    private final URL faceImageURL;
    private final int cardId;
    private ImagePanel panel;
    private Image currentImage;


    public Card(int cardId, URL faceImageUrl, URL backImageURL) {
        super();
        this.cardId = cardId;
        this.faceImageURL = faceImageUrl;
        this.backImageURL = backImageURL;
    }

    public URL getBackImageURL() {
        return backImageURL;
    }

    public URL getFaceImageURL() {
        return faceImageURL;
    }

    public int getCardId() {
        return cardId;
    }

    public ImagePanel getPanel() {
        return panel;
    }

    public void setImage(Image newImage) {
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(newImage, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException x) {
            System.err.println("Error loading image!");
        }
        currentImage = newImage;
    }

    public Dimension getPreferredSize() {
        int width = currentImage.getWidth(this);
        int height = currentImage.getHeight(this);
        System.out.println("" + width + " " + height);
        return (new Dimension(width, height));
    }

    public void paint(Graphics g) {
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, this);
        }
    }
}
