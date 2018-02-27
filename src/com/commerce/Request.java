package com.commerce;

import java.util.ArrayList;
import java.util.Random;

public class Request {
    private int number;
    private String color;
    private String flowerType;
    private static String[] flowerTypes = {"rose", "climber rose", "tulip", "pink"};
    private static String[] flowerColors = {"white", "red", "rosy", "yellow", "orange", "blue" };
    public static final Request rNotCorrect = new Request(2, "black", "violet"); //public -> private
    private static final ArrayList<Request> requests = new ArrayList<>();
    static {
        requests.add(new Request(40, "white", "climber rose"));
        requests.add(new Request(20, "rosy", "climber rose"));
        requests.add(new Request(20, "red", "rose"));
        requests.add(new Request(20, "blue", "rose"));
        requests.add(new Request(50, "orange", "tulip"));
        requests.add(new Request(50, "yellow", "tulip"));
        requests.add(new Request(60, "blue", "pink"));
        requests.add(new Request(10, "red", "pink"));
        requests.add(new Request(20, "orange", "rose"));
        requests.add(new Request(10, "yellow", "pink"));
    }
    public static ArrayList<Request> getRequests() {
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
}
