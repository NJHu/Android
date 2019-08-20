package com.example.recyclerviewtest;

class Fruit extends Object {
    private String name;
    private int image;
    Fruit(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
