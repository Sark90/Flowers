package com.commerce.flowers;

import com.commerce.log.EventLogException;
import java.util.ArrayList;

public class Bouquet {
    private ArrayList<Flower> bouquet;
    private int index;
    private double price;
    public Bouquet() {
        bouquet = new ArrayList<>();
        price = index = 0;
    }
    public double getPrice() {
        price=0;
        for (Flower f : bouquet) {
            price += f.getPricePerOne();
        }
        return price;
    }

    public void addFlower(Flower flower) throws EventLogException {
        if (flower == null) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException npe) {
                throw new EventLogException(npe, EventLogException.ADD_NE_FLOWER, true);
            }
        }
        System.out.println("Add 1 " + flower.getMainFlowerColor() + " " + flower.getFlowerName());
        bouquet.add(flower);
        try {
            throw new EventLogException(EventLogException.ADD_FLOWER);
        } catch (EventLogException e) {
            e.printLog();
        }
    }

    public void showBouquetInfo() {
        System.out.println("Number of flowers: " + bouquet.size() + ".\nFlowers in bouquet:");
        for(Flower f : bouquet) {
            System.out.println("\t" + f.getFullFlowerColor() + " " + f.getFlowerFullName());
        }
    }
}
