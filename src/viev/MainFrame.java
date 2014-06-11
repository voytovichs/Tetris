package viev;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(JPanel mainFrame){

        setSize(300, 300);
        setTitle("Best tetris ever");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().add(mainFrame);
        setVisible(true);
    }

}
