package model.Figures;

public class TFigure extends Figure{

    public TFigure(int fieldWidth, int fieldHeight){
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{false, true, false}, {true, true, true}};
        super.setPresentation(presentation);
    }

    public TFigure(Figure figure){
        super(figure);
    }

    @Override
    public Figure clone() {
        return new TFigure(this);
    }
}
