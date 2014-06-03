package model.Figures;

public abstract class Figure {

    protected int X;
    protected int Y;
    private final int fieldWidth;
    private final int fieldHeight;
    private boolean[][] presentation;

    public  Figure(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        X = fieldWidth / 2;
        Y = 0;
    }

    public boolean rotate() {

        int newFigureWidth = presentation[0].length;
        int newFigureHeight = presentation.length;
        if(newFigureWidth + X > fieldWidth - 1 || newFigureHeight + Y > fieldHeight - 1 ||
                newFigureWidth > presentation[0].length && X == 0){
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
        if(Y + 1< fieldHeight){
            this.Y += 1;
            return true;
        }
        return false;
    }

    public boolean moveRight() {
        if (this.X + getWidth() - this.fieldWidth >= 1) {
            this.X += 1;
            return true;
        }
        return false;
    }

    public void moveLeft() {
        if (this.X >= 1) {
            this.X -= 1;
        }
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public int getWidth(){
        return presentation[0].length;
    }

    public int getHeight(){
        return this.presentation.length;
    }

    public boolean[][] getPresentation(){
        return presentation;
    }

}
