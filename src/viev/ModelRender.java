package viev;

import javax.swing.*;

public class ModelRender extends JFrame {

    private JPanel mainPanel;

    public ModelRender(){

        mainPanel = new JPanel();
        setSize(300, 300);
        setTitle("Best tetris ever");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

}
