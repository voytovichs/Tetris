package model.Figures;

public class Cane extends Figure {

    public Cane(int fieldWidth, int fieldHeight){
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, true}, {true, false, false}};
        super.setPresentation(presentation);
    }

    public Cane(Figure figure){
        super(figure);
    }

    @Override
    public Figure clone() {
        return new Cane(this);
    }
}
