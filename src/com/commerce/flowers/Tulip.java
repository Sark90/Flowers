package com.commerce.flowers;

public class Tulip extends Flower {
    private boolean hasFringe; //бахрома
    public Tulip() {
        super("tulip", 15, 6, 25, "yellow");
        hasFringe = false;
    }
    public Tulip(boolean hasFringe) {
        super("tulip", 15, 6, 25, "yellow");
        this.hasFringe = hasFringe;
    }
    public Tulip(String color) {
        super("tulip", 15, 6, 25, color);
        hasFringe = false;
    }

    @Override
    public String getFlowerFullName() {
        if(hasFringe) return super.getFlowerName() + " with fringe";
        else return super.getFlowerName() + " without fringe";
    }
}
