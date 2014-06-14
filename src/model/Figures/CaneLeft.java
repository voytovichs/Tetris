package model.Figures;

public class CaneLeft extends Figure {

    public CaneLeft(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, true}, {true, false, false}};
        super.setPresentation(presentation);
    }

    public CaneLeft(Figure figure) {
        super(figure);
    }

    @Override
    public Figure clone() {
        return new CaneLeft(this);
    }
}
