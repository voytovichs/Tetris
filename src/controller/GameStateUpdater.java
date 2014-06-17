package controller;

import model.GameState;
import view.MainFrame;
import view.MainPanel;

import java.io.*;


public class GameStateUpdater {

    private static GameState gameState;
    static boolean isNewGameButtonPressed;

    public static void main(String[] args) {
        startGame();
    }

    private static MainFrame init(ScoreIO io) {

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState, io.getBestScore());
        mainPanel.addKeyListener(new Listener(gameState, mainPanel));
        MainFrame mainFrame = new MainFrame(mainPanel, gameState);

        gameState.addObserver(mainPanel);
        gameState.setChangedAndNotify();
        return mainFrame;
    }

    public static void startGame() {

        ScoreIO io = new ScoreIO();
        MainFrame mainFrame = init(io);
        while (true) {
            isNewGameButtonPressed = false;
            while (gameState.hasGame()) {
                gameState.moveFigureDown();
                try {
                    Thread.sleep(gameState.delay);
                    while (gameState.isPaused) {
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
            if (gameState.getScore() > io.getBestScore()) {
                io.writeBestScore(gameState.getScore());
            }
            while (!isNewGameButtonPressed) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
            mainFrame.clearAndRestart();
        }
    }

    private static class ScoreIO{

    private int getBestScore() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("res/BestScore.txt")))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private static void writeBestScore(int score) {
        try (FileWriter writer = new FileWriter(new File("res/BestScore.txt"))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            //do nothing
        }
    }
    }
}
