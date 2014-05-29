package model.Figures;

public class TFigure extends Figure{

    private boolean[][] presentation = {{false, true, false}, {true, true, true}};

    public TFigure(int fieldWidth){
        super(fieldWidth);
    }
}
