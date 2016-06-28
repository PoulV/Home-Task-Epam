package com.applet.test;

import java.awt.*;

/**
 * Created by 1 on 28.06.2016.
 */
public class ImagePanel extends Panel {
    private Image currentImage;

    public ImagePanel() {
        super();
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
