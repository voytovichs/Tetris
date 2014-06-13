package controller;

import model.GameState;
import viev.MainFrame;
import viev.MainPanel;

/* 1) Нужно фиксить rotation'ы, особенно у палочки, она вообще эксепшн кидает
 * 2) Сделать что-нибудь с thread.sleep()*/

public class GameStateUpdater {

    private static GameState gameState;

    public static void main(String[] args){

        gameState = new GameState();
        MainPanel mainPanel = new MainPanel(gameState, new Listener(gameState));
        gameState.addObserver(mainPanel);
        new MainFrame(mainPanel);

        while(gameState.hasGame){
            gameState.moveFigureDown();
            try {
                Thread.sleep(gameState.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
