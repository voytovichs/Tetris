package model.Figures;

public abstract class Figure {

    protected int X;
    protected int Y;
    private final int fieldWidth;
    private boolean[][] presentation;

    public  Figure(int fieldWidth) {
        this.fieldWidth = fieldWidth;
        X = fieldWidth / 2;
        Y = 0;
    }

    public void rotate() {
        boolean[][] newFigure = new boolean[presentation[0].length][presentation.length];

        for (int i = 0; i < newFigure.length; i++) {
            for (int j = 0; j < newFigure[i].length; j++) {
                newFigure[i][j] = presentation[j][(presentation[0].length - 1) - i];
            }
        }
        this.presentation = newFigure;
    }

    public void moveDown() { this.Y += 1; }

    public void moveRight() {
        if (this.X + getWidth() - this.fieldWidth >= 1) {
            this.X += 1;
        }
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
