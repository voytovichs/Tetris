package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final Drawable model;
    private final int panelWidth;
    private final int panelHeight;

    public MainFrame(JPanel mainPanel, Drawable model) {
        this.model = model;
        this.panelWidth = mainPanel.getWidth();
        this.panelHeight = mainPanel.getHeight();

        setSize(panelWidth, panelHeight);
        setTitle("Tetris");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    public void clearAndRestart() {
        model.clear();
    }

    public void setCrossplatformSize() {
        int borderWidth = panelWidth - (int) getContentPane().getSize().getWidth();
        int borderHeight = panelHeight - (int) getContentPane().getSize().getHeight();
        setSize(panelWidth + borderWidth, panelHeight + borderHeight);
    }

}