package controller;

import model.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener extends KeyAdapter {

    private final GameState gameState;

    public Listener(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){

            case KeyEvent.VK_LEFT:{
                gameState.moveFigureLeft();
                break;
            }

            case KeyEvent.VK_RIGHT:{
                gameState.moveFigureRight();
                break;
            }

            case KeyEvent.VK_UP:{
                gameState.rotateFigure();
                break;
            }

            case KeyEvent.VK_DOWN:{
                gameState.moveFigureDown();
                break;
            }

            default:{
                //just ignore it
            }
        }
    }


}
