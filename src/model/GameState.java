package model;

import model.Figures.Figure;
import viev.Drawable;

import java.util.Arrays;
import java.util.Observable;

public class GameState extends Observable implements Drawable {

    private final int WIDTH = 10;
    private final int HEIGHT = 16;
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


    private boolean isIntersect(Figure figure, int[][] field) {
        try {
            for (int i = 0; i < figure.getWidth(); i++) {
                for (int j = 0; j < figure.getHeight(); j++) {
                    if (field[j + figure.getY()][i + figure.getX()] != 0 && figure.getPresentation()[j][i]) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean canFigureMovesDown(Figure figure) {
        return figure.getY() + figure.getHeight() < HEIGHT;
    }

    private boolean canFigureMovesRight(Figure figure, int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.moveRight();
        return !isIntersect(potentialFigure, field);
    }

    private boolean canFigureMovesLeft(Figure figure, int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.moveLeft();
        return !isIntersect(potentialFigure, field);
    }

    private boolean canFigureRotates(Figure figure, int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.rotate();
        return !isIntersect(potentialFigure, field);
    }

    private int[] movePartOfFieldDown(int currentLineNumber, int[][] field) {

        int[] currentLine = Arrays.copyOf(field[currentLineNumber], field[currentLineNumber].length);
        if (currentLineNumber == 0) {
            for (int i = 0; i < field[0].length; i++) {
                field[0][i] = 0;
            }
            return currentLine;
        }

        int[] previousLine = movePartOfFieldDown(currentLineNumber - 1, field);
        field[currentLineNumber] = previousLine;
        return currentLine;
    }

    private void deleteFilledLines(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            boolean isFilled = true;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0) {
                    isFilled = false;
                }
            }
            if (isFilled) {
                field[i] = movePartOfFieldDown(i - 1, field);
                i--;
            }
        }
        setChangedAndNotify();
    }

    public void moveFigureDown() {

        if (!canFigureMovesDown(currentFigure)) {
            deleteFilledLines(field);
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            if (isIntersect(currentFigure, field)) {
                hasGame = false;
            }
            drawFigureOnField(currentFigure, field);
        }

        Figure previousState = currentFigure.clone();
        currentFigure.moveDown();
        eraseFigureFromField(previousState, field);
        if (isIntersect(currentFigure, field)) {
            drawFigureOnField(previousState, field);
            deleteFilledLines(field);
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            if (isIntersect(currentFigure, field)) {
                hasGame = false;
            }
        }

        drawFigureOnField(currentFigure, field);
        setChangedAndNotify();
    }

    public synchronized void moveFigureLeft() {

        eraseFigureFromField(currentFigure, field);
        if (!canFigureMovesLeft(currentFigure, field)) {
            drawFigureOnField(currentFigure, field);
            return;
        }
        currentFigure.moveLeft();

        drawFigureOnField(currentFigure, field);
        setChangedAndNotify();
    }

    public synchronized void moveFigureRight() {

        eraseFigureFromField(currentFigure, field);
        if (!canFigureMovesRight(currentFigure, field)) {
            drawFigureOnField(currentFigure, field);
            return;
        }
        currentFigure.moveRight();

        drawFigureOnField(currentFigure, field);
        setChangedAndNotify();
    }

    public synchronized void rotateFigure() {

        eraseFigureFromField(currentFigure, field);
        if (!canFigureRotates(currentFigure, field)) {
            drawFigureOnField(currentFigure, field);
            return;
        }
        currentFigure.rotate();

        drawFigureOnField(currentFigure, field);
        setChangedAndNotify();
    }

    public void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }

    @Override
    public int[][] getState() {
        return field;
    }
}
