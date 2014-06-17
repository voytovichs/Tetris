package controller;

import model.GameState;
import view.MainFrame;
import view.MainPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class GameStateUpdater {

    private static GameState gameState;
    static boolean isNewGameButtonPressed;

    public static void main(String[] args) {
        startGame();
    }

    private static MainFrame init() {

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState, getBestScore());
        mainPanel.addKeyListener(new Listener(gameState, mainPanel));
        MainFrame mainFrame = new MainFrame(mainPanel, gameState);

        gameState.addObserver(mainPanel);
        gameState.setChangedAndNotify();
        return mainFrame;
    }

    public static void startGame() {

        MainFrame mainFrame = init();
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
            if (gameState.getScore() > getBestScore()) {
                writeBestScore(gameState.getScore());
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

    private static int getBestScore() {
        try (Scanner scanner = new Scanner(new File("/resources/BestScore.txt"))) {
            return scanner.nextInt();
        } catch (FileNotFoundException e) {
            return 0;
        }
    }

    private static void writeBestScore(int score) {
        try (FileWriter writer = new FileWriter(new File("/resources/BestScore.txt"))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            //do nothing
        }
    }
}
