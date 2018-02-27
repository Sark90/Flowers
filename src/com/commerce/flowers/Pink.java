package com.commerce.flowers;

public class Pink extends Flower {
    private String secondFlowerColor;

    public Pink() {
        super("pink", 5, 5, 40, "rosy");
        secondFlowerColor = "white";
    }
    public Pink(String color) {
        super("pink", 5, 5, 40, color);
        if(!color.equals("white")) {
            secondFlowerColor = "white";
        } else {
            secondFlowerColor = "blue";
        }
    }
    public Pink(String mainFlowerColor, String secondFlowerColor) {
        super("pink", 5, 5, 40, mainFlowerColor);
        if(!secondFlowerColor.equals(super.getMainFlowerColor())) {
            this.secondFlowerColor = secondFlowerColor;
        } else {
            this.secondFlowerColor = "";
        }
    }

    protected void setSecondFlowerColor(String secondFlowerColor) {
        if(!secondFlowerColor.equals(super.getMainFlowerColor())) {
            this.secondFlowerColor = secondFlowerColor;
        } else {
            this.secondFlowerColor = "";
        }
    }

    @Override
    public String getFullFlowerColor() {
        if(!secondFlowerColor.equals("")) {
            return super.getMainFlowerColor() + "-" + secondFlowerColor;
        } else {
            return super.getMainFlowerColor();
        }
    }

    @Override
    public String getFlowerFullName() {
        return super.getFlowerName();
    }

}
