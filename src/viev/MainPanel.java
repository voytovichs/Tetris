package viev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class MainPanel extends JPanel implements Observer {

    private final Drawable model;
    private final List<BufferedImage> blocksList;
    private final List<Color> backgroundColors;
    private Iterator<Color> colorIterator;
    private Color currentBackground;
    private final Map<Integer, BufferedImage> currentBlocks;
    private final int BLOCK_SIZE = 30;
    private final int width;
    private final int height;


    public MainPanel(final Drawable model) {

        blocksList = initialBlocksPictures();
        backgroundColors = initialBackgroundColors();
        colorIterator = backgroundColors.iterator();
        currentBackground = colorIterator.next();
        currentBlocks = new HashMap<>();
        this.model = model;
        width = model.getState()[0].length * BLOCK_SIZE;
        height = model.getState().length * BLOCK_SIZE;

        setFocusable(true);
        setSize(new Dimension(width, height));
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (!colorIterator.hasNext()) {
            colorIterator = backgroundColors.iterator();
        }
        renderModel((Graphics2D) getGraphics(), model.getState(), width, height, BLOCK_SIZE, blocksList, currentBlocks);
    }

    private List<BufferedImage> initialBlocksPictures() {
        List<BufferedImage> blocksList = new ArrayList<>();
        try {
            blocksList.add(ImageIO.read(new File("resources/aqua.png")));
            blocksList.add(ImageIO.read(new File("resources/blue.png")));
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

    private List<Color> initialBackgroundColors() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(Color.WHITE);
        colorList.add(new Color(10, 50, 70));
        colorList.add(new Color(100, 200, 180));
        colorList.add(new Color(150, 40, 70));
        colorList.add(new Color(0x29, 0xb4,0x67));
        return colorList;
    }

    private void renderModel(final Graphics2D g, final int[][] model, final int width, final int height, final int blockSize,
                             final List<BufferedImage> blocks, final Map<Integer, BufferedImage> currentBlocks) {

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D biGraphics = bi.createGraphics();
        biGraphics.setBackground(currentBackground);
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
        g.drawImage(bi, 0, 0, null);
    }

    public void changeBackgroundColor() {
        if (!colorIterator.hasNext()) {
            colorIterator = backgroundColors.iterator();
        }
        currentBackground = colorIterator.next();
    }
}