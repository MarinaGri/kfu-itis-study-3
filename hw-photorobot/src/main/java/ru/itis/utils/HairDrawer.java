package ru.itis.utils;

import java.awt.*;

public class HairDrawer {
    private Dimension sSize;
    private int oneTenthH;
    private int oneTenthW;

    public HairDrawer(Dimension sSize) {
        this.sSize = sSize;
        oneTenthH = sSize.height / 10;
        oneTenthW = sSize.width / 10;
    }

    public void drawWavyLongHair(Graphics g){
        drawShortHair(g);
        drawWavyShortHair(g);
        int l = sSize.height - oneTenthH;
        drawWavyHair(g, l);
    }

    public void drawWavyMediumHair(Graphics g){
        drawShortHair(g);
        drawWavyShortHair(g);
        int l = sSize.height/2 + sSize.height/4;
        drawWavyHair(g, l);
        clearArea(g);
    }

    public void drawShortHairWithoutBang(Graphics g){
        drawShortHair(g);
        clearArea(g);
    }

    public void drawWavyShortHair(Graphics g){
        int k = 30;
        for(int i = oneTenthW; i < sSize.width/4 ; i += 39){
            for(int j = oneTenthH - k; j <  sSize.height/5 ; j+= 30) {
                g.fillOval(i, j, sSize.width/32, sSize.height/17);
                k += sSize.height/150;
            }
        }
        k += 50;
        for(int i = sSize.width/4; i < sSize.width/2 - sSize.width / 20 ; i += 39){
            for(int j = oneTenthH - k; j <  sSize.height/5 ; j+= 30) {
                g.fillOval(i, j, sSize.width/32, sSize.height/17);
                k -= sSize.height/200;
            }
        }
    }

    public void drawShortHair(Graphics g){
        g.fillOval(oneTenthW, 0, sSize.height/2 + sSize.height/8,
                sSize.height/2 - sSize.height/20);
        g.fillRect(oneTenthW, sSize.height/4, oneTenthH,sSize.height/5);
        g.fillRect(sSize.width/2 - oneTenthW, sSize.height/4, oneTenthH - 5 ,sSize.height/5);
        Graphics2D g2D = (Graphics2D) g;
        g2D.clearRect(oneTenthW + 40,sSize.height/3 - sSize.height/20,
                sSize.width / 4 + sSize.width / 20, sSize.height / 7);
    }

    public void drawStraightLongHair(Graphics g){
        int l = sSize.height;
        drawShortHairWithoutBang(g);
        Graphics2D g2D = (Graphics2D) g;

        drawStraightHair(g, l);

        g2D.setStroke(new BasicStroke(6));
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(sSize.width/4 + oneTenthW/4, 0 , sSize.width/4 + oneTenthW/4, oneTenthH);
        g.setColor(color);
    }

    public void drawStraightMediumHair(Graphics g){
        int l = sSize.height/2 + sSize.height/3;
        drawShortHair(g);
        drawStraightHair(g, l);
    }

    private void fillOvalLine(Graphics g, int y1, int y2, int x){
        for(int i = y1; i <  y2; i+= 30) {
            g.fillOval(x, i, sSize.width/32, sSize.height/17);
        }
    }

    private void drawWavyHair(Graphics g, int l){
        int h = sSize.height/2;
        fillOvalLine(g, oneTenthH + 70, l, oneTenthW - 70);
        fillOvalLine(g, oneTenthH, l, oneTenthW - 33);
        fillOvalLine(g, oneTenthH, l, oneTenthW);
        fillOvalLine(g, h, l, oneTenthW + 20);
        fillOvalLine(g, h + oneTenthH + 50, l, oneTenthW + 50);
        fillOvalLine(g, h + sSize.height/4, l, oneTenthW + 60);
        fillOvalLine(g, oneTenthH, l, sSize.width/2 - sSize.width / 20);
        fillOvalLine(g, oneTenthH + 40, l, sSize.width/2 - sSize.width / 20 + 30);
        fillOvalLine(g, h + oneTenthH - 50, l, sSize.width/2 - sSize.width / 20 - 35);
        fillOvalLine(g, h + oneTenthH, l, sSize.width/2 - sSize.width / 20 - 50);
        fillOvalLine(g, h + sSize.height/5, l, sSize.width/2 - sSize.width / 20 - 80);
        fillOvalLine(g, h + sSize.height/4, l, sSize.width/2 - sSize.width / 20 - 110);
    }

    private void clearArea(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.fillOval(oneTenthW + sSize.width/50 + 10, oneTenthH, sSize.height/2 + sSize.height/30,
                sSize.height/2 - sSize.height/8);
        g.setColor(color);
    }

    private void drawStraightHair(Graphics g, int l){
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(40));
        int h = sSize.height/2;
        g.drawLine(oneTenthW + 15, h - oneTenthH, oneTenthW + 15, l);

        g2D.setStroke(new BasicStroke(10));
        for(int j = 5; j <= 10; j++){
            g.drawLine(oneTenthW + j*10, h + oneTenthH + (j-5)*20, oneTenthW + j*10, l);
        }
        g.drawLine(oneTenthW + 40, h, oneTenthW + 40, l);

        g2D.setStroke(new BasicStroke(20));

        int w = oneTenthW + sSize.width/4;
        int k = 90;
        for(int j = 8; j <= 14; j++){
            g.drawLine(w+ j*10, h + oneTenthH + k, w + j*10, l);
            k -=10;
        }
        g.drawLine(w + 120, h + oneTenthH, w + 120, l);
        g.drawLine(w + 140, h + oneTenthH - 10, w + 140, l);
    }
}
