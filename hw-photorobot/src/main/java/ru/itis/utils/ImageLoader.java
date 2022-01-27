package ru.itis.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageLoader {
    private static Image[] images;

    private static void loadImg(){
        images = new Image[Constants.MAX_NUM + 1];
        for(int i = 0; i < images.length; i++){
            try {
                URL resource = ImageLoader.class.getClassLoader().getResource("img\\"+ i +".jpg");
                if (resource == null) {
                    throw new IllegalArgumentException("file not found!");
                } else {
                    images[i] = ImageIO.read(new File(resource.toURI()));
                }
            } catch (URISyntaxException | IOException | IllegalArgumentException ex) {
                JOptionPane.showInternalMessageDialog(null, Constants.ERROR_MES);
            }
        }
    }

    public static Image[] getImages() {
        if(images == null){
            loadImg();
        }
        return images;
    }
}
