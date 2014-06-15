package model.Figures;

public class ZLeftFigure extends Figure {

    public ZLeftFigure(final int fieldWidth, final int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, false}, {false, true, true}};
        super.setPresentation(presentation);
    }

    public ZLeftFigure(final Figure figure) {
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
        return new ZLeftFigure(this);
    }
}
