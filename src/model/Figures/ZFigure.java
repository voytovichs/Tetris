package model.Figures;

public class ZFigure extends Figure{

    public ZFigure(int fieldWidth, int fieldHeight){
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, false}, {false, true, true}};
        super.setPresentation(presentation);
    }

    public ZFigure(Figure figure){
        super(figure);
    }

    @Override
    public Figure clone() {
        return new ZFigure(this);
    }
}
