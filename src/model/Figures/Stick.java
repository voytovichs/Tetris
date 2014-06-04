package model.Figures;

public class Stick extends Figure {

    private boolean isVertical = false;

    public Stick(int fieldWidth, int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true}, {true}, {true}, {true}};
        super.setPresentation(presentation);
        super.X = fieldWidth / 2;
    }

    public Stick(Figure figure){
        super(figure);
    }

    @Override
    public boolean rotate(){
        int newFigureWidth = presentation[0].length;
        int newFigureHeight = presentation.length;
        if(newFigureWidth + X > fieldWidth - 1 || newFigureHeight + Y > fieldHeight - 1 ||
                newFigureWidth > presentation[0].length && X == 0){
            return false;
        }
        if(!isVertical){
            if(super.Y > 0){
                super.Y -= 1;
                super.X += 1;
                presentation = new boolean[][]{{true}, {true}, {true}, {true}};
                isVertical = true;
            }
        }else{
            if(super.X > 0){
                super.X -= 1;
                super.Y += 1;
                presentation = new boolean[][]{{true, true, true, true}};
                isVertical = false;
            }
        }
        return true;
    }

    @Override
    public Figure clone() {
        return new Stick(this);
    }
}
