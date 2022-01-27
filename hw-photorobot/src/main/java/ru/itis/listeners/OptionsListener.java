package ru.itis.listeners;

import lombok.Data;
import ru.itis.components.ControlJPanel;
import ru.itis.components.ImageOptionsJPanel;
import ru.itis.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class OptionsListener implements ActionListener {
    private final ControlJPanel controlPanel;
    private byte isMale;
    private byte numFacePart;

    public OptionsListener(ControlJPanel controlPanel){
        this.controlPanel = controlPanel;
        this.isMale = Constants.MALE_START;
        this.numFacePart = 0;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 2;

        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        controlPanel.remove(controlPanel.getComponent(3));

        JScrollPane sPane = new JScrollPane();
        sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sPane.setViewportView(new ImageOptionsJPanel(getFirstIndex(event), controlPanel.getDrawJPanel()));

        controlPanel.add(sPane, constraints);

        controlPanel.validate();
        controlPanel.repaint();
    }

    private int getFirstIndex(ActionEvent event){
        if(event.getSource() instanceof JRadioButton) {
            JRadioButton button = (JRadioButton) event.getSource();
            isMale = button.getText().equals("Мужчина") ? Constants.MALE_START : Constants.FEMALE_START;
        }

        if(event.getSource() instanceof JComboBox){
            JComboBox comboBox = (JComboBox) event.getSource();
            String item = (String)comboBox.getSelectedItem();

            for(byte i = 0; i < Constants.FACE_PARTS.length; i++){
                if(item != null && item.equals(Constants.FACE_PARTS[i])){
                    numFacePart = i;
                }
            }
        }
        if(numFacePart == 3){
            isMale = Constants.MALE_START;
        }
        return isMale + Constants.STEP * numFacePart;
    }
}
