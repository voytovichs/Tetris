package view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private final Drawable model;
    private final int panelWidth;
    private final int panelHeight;

    public MainFrame(final MainPanel mainPanel, final Drawable model) {
        this.model = model;
        this.panelWidth = mainPanel.getWidth();
        this.panelHeight = mainPanel.getHeight();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent windowEvent) {
                mainPanel.repaintField();
            }
        });
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