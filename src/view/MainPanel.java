package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class MainPanel extends JPanel implements Observer {

    private int bestScore;
    private final Drawable model;

    private Color currentBackground;
    private Iterator<Color> colorIterator;
    private final List<BufferedImage> blocksList;
    private final List<Color> backgroundColors;
    private final Map<Integer, BufferedImage> currentBlocks;

    private final int width;
    private final int height;
    private final int BLOCK_SIZE = 30;


    public MainPanel(final Drawable model) {

        blocksList = initialBlocksPictures();
        backgroundColors = initialBackgroundColors();
        colorIterator = backgroundColors.iterator();
        currentBackground = colorIterator.next();
        currentBlocks = new HashMap<>();

        this.model = model;
        this.bestScore = 0;
        width = model.getState()[0].length * BLOCK_SIZE;
        height = model.getState().length * BLOCK_SIZE;

        setFocusable(true);
        setSize(new Dimension(width, height + 30));
    }

    @Override
    public void update(final Observable o, final Object arg) {
        Graphics2D g = (Graphics2D) getGraphics();
        renderModel(g, model.getState());
        drawInfo(g, model.getScore());
    }

    private void drawInfo(final Graphics2D g, final  int score) {
        BufferedImage bi = new BufferedImage(width, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D biGraphics = bi.createGraphics();

        biGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        biGraphics.setBackground(new Color(30, 70, 70));
        biGraphics.clearRect(0, 0, width, 45);
        biGraphics.setColor(currentBackground);

        if (bestScore < score) {
            bestScore = score;
        }
        biGraphics.setFont(new Font("Type1", Font.TYPE1_FONT, 12));
        biGraphics.drawString("Score: " + score, 240, 15);
        biGraphics.drawString("Best: " + bestScore, 240, 27);
        if (model.hasGame()) {
            biGraphics.drawString("P - Pause", 10, 15);
            biGraphics.drawString("Shift - Switch background", 10, 27);
        } else {
            biGraphics.drawString("Game over", 10, 15);
            biGraphics.drawString("Space - Start new game", 10, 27);
        }

        g.drawImage(bi, 0, 0, null);
    }

    private List<BufferedImage> initialBlocksPictures() {
        List<BufferedImage> blocksList = new ArrayList<>();
        try {
            blocksList.add(ImageIO.read(getClass().getResource("/res/aqua.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/blue.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/darkblue.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/bordo.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/green.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/red.png")));
            blocksList.add(ImageIO.read(getClass().getResource("/res/yellow.png")));
        } catch (IOException e) {
            blocksList.add(new BufferedImage(10, 10, 10));
            e.printStackTrace(System.out);
        }
        return blocksList;
    }

    private List<Color> initialBackgroundColors() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(new Color(240, 240, 240));
        colorList.add(new Color(0x39, 0xa4, 0x67));
        colorList.add(new Color(1, 43, 64));
        colorList.add(new Color(150, 50, 70));
        return colorList;
    }

    private void renderModel(final Graphics2D g, final int[][] model) {
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
                    biGraphics.drawImage(currentBlocks.get(model[i][j]), j * BLOCK_SIZE, i * BLOCK_SIZE, null);
                }

                //Current block is a new figure, need to assign new image
                else {
                    currentBlocks.put(model[i][j], blocksList.get(random.nextInt(blocksList.size())));
                    biGraphics.drawImage(currentBlocks.get(model[i][j]), j * BLOCK_SIZE, i * BLOCK_SIZE, null);
                }
            }
        }
        g.drawImage(bi, 0, 30, null);
    }

    public void changeBackgroundColor() {
        if (!colorIterator.hasNext()) {
            colorIterator = backgroundColors.iterator();
        }
        currentBackground = colorIterator.next();
    }
}