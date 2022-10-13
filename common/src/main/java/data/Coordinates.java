package data;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private int x; //Максимальное значение поля: 316
    private Integer y; //Поле не может быть null

    public Coordinates(int x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
