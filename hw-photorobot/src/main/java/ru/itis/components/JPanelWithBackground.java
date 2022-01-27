package ru.itis.components;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;

    public JPanelWithBackground(Image image){
        this.backgroundImage = image;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
