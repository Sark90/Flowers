package com.commerce.flowers;

import com.commerce.annotations.Change;
import java.io.Serializable;

public abstract class Flower implements Serializable {
    private int leavesSize, flowerSize;
    @Change(change = false, maxValue = 85)
    private int height;
    private String flowerName;
    private String mainFlowerColor;
    @Change(change = true, maxValue = 100)
    private double pricePerOne;

    protected Flower(String flowerName, int leavesSize, int flowerSize, int height, String mainFlowerColor) {    //BUILDER?
        this.flowerName = flowerName;
        this.leavesSize = leavesSize;
        this.flowerSize = flowerSize;
        this.height = height;
        this.mainFlowerColor = mainFlowerColor;
        this.pricePerOne = Data.readPrice(this.flowerName, this.mainFlowerColor);
    }

    public void setHeight(int height) {
            if (height<this.height) {
                this.height = height;
            }
    }

    public void setPricePerOne(double pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public double getPricePerOne() {
        return pricePerOne;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public int getHeight() {
        return height;
    }

    public String getMainFlowerColor() {
        return mainFlowerColor;
    }
    public String getFullFlowerColor() { return getMainFlowerColor(); }
    public abstract String getFlowerFullName();
}
