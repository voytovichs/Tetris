package controller;

import model.GameState;
import view.MainFrame;
import view.MainPanel;


public class GameStateUpdater {

    private static GameState gameState;
    private static boolean isNewGameButtonPressed = false;

    public static void main(String[] args) {
        startGame();
    }

    private static MainFrame init() {

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState);
        mainPanel.addKeyListener(new Listener(gameState, mainPanel));
        MainFrame mainFrame = new MainFrame(mainPanel, gameState);

        gameState.addObserver(mainPanel);
        gameState.setChangedAndNotify();
        return mainFrame;
    }

    public static void startGame() {

        MainFrame mainFrame = init();
        mainFrame.setCrossplatformSize();
        while (true) {
            isNewGameButtonPressed = false;
            while (gameState.hasGame()) {
                gameState.moveFigureDown();
                try {
                    Thread.sleep(gameState.getDelay());
                    while (gameState.isPaused()) {
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    //do nothing
                }
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

    public static void setNewGameButtonPressed() {
        isNewGameButtonPressed = true;
    }
}
