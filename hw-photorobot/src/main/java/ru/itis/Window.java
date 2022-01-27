package ru.itis;

import ru.itis.components.ControlJPanel;
import ru.itis.components.DrawJPanel;

import javax.swing.*;
import java.awt.*;

public class Window {
    public void createGUI(){
        JFrame mainFrame = new JFrame();

        addComponents(mainFrame);

        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(sSize);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
    }

    private void addComponents(JFrame mainFrame) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        mainFrame.setLayout(new GridBagLayout());

        DrawJPanel drawPanel = new DrawJPanel();

        JScrollPane sPane = new JScrollPane();
        sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sPane.setViewportView(drawPanel);

        mainFrame.getContentPane().add(sPane, constraints);

        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 0.6;

        mainFrame.getContentPane().add(new ControlJPanel(drawPanel), constraints);
    }
}
