package viev;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

    private final Drawable model;

    public MainFrame(final JPanel mainPanel, Drawable model) {
        this.model = model;

        setSize(mainPanel.getWidth(), mainPanel.getHeight() + 30);
        setTitle("Best tetris ever");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void drawScoreAndInfo(Graphics2D g, int score){

        BufferedImage info = new BufferedImage(getWidth(), 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D infoGraphics = (Graphics2D)info.getGraphics();
        infoGraphics.setBackground(new Color(40, 40, 40));
        g.drawImage(info, 0, 0, null);
    }
}
