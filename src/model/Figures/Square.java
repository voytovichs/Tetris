package model.Figures;

public class Square extends Figure {

    public Square(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true}, {true, true}};
        super.setPresentation(presentation);
    }

    public Square(Figure figure) {
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
