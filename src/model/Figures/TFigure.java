package model.Figures;

public class TFigure extends Figure {

    public TFigure(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{false, true, false}, {true, true, true}};
        super.setPresentation(presentation);
    }

    public TFigure(Figure figure) {
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
        return new TFigure(this);
    }
}
