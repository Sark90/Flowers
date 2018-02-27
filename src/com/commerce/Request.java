package com.commerce;

import java.util.Random;

public class Request {
    private int number;
    private String color;
    private String flowerType;
    private double price;
    protected static String[] flowerTypes = {"rose", "climber rose", "tulip", "pink"};
    protected static String[] flowerColors = {"white", "red", "rosy",
                                              "yellow", "orange", "blue" };
    public static final Request rNotCorrect = new Request(2, "black", "violet"); //public -> private
    private static final Request requests[] = new Request[10];
    static {
        requests[0] = new Request(40, "white", "climber rose");
        requests[1] = new Request(20, "rosy", "climber rose");
        requests[2] = new Request(20, "red", "rose");
        requests[3] = new Request(20, "blue", "rose");
        requests[4] = new Request(50, "orange", "tulip");
        requests[5] = new Request(50, "yellow", "tulip");
        requests[6] = new Request(60, "blue", "pink");
        requests[7] = new Request(10, "red", "pink");
        requests[8] = new Request(20, "orange", "rose");
        requests[9] = new Request(10, "yellow", "pink");
    }
    public static Request[] getRequests() {
        return requests;
    }

    private Request() {}
    public Request(int number, String color, String flowerType) {
        this.number = number;
        this.color = color;
        this.flowerType = flowerType;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public String getFlowerType() {
        return flowerType;
    }

    public static Request[] getRandRequest() {
        Request randArr[] = new Request[5];
        for (int i=0; i<randArr.length; i++) {
            Random rand = new Random();
            int num = rand.nextInt(15);
            //if(num%2==0) num++;
            String type, flowerColor;
            type = Request.flowerTypes[rand.nextInt(Request.flowerTypes.length)];
            flowerColor = Request.flowerColors[rand.nextInt(Request.flowerColors.length)];
            randArr[i] = new Request(num, flowerColor, type);
        }
        return randArr;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
