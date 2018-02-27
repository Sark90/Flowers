package com.commerce.flowers;

public class Rose extends Flower {
    private boolean hasThorns;

    public Rose(String flowerName, int height, int price, String color, boolean hasThorns) {
        super(flowerName, 3, 6, height, color);
        this.hasThorns = hasThorns;
    }
    public Rose() {
        super("rose", 3, 6, 60, "red");
        hasThorns = true;
    }
    public Rose(String color) {
        super("rose", 3, 6, 60, color);
        hasThorns = true;
    }

    @Override
    public String getFlowerFullName() {
        if(hasThorns) return super.getFlowerName() + " with thorns";
        else return super.getFlowerName() + " without thorns";
    }
}
