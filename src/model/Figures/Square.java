package model.Figures;

public class Square extends Figure {

    public Square(final int fieldWidth, final int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true}, {true, true}};
        super.setPresentation(presentation);
    }

    public Square(final Figure figure) {
        super(figure);
    }

    @Override
    public boolean rotate() {
        return true;
    }

    @Override
    public Figure clone() {
        return new Square(this);
    }
}
