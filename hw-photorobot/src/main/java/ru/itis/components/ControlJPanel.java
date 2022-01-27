package ru.itis.components;

import lombok.Data;
import ru.itis.listeners.OptionsListener;
import ru.itis.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Data
public class ControlJPanel extends JPanel{
    private DrawJPanel drawJPanel;

    public ControlJPanel(DrawJPanel drawJPanel) {
        this.drawJPanel = drawJPanel;
        this.setLayout(new GridBagLayout());
        ActionListener listener = new OptionsListener(this);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.01;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        JComboBox<String> comboBox = new JComboBox<>(Constants.FACE_PARTS);
        comboBox.addActionListener(listener);

        this.add(comboBox, constraints);

        constraints.weighty = 0.05;
        constraints.gridwidth = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;

        ButtonGroup group = new ButtonGroup();
        JRadioButton rb1 = new JRadioButton("Мужчина", true);
        JRadioButton rb2 = new JRadioButton("Женщина");

        group.add(rb1);
        group.add(rb2);

        this.add(rb1, constraints);

        constraints.gridx = 1;

        this.add(rb2, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.weighty = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;

        JPanel imgPanel = new ImageOptionsJPanel(Constants.MALE_START, drawJPanel);

        JScrollPane sPane = new JScrollPane();
        sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sPane.setViewportView(imgPanel);
        this.add(sPane, constraints);

        rb1.addActionListener(listener);
        rb2.addActionListener(listener);
    }
}
