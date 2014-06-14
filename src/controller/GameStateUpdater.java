package controller;

import model.GameState;
import viev.MainFrame;
import viev.MainPanel;


public class GameStateUpdater {

    private static GameState gameState;

    public static void main(String[] args) {

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState, new Listener(gameState));

        gameState.addObserver(mainPanel);
        new MainFrame(mainPanel);
        gameState.setChangedAndNotify();

        while (gameState.hasGame) {
            gameState.moveFigureDown();
            try {
                Thread.sleep(gameState.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
