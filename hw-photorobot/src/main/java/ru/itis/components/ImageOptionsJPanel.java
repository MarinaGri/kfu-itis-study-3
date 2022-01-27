package ru.itis.components;

import lombok.Data;
import ru.itis.listeners.DrawListener;
import ru.itis.utils.Constants;
import ru.itis.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;

@Data
public class ImageOptionsJPanel extends JPanel {

    public ImageOptionsJPanel(int firstImgIndex, DrawJPanel drawJPanel){

        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        int j = firstImgIndex;
        for(int i = 0; i < Constants.STEP; i++){
            JPanel panel = new JPanelWithBackground(ImageLoader.getImages()[j]);

            JButton button = new JButton(String.valueOf(j));
            button.setLayout(new BorderLayout());
            button.setPreferredSize(new Dimension(200, 100));
            button.addActionListener(new DrawListener(drawJPanel));
            button.setBackground(Color.WHITE);

            button.add(panel, BorderLayout.CENTER);

            this.add(button, constraints);
            constraints.gridx = 1;
            j++;

            if(i % 2 != 0){
                constraints.gridx = 0;
                constraints.gridy = GridBagConstraints.RELATIVE;
            }
        }
    }
}
