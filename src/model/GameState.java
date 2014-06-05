package model;

import model.Figures.Figure;

import java.util.Arrays;
import java.util.Observable;

public class GameState extends Observable{

    private final int WIDTH = 9;
    private final int HEIGHT = 12;
    private final int DELAY = 1000;
    private Figure currentFigure;
    private final int[][] field = new int[HEIGHT][WIDTH];
    private int counterOfFigures = 1;
    private RandomFigureGenerator figureGenerator = new RandomFigureGenerator(WIDTH, HEIGHT);
    private boolean hasGame = true;

    public GameState() {
        currentFigure = figureGenerator.getRandomFigure();
        drawFigureOnField(currentFigure, field);
    }

    public static void main(String[] args){
        GameState gameState = new GameState();
        while(gameState.hasGame){
            for(int i = 0; i < gameState.getState().length; i++){
                System.out.println(Arrays.toString(gameState.getState()[i]));
            }
            System.out.println("---------------------------");
            gameState.moveFigureDown();

        }
    }

    private void drawFigureOnField(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i]) {
                    field[j + figure.getY()][i + figure.getX()] = counterOfFigures;
                }
            }
        }
    }

    private void eraseFigureFromField(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i]) {
                    field[j + figure.getY()][i + figure.getX()] = 0;
                }
            }
        }
    }


    private boolean checkIntersections(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (field[j + figure.getY()][i + figure.getX()] != 0 && figure.getPresentation()[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canFigureMovesDown(Figure figure) {
        return figure.getY() + figure.getHeight() < HEIGHT;
    }

    public void moveFigureDown() {
        if (!canFigureMovesDown(currentFigure)) {
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            if (checkIntersections(currentFigure, field)) {
                hasGame = false;
            }
            drawFigureOnField(currentFigure, field);

        }
        Figure previousState = currentFigure.clone();
        currentFigure.moveDown();
        eraseFigureFromField(previousState, field);
        if (checkIntersections(currentFigure, field)) {
            drawFigureOnField(previousState, field);
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            if (checkIntersections(currentFigure, field)) {
                hasGame = false;
            }
        }
        drawFigureOnField(currentFigure, field);
        setChanged();
        notifyObservers();
    }

    public synchronized void moveFigureLeft() {
        eraseFigureFromField(currentFigure, field);
        currentFigure.moveLeft();
        drawFigureOnField(currentFigure, field);
        setChanged();
        notifyObservers();
    }

    public synchronized void moveFigureRigth() {
        eraseFigureFromField(currentFigure, field);
        currentFigure.moveRight();
        drawFigureOnField(currentFigure, field);
        setChanged();
        notifyObservers();
    }

    public synchronized void rotateFigure() {
        eraseFigureFromField(currentFigure, field);
        currentFigure.rotate();
        drawFigureOnField(currentFigure, field);
        setChanged();
        notifyObservers();
    }

    public int[][] getState() {
        return field;
    }
}
