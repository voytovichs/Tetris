package model.Figures;

public class ZLeftFigure extends Figure{

    public ZLeftFigure(int fieldWidth, int fieldHeight){
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true, true, false}, {false, true, true}};
        super.setPresentation(presentation);
    }

    public ZLeftFigure(Figure figure){
        super(figure);
    }

    @Override
    public Figure clone() {
        return new ZLeftFigure(this);
    }
}
