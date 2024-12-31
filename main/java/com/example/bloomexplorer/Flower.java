package com.example.bloomexplorer;

public class Flower {
    private String name;
    private String imageUrl;

    public Flower(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
