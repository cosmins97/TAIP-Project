package com.taip.nextvision.ImageProcessingEngine;

//import java.awt.image.BufferedImage;

public class Image {
    private int Height;
    private int Width;
//    private BufferedImage image;

    public Image() {

    }

    public Image(int height, int width) {
        Height = height;
        Width = width;
    }

//    public BufferedImage getImage() {
//        return image;
//    }
//
//    public void setImage(BufferedImage image) {
//        this.image = image;
//    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }
}
