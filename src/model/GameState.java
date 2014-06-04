package model;

import model.Figures.Figure;

import java.util.Arrays;

public class GameState {

    private final static int WIDTH = 11;
    private final static int HEIGHT = 8;
    private final static int DELAY = 1000;
    private static Figure currentFigure;
    private static final int[][] field = new int[HEIGHT][WIDTH];
    private static int counterOfFigures = 1;

    public static void main(String[] args) {

        RandomFigureGenerator figureGenerator = new RandomFigureGenerator(WIDTH, HEIGHT);
        currentFigure = figureGenerator.getRandomFigure();
        drawFigureOnField(currentFigure, field);
        System.out.flush();
        for (int i = 0; i < field.length; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println("---------------------");

        while (true) {
            if (!canFigureMovesDown(currentFigure)) {
                currentFigure = figureGenerator.getRandomFigure();
                counterOfFigures++;
                if(checkIntersections(currentFigure, field)){
                    break;
                }
                drawFigureOnField(currentFigure, field);
                for (int i = 0; i < field.length; i++) {
                    System.out.println(Arrays.toString(field[i]));
                }
                System.out.println("---------------------");
                continue;
            }
            Figure previousState = currentFigure.clone();
            currentFigure.moveDown();
            eraseFigureFromField(previousState, field);
            if (checkIntersections(currentFigure, field)) {
                drawFigureOnField(previousState, field);

                for (int i = 0; i < field.length; i++) {
                    System.out.println(Arrays.toString(field[i]));
                }

                System.out.println("---------------------");
                currentFigure = figureGenerator.getRandomFigure();
                counterOfFigures++;
                if(checkIntersections(currentFigure, field)){
                    break;
                }
            }
            drawFigureOnField(currentFigure, field);
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.flush();
            for (int i = 0; i < field.length; i++) {
                System.out.println(Arrays.toString(field[i]));
            }
            System.out.println("---------------------");
        }
    }

    private static void drawFigureOnField(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i]) {
                    field[j + figure.getY()][i + figure.getX()] = counterOfFigures;
                }
            }
        }
    }

    private static void eraseFigureFromField(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (figure.getPresentation()[j][i]) {
                    field[j + figure.getY()][i + figure.getX()] = 0;
                }
            }
        }
    }


    private static boolean checkIntersections(Figure figure, int[][] field) {
        for (int i = 0; i < figure.getWidth(); i++) {
            for (int j = 0; j < figure.getHeight(); j++) {
                if (field[j + figure.getY()][i + figure.getX()] != 0 && figure.getPresentation()[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canFigureMovesDown(Figure figure) {
        return figure.getY() + figure.getHeight() < HEIGHT;
    }

}
