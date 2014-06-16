package viev;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final Drawable model;

    public MainFrame(JPanel mainPanel, Drawable model) {
        this.model = model;

        setSize(mainPanel.getWidth(), mainPanel.getHeight());
        setTitle("Best tetris ever");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    public void clearAndRestart() {
        model.clear();
    }

}