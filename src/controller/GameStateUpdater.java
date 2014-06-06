package controller;

import model.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class GameStateUpdater extends KeyAdapter {

    private static GameState gameState;

    public static void main(String[] args){
        gameState = new GameState();
        while(gameState.hasGame){
            for(int i = 0; i < gameState.getState().length; i++){
                System.out.println(Arrays.toString(gameState.getState()[i]));
            }
            System.out.println("---------------------------");
            gameState.moveFigureDown();
            try {
                Thread.sleep(gameState.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameState.rotateFigure();
        System.out.println("WORKS");
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
