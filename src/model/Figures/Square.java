package model.Figures;

public class Square extends Figure {

    private boolean[][] presentation = {{true, true}, {true, true}};

    public Square(int fieldWidth){
        super(fieldWidth);
    }

    @Override
    public void rotate() {
        //do nothing

    }
}
