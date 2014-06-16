package model;

import model.Figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFigureGenerator {

    private final List<Figure> figures;
    private final Random random;

    public RandomFigureGenerator(final int fieldWidth, final int fieldHeight) {

        random = new Random();
        figures = new ArrayList<>();

        figures.add(new CaneLeft(fieldWidth, fieldHeight));
        figures.add(new CaneRight(fieldWidth, fieldHeight));
        figures.add(new Square(fieldWidth, fieldHeight));
        figures.add(new Stick(fieldWidth, fieldHeight));
        figures.add(new TFigure(fieldWidth, fieldHeight));
        figures.add(new ZLeftFigure(fieldWidth, fieldHeight));
        figures.add(new ZRightFigure(fieldWidth, fieldHeight));
    }

    public Figure getRandomFigure() {
        return figures.get(random.nextInt(figures.size())).clone();
    }
}
