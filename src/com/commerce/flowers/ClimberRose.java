package com.commerce.flowers;

public class ClimberRose extends Rose {
    private String flowersSize;
    public ClimberRose() {
        super("climber rose", 15, 40, "orange", false);
        flowersSize = "small";
    }
    public ClimberRose(String color) {
        super("climber rose", 15, 40, color, false);
        flowersSize = "small";
    }

    @Override
    public String getFlowerFullName() {
        return super.getFlowerFullName() + " and with " + flowersSize + " flowers";
    }
}
