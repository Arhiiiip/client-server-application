package data;


import java.io.Serializable;

public class Location implements Serializable {

    private Integer x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 825, Поле не может быть null

    public Location(Integer x, Float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Location() {
        y = null;
        x = null;
        name = null;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
