package model;

import model.Figures.Figure;

public class GameState {

    private final static int WIDTH = 30;
    private final static int HEIGHT = 30;
    private final static int DELAY = 2000;
    int counterOfFigures = 0;

    public static void main(String[] args){

        RandomFigureGenerateor figureGenerateor = new RandomFigureGenerateor(WIDTH);
        int[][] field = new int[WIDTH][HEIGHT];
        Figure currentFigure = figureGenerateor.getRandomFigure();

        while(true){


        }
    }

    private boolean checkIntersections(Figure figure, int[][] field){

        for(int i = figure.getX(); i <= figure.getWidth() + figure.getX(); i++){
            for(int j = figure.getY(); j <= figure.getHeight() + figure.getY(); j++){
                if(field[i][j] != 0 && figure.getPresentation()[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canFigureMovesDown(Figure figure, int[][] field){
        if(figure.getY() + figure.getHeight() + 1 < HEIGHT){
            return true;
        }
        return false;
    }

}
