package data;

import java.io.Serializable;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Float weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, Float weight, Color eyeColor, Country nationality, Location location) {
        this.name = name;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
        name = null;
        weight = null;
        eyeColor = null;
        nationality = null;
        location = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Float getWeight() {
        return weight;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", weight: " + weight +
                ", eyeColor: " + eyeColor +
                ", nationality: " + nationality +
                ", location: " + location;
    }
}
