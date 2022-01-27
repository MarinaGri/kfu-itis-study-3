package ru.itis.listeners;

import lombok.Data;
import ru.itis.components.DrawJPanel;
import ru.itis.components.FacePartJPanel;
import ru.itis.utils.Constants;
import ru.itis.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@Data
public class DrawListener implements ActionListener {
    private DrawJPanel drawJPanel;

    public DrawListener(DrawJPanel drawJPanel){
        this.drawJPanel = drawJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        JButton button = (JButton) event.getSource();
        int num  = Integer.parseInt(button.getText());
        FacePartJPanel facePartPanel = new FacePartJPanel(ImageLoader.getImages()[num]);
        Box box = (Box) drawJPanel.getComponent(0);
        int oneTenthH = sSize.height / 10;
        int oneTenthW = sSize.width / 10;

        if(isPart(num, 0)) {
            facePartPanel.setPreferredSize(new Dimension(sSize.width/4 + sSize.width/20, sSize.height/8));
            drawFacePart(facePartPanel, box, 1, 0);
        } else if(isPart(num, 1)){
            Dimension dimension = new Dimension(oneTenthW + sSize.width/20, oneTenthH);
            facePartPanel.setPreferredSize(dimension);
            facePartPanel.setMaximumSize(dimension);
            facePartPanel.setMinimumSize(dimension);

            drawFacePart(facePartPanel, box, 3, sSize.width/26);
        } else if(isPart(num, 2)){
            Dimension dimension = new Dimension(2 * oneTenthW, oneTenthH - sSize.height/50);
            facePartPanel.setPreferredSize(dimension);
            facePartPanel.setMaximumSize(dimension);
            facePartPanel.setMinimumSize(dimension);

            drawFacePart(facePartPanel, box, 5, sSize.width/30);
        } else if(isPart(num, 3)){
            drawHair(num);
        }
    }

    private void drawFacePart(FacePartJPanel facePartPanel, Box box, int ind, int stop) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (facePartPanel.getX() < stop) {
                    try {
                        Thread.sleep(ind);
                    } catch (InterruptedException e) {
                        JOptionPane.showInternalMessageDialog(null, Constants.ERROR_MES);
                    }
                    box.remove(ind);
                    box.add(facePartPanel, ind);

                    drawJPanel.validate();
                    drawJPanel.repaint();
                }
                facePartPanel.setDraw(false);
                return null;
            }
        };
        worker.execute();
    }

    private void drawHair(int num){
        Arrays.fill(drawJPanel.getHair(), false);
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                int ind = num % Constants.STEP - 1;
                if (ind < 0) {
                    ind += Constants.STEP;
                }
                drawJPanel.getHair()[ind] = true;
                drawJPanel.validate();
                drawJPanel.repaint();
                return null;
            }
        };
        worker.execute();
    }

    private boolean isPart(int num, int indPart){
        return isPart(num, Constants.MALE_START, indPart)
                || isPart(num, Constants.FEMALE_START, indPart);
    }

    private boolean isPart(int num, int start, int part){
        return num >= start + Constants.STEP*part && num < start + Constants.STEP*(part + 1);
    }
}
