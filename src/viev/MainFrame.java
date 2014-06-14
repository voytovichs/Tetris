package viev;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(JPanel mainPanel) {

        setSize(mainPanel.getWidth(), mainPanel.getHeight());
        setTitle("Best tetris ever");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

}
