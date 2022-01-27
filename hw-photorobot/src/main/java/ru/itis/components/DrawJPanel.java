package ru.itis.components;

import lombok.Data;
import ru.itis.utils.Constants;
import ru.itis.utils.HairDrawer;
import ru.itis.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;

@Data
public class DrawJPanel extends JPanel {
    private Image backgroundImage;
    private HairDrawer drawer;
    private boolean[] hair;

    public DrawJPanel() {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawer = new HairDrawer(sSize);
        backgroundImage = ImageLoader.getImages()[Constants.BACKGROUND];
        hair = new boolean[Constants.STEP];

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(sSize.height/3 - sSize.height/20));

        JPanel eye = new JPanel();
        eye.setBackground(Color.WHITE);
        eye.setPreferredSize(new Dimension(sSize.width / 4 + sSize.width / 20, sSize.height / 8));
        box.add(eye);

        JPanel struct = new JPanel();
        struct.setBackground(Color.WHITE);
        struct.setPreferredSize(new Dimension(sSize.width / 4 + sSize.width / 20, sSize.height / 15));
        box.add(struct);

        box.add(Box.createVerticalStrut( sSize.height/10));
        box.add(Box.createVerticalStrut(sSize.height/15));
        box.add(Box.createVerticalStrut(sSize.height/10 - sSize.height/20));

        this.add(box);
        this.setPreferredSize(new Dimension(sSize.width/2 + sSize.width/20, sSize.height - 20));
    }

    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

        int i = 0;
        if(hair[i++]) g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        if(hair[i++]) drawer.drawStraightMediumHair(g);
        if(hair[i++]) drawer.drawShortHairWithoutBang(g);
        if(hair[i++]) drawer.drawWavyMediumHair(g);
        if(hair[i++]) drawer.drawShortHair(g);
        if(hair[i++]) drawer.drawStraightLongHair(g);
        if(hair[i++]) drawer.drawWavyShortHair(g);
        if(hair[i++]) drawer.drawWavyLongHair(g);
    }
}
