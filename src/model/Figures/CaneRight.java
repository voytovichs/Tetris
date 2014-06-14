package model.Figures;

public class CaneRight extends Figure {

    public CaneRight(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, true}, {false, false, true}};
        super.setPresentation(presentation);
    }

    public CaneRight(Figure figure) {
        super(figure);
    }

    @Override
    public boolean rotate() {
        if (this.getWidth() + X == fieldWidth && getWidth() == 2) {
            return false;
        }
        return super.rotate();
    }

    @Override
    public Figure clone() {
        return new CaneRight(this);
    }
}
