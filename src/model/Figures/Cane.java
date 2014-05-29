package model.Figures;

public class Cane extends Figure {

    private boolean[][] presentation = {{true, true, true}, {true, false, false}};

    public Cane(int fieldWidth){
        super(fieldWidth);
    }
}
