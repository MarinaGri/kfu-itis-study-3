package ru.itis.components;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class FacePartJPanel extends JPanel {
    private boolean isDraw;
    private Image image;
    private int x;

    public FacePartJPanel(Image image) {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.isDraw = true;
        this.image = image;
        this.x = -sSize.width/8;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(isDraw) {
            g.drawImage(image, x += 3, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.drawImage(image, x, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
