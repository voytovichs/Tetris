package model.Figures;

public class ZRightFigure extends Figure {

    public ZRightFigure(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{false, true, true}, {true, true, false}};
        super.setPresentation(presentation);
    }

    public ZRightFigure(Figure figure) {
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
        return new ZRightFigure(this);
    }
}
