package controller;

import model.GameState;
import viev.MainPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener extends KeyAdapter {

    private final GameState gameState;
    private final MainPanel panel;

    public Listener(GameState gameState, MainPanel panel) {
        this.gameState = gameState;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!gameState.hasGame()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    GameStateUpdater.isNewGameButtonPressed = true;
                    break;
            }
            return;
        }

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                gameState.moveFigureLeft();
                break;

            case KeyEvent.VK_RIGHT:
                gameState.moveFigureRight();
                break;

            case KeyEvent.VK_UP:
                gameState.rotateFigure();
                break;

            case KeyEvent.VK_DOWN:
                gameState.moveFigureDown();
                break;

            case KeyEvent.VK_SHIFT:
                panel.changeBackgroundColor();
                break;

            case KeyEvent.VK_PAUSE:
            case KeyEvent.VK_P:
                if (gameState.isPaused) {
                    gameState.isPaused = false;
                    break;
                }
                gameState.isPaused = true;
                break;

            default:
                //ignore this case
        }
    }
}
