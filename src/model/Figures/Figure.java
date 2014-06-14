package model.Figures;

public abstract class Figure {

    protected int X;
    protected int Y;
    protected final int fieldWidth;
    protected final int fieldHeight;
    protected boolean[][] presentation;

    public Figure(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        X = fieldWidth / 2 - 1;
        Y = 0;
    }

    public Figure(Figure figure) {
        this.fieldWidth = figure.fieldWidth;
        this.fieldHeight = figure.fieldHeight;
        X = figure.getX();
        Y = figure.getY();
        boolean[][] figurePresentation = figure.getPresentation();
        this.presentation = new boolean[figurePresentation.length][figurePresentation[0].length];
        for (int i = 0; i < presentation.length; i++) {
            for (int j = 0; j < presentation[0].length; j++) {
                presentation[i][j] = figurePresentation[i][j];
            }
        }

    }

    public boolean rotate() {

        int newFigureWidth = presentation[0].length;
        int newFigureHeight = presentation.length;
        if (newFigureWidth + X > fieldWidth || newFigureHeight + Y > fieldHeight - 1 ||
                newFigureWidth > presentation[0].length && X == 0) {
            return false;
        }
        boolean[][] newFigure = new boolean[newFigureWidth][newFigureHeight];

        for (int i = 0; i < newFigure.length; i++) {
            for (int j = 0; j < newFigure[i].length; j++) {
                newFigure[i][j] = presentation[j][(presentation[0].length - 1) - i];
            }
        }
        this.presentation = newFigure;
        return true;
    }

    public boolean moveDown() {
        if (Y + 1 < fieldHeight) {
            this.Y += 1;
            return true;
        }
        return false;
    }

    public boolean moveRight() {
        if (this.fieldWidth - (this.X + getWidth()) >= 1) {
            this.X += 1;
            return true;
        }
        return false;
    }

    public boolean moveLeft() {
        if (this.X >= 1) {
            this.X -= 1;
            return true;
        }
        return false;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getWidth() {
        return presentation[0].length;
    }

    public int getHeight() {
        return this.presentation.length;
    }

    public boolean[][] getPresentation() {
        return presentation;
    }

    public abstract Figure clone();

    protected void setPresentation(boolean[][] presentation) {
        this.presentation = presentation;
    }
}
