package com.example.listfiewtest;

class Fruit extends Object {
    private String name;
    private int imageId;
    Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
