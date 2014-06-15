package model.Figures;

public class Stick extends Figure {

    private boolean isVertical = true;

    public Stick(final int fieldWidth, final int fieldHeight) {
        super(fieldWidth, fieldHeight);
        boolean[][] presentation = {{true}, {true}, {true}, {true}};
        super.setPresentation(presentation);
        super.X = fieldWidth / 2;
        super.Y = -3;
    }

    public Stick(final Figure figure) {
        super(figure);
    }

    @Override
    public boolean rotate() {

        int newFigureWidth = this.getHeight();
        int newFigureHeight = this.getWidth();

        if (isVertical) {
            if (super.Y > -3) {
                int newFigureX = super.X - 1;
                int newFigureY = super.Y + 1;

                if (newFigureX < 0 || newFigureX + newFigureWidth > fieldWidth) {
                    return false;
                }

                super.X = newFigureX;
                super.Y = newFigureY;
                presentation = new boolean[][]{{true, true, true, true}};
                isVertical = false;
            }
        } else {
            if (super.X >= 0) {
                int newFigureX = super.X + 1;
                int newFigureY = super.Y - 1;

                if (newFigureY + newFigureHeight > fieldHeight) {
                    return false;
                }

                super.X = newFigureX;
                super.Y = newFigureY;
                presentation = new boolean[][]{{true}, {true}, {true}, {true}};
                isVertical = true;
            }
        }
        return true;
    }

    @Override
    public Figure clone() {
        return new Stick(this);
    }
}
