package model;

import model.Figures.Figure;
import view.Drawable;

import java.util.Arrays;
import java.util.Observable;

public class GameState extends Observable implements Drawable {

    private static final int WIDTH = 11;
    private static final int HEIGHT = 16;
    private final int[][] field = new int[HEIGHT][WIDTH];

    private int counterOfFigures = 1;
    private Figure currentFigure;
    private final RandomFigureGenerator figureGenerator = new RandomFigureGenerator(WIDTH, HEIGHT);

    private boolean hasGame = true;
    private boolean isPaused = false;
    private int delay = 700;
    private int score = 0;

    public GameState() {
        currentFigure = figureGenerator.getRandomFigure();
    }

    private void drawFigureOnField(final Figure figure, final int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i] && figure.getY() + j >= 0) {
                    field[j + figure.getY()][i + figure.getX()] = counterOfFigures;
                }
            }
        }
    }

    private void eraseFigureFromField(final Figure figure, final int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i] && figure.getY() + j >= 0) {
                    field[j + figure.getY()][i + figure.getX()] = 0;
                }
            }
        }
    }

    private boolean isIntersect(final Figure figure, final int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getY() + j >= 0) {
                    if (field[j + figure.getY()][i + figure.getX()] != 0 && figure.getPresentation()[j][i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canFigureMovesDown(final Figure figure) {
        return figure.getY() + figure.getHeight() < HEIGHT;
    }

    private boolean canFigureMovesRight(final Figure figure, final int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.moveRight();
        return !isIntersect(potentialFigure, field);
    }

    private boolean canFigureMovesLeft(final Figure figure, final int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.moveLeft();
        return !isIntersect(potentialFigure, field);
    }

    private boolean canFigureRotates(final Figure figure, final int[][] field) {
        Figure potentialFigure = figure.clone();
        potentialFigure.rotate();
        return !isIntersect(potentialFigure, field);
    }

    private int[] movePartOfFieldDown(final int currentLineNumber, final int[][] field) {

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

    private void deleteFilledLines(final int[][] field) {
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
                increaseScore();
            }
        }
        setChangedAndNotify();
    }

    public synchronized void moveFigureDown() {

        if (!canFigureMovesDown(currentFigure)) {
            deleteFilledLines(field);
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            drawFigureOnField(currentFigure, field);
            setChangedAndNotify();
            return;
        }

        eraseFigureFromField(currentFigure, field);
        Figure previousState = currentFigure.clone();
        currentFigure.moveDown();
        if (isIntersect(currentFigure, field)) {
            drawFigureOnField(previousState, field);
            deleteFilledLines(field);
            currentFigure = figureGenerator.getRandomFigure();
            counterOfFigures++;
            if (isIntersect(currentFigure, field)) {
                hasGame = false;
                setChangedAndNotify();
                return;
            }
            drawFigureOnField(currentFigure, field);
            setChangedAndNotify();
            return;
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

    public void moveFigureRight() {

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

    private void increaseScore() {
        score += 10 * WIDTH;
        if (delay > 200) {
            delay -= 15;
        }
    }

    public void pause() {
        isPaused = true;
    }

    public void unpause() {
        isPaused = false;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public int[][] getState() {
        return field;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void clear() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = 0;
            }
        }
        currentFigure = figureGenerator.getRandomFigure();
        score = 0;
        counterOfFigures = 1;
        delay = 700;
        hasGame = true;
        setChangedAndNotify();
    }

    @Override
    public boolean hasGame() {
        return hasGame;
    }
}
