package com.taip.nextvision.ImageProcessingEngine;

import com.taip.nextvision.CommandEngine;

public class ImageProcessingEngine implements CommandEngine {

    private static ImageProcessingEngine instance = null;
    private Image targetImage;

    private ImageProcessingEngine()
    {

    }

    public static ImageProcessingEngine getInstance()
    {
        instance = new ImageProcessingEngine();
        return instance;
    }

    public Image getTargetImage() {
        return targetImage;
    }

    public void setTargetImage(Image targetImage) {
        this.targetImage = targetImage;
    }



    public void objectRecognition(Image targetImage)
    {

    }

    public void imageAnalysis(Image targetImage)
    {

    }

    public void imageModification(Image targetImage)
    {

    }

    @Override
    public String execute(String cmd) {
        return null;
    }
}
