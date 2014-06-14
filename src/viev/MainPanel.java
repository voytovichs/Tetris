package viev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class MainPanel extends JPanel implements Observer {

    private final Drawable model;
    private final List<BufferedImage> blocksList;
    private final Map<Integer, BufferedImage> currentBlocks;
    private final int BLOCK_SIZE = 28;
    private final int BORDER_SIZE = 0;
    private final int width;
    private final int height;


    public MainPanel(Drawable model, KeyAdapter listener) {

        this.blocksList = getBlocks();
        currentBlocks = new HashMap<>();
        this.model = model;
        this.width = model.getState()[0].length * BLOCK_SIZE + 2 * BORDER_SIZE;
        this.height = model.getState().length * BLOCK_SIZE + 2 * BORDER_SIZE;
        this.addKeyListener(listener);

        setFocusable(true);
        setSize(new Dimension(width, height));
    }

    @Override
    public void update(Observable o, Object arg) {
        renderModel((Graphics2D) getGraphics(), model.getState(), width, height, BLOCK_SIZE, BORDER_SIZE, blocksList, currentBlocks);
    }

    private List<BufferedImage> getBlocks() {
        List<BufferedImage> blocksList = new ArrayList<>();
        try {
            blocksList.add(ImageIO.read(new File("resources/aqua.png")));
            blocksList.add(ImageIO.read(new File("resources/blue.png")));
            blocksList.add(ImageIO.read(new File("resources/bluepeach.png")));
            blocksList.add(ImageIO.read(new File("resources/bordo.png")));
            blocksList.add(ImageIO.read(new File("resources/darkblue.png")));
            blocksList.add(ImageIO.read(new File("resources/green.png")));
            blocksList.add(ImageIO.read(new File("resources/red.png")));
            blocksList.add(ImageIO.read(new File("resources/yellow.png")));
        } catch (IOException e) {
            blocksList.add(new BufferedImage(10, 10, 10));
        }
        return blocksList;
    }

    private void renderModel(Graphics2D g, int[][] model, int width, int height, int blockSize,
                             int borderSize, List<BufferedImage> blocks, Map<Integer, BufferedImage> currentBlocks) {

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D biGraphics = bi.createGraphics();
        biGraphics.setBackground(Color.WHITE);
        biGraphics.clearRect(0, 0, width, height);
        Random random = new Random();

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {

                //There's an empty block
                if (model[i][j] == 0) {
                    continue;
                }

                //Current block is not a new figure
                if (currentBlocks.containsKey(model[i][j])) {
                    biGraphics.drawImage(currentBlocks.get(model[i][j]), j * blockSize, i * blockSize, null);
                }

                //Current block is a new figure, need to assign new image
                else {
                    currentBlocks.put(model[i][j], blocks.get(random.nextInt(blocks.size())));
                    biGraphics.drawImage(currentBlocks.get(model[i][j]), j * blockSize, i * blockSize, null);
                }
            }
        }
        g.drawImage(bi, borderSize, borderSize, null);
    }
}