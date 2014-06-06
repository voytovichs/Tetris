package model;

import model.Figures.Figure;

import java.util.Arrays;
import java.util.Observable;

public class GameState extends Observable{

    private final int WIDTH = 9;
    private final int HEIGHT = 12;
    private Figure currentFigure;
    private final int[][] field = new int[HEIGHT][WIDTH];
    private int counterOfFigures = 1;
    private RandomFigureGenerator figureGenerator = new RandomFigureGenerator(WIDTH, HEIGHT);
    public boolean hasGame = true;
    public final int DELAY = 1000;

    public GameState() {
        currentFigure = figureGenerator.getRandomFigure();
        drawFigureOnField(currentFigure, field);
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

    private int[] movePartOfFieldDown(int currentLineNumber, int[][] field){

        int[] currentLine = Arrays.copyOf(field[currentLineNumber], field[currentLineNumber].length);
        if(currentLineNumber == 0){
            for(int i = 0; i < field[0].length; i++){
                field[0][i] = 0;
            }
            return currentLine;
        }

        int[] previousLine = movePartOfFieldDown(currentLineNumber - 1, field);
        field[currentLineNumber] = previousLine;
        return currentLine;
    }

    private void deleteFilledLines(int[][] field){
        for(int i = 0; i < field.length; i++){
            boolean isFilled = true;
            for(int j = 0; j < field[i].length; j++){
                if(field[i][j] == 0){ isFilled = false; }
            }
            if(isFilled){ movePartOfFieldDown(i, field); }
        }
    }

    public void moveFigureDown() {
        if (!canFigureMovesDown(currentFigure)) {
            deleteFilledLines(field);
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
