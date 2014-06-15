package controller;

import model.GameState;
import viev.MainFrame;
import viev.MainPanel;


public class GameStateUpdater {

    private static GameState gameState;

    public static void main(String[] args) {
        init();
        startGame();
    }

    private static void init() {

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState, new Listener(gameState));
        gameState.addObserver(mainPanel);
        new MainFrame(mainPanel);
        gameState.setChangedAndNotify();
    }

    public static void startGame() {
        while (gameState.hasGame) {
            gameState.moveFigureDown();
            try {
                Thread.sleep(gameState.DELAY);
                while (gameState.isPaused) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
