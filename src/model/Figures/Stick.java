package model.Figures;

public class Stick extends Figure {

    private boolean isVertical = false;
    private boolean[][] presentation = new boolean[][]{{true, true, true, true}};

    public Stick(int fieldWidth) {
        super(fieldWidth);
        super.X = fieldWidth / 2 - 1;
    }

    @Override
    public void rotate(){
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
    }

}
