package model;

import lombok.Data;

public enum DishType {

    BEEF ("Beef"),
    CHICKEN ("Chicken"),
    VEGETABLES("Vegetables");


    String name;
    DishType(String name) {
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DishType{" +
                "name='" + name + '\'' +
                '}';
    }
}
