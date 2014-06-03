package model;

import model.Figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFigureGenerateor {

    private final List<Figure> figures;
    private final Random random;

    public RandomFigureGenerateor(int fieldWidth){

        random = new Random();
        figures = new ArrayList<>();

        figures.add(new Cane(fieldWidth));
        figures.add(new Square(fieldWidth));
        figures.add(new Stick(fieldWidth));
        figures.add(new TFigure(fieldWidth));
        figures.add(new ZFigure(fieldWidth));

    }

    public Figure getRandomFigure(){
        return figures.get(random.nextInt(figures.size()));
    }
}
