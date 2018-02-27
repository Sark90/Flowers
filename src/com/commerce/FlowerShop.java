package com.commerce;

import com.commerce.annotations.Change;
import com.commerce.flowers.*;
import com.commerce.log.EventLogException;
import com.commerce.property.Owner;
import com.commerce.sale.Seller;
import com.commerce.supply.Supplier;

import java.util.Scanner;

public class FlowerShop implements Seller, Owner, Supplier {
    private double proceeds;    //выручка
    private double costs;   //себестоимость/расходы
    //private double profit;  //прибыль
    //private Bouquet bouquets[];
    private Storehouse sh;

    protected FlowerShop() {
        proceeds = costs = 0;
        sh = new Storehouse();
    }

    @Override
    public Bouquet createBouquet(Request ... r) {
        try {
            throw new EventLogException(EventLogException.CREATE_BOUQUET);
        } catch (EventLogException e) {
            e.printLog();
        }
        //System.out.println("Creating a bouquet");
        Bouquet bouquet = new Bouquet();
        for (int i=0; i<r.length; i++) {
            inner: for(int j=0; j<r[i].getNumber(); j++) {
                Flower f = null;
                try {
                    f = sh.getFlower(r[i].getFlowerType(), r[i].getColor());
                } catch (EventLogException e) {
                    e.printLog();
                }
                try {
                    bouquet.addFlower(f);
                } catch (EventLogException e) {
                    e.printLog();
                    break inner;
                }
            }
        }
        return bouquet;
    }

    @Override
    public void saleBouquet(Bouquet ... bouquet) {
        for(Bouquet b: bouquet) {
            proceeds += b.getPrice();
        }
    }

    @Override
    public void setPrice(String flowerType, String color) throws EventLogException {
        switch (flowerType) {
            case "rose": break;
            case "climber rose": break;
            case "tulip": break;
            case "pink": break;
            default:
                //System.out.println("setPricePerOne: " + color + " " + flowerType + " - no such flower");
                throw new EventLogException(EventLogException.SET_PRICE_TO_NEF, true);
        }
        Change changePrice = null;
        try {
            changePrice = Flower.class.getDeclaredField("pricePerOne").getAnnotation(Change.class);
        } catch (NoSuchFieldException e) {
            System.out.println(e);
        }
        if(changePrice!=null && changePrice.change()) {
            Scanner scanner = new Scanner(System.in);
            double price = Data.DEFAULT_PRICE;
            System.out.print("Type the new price for " + color + " " + flowerType + "s: ");
            try {
                price = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (price > changePrice.maxValue()) {
                price = changePrice.maxValue();
            }
            setPricePerOne(flowerType, color, price);
            //Data.write(new Data(flowerType, color, price));
        }
    }

    public void setPricePerOne(String flowerType, String color, double price) throws EventLogException {
        switch (flowerType) {
            case "rose":
                sh.setRosesPrice(color, price);
                break;
            case "climber rose":
                sh.setClRosesPrice(color, price);
                break;
            case "tulip":
                sh.setTulipsPrice(color, price);
                break;
            case "pink":
                sh.setPinksPrice(color, price);
                break;
            default:
                //System.out.println("setPricePerOne: " + color + " " + flowerType + " - no such flower");
                throw new EventLogException(EventLogException.SET_PRICE_TO_NEF, true);
        }
        throw new EventLogException(EventLogException.SET_PRICE);
    }

    @Override
    public void deliverFlowers(Request r) throws EventLogException {
        Flower flowers[] = new Flower[0];
        switch (r.getFlowerType()) {
            case "rose":
                flowers = new Rose[r.getNumber()];
                for(int i=0; i<flowers.length; i++) {
                    flowers[i] = new Rose(r.getColor());
                }
                break;
            case "climber rose":
                flowers = new ClimberRose[r.getNumber()];
                for(int i=0; i<flowers.length; i++) {
                    flowers[i] = new ClimberRose(r.getColor());
                }
                break;
            case "tulip":
                flowers = new Tulip[r.getNumber()];
                for(int i=0; i<flowers.length; i++) {
                    flowers[i] = new Tulip(r.getColor());
                }
                break;
            case "pink":
                flowers = new Pink[r.getNumber()];
                for(int i=0; i<flowers.length; i++) {
                    flowers[i] = new Pink(r.getColor());
                }
                break;
            default:
                throw new EventLogException(EventLogException.SUPPLY_NE_FLOWER, true);
                //System.out.println("deliverFlowers: " + r.getColor() + " " + r.getFlowerType() + " - no such flowers");
        }
        if(flowers.length!=0) {
            for (Flower f: flowers) {
                costs += f.getPricePerOne();
                sh.putFlower(f);
            }
            throw new EventLogException(EventLogException.SUPPLY_FLOWER);
        } else {
            throw new EventLogException(EventLogException.SUPPLY_NE_FLOWER, true);
        }
    }

    @Override
    public double getSummaryCosts() {
        return sh.getCosts("rose") + sh.getCosts("climber rose")
                + sh.getCosts("tulip") + sh.getCosts("pink");
    }

    @Override
    public void showStorehouse() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFlowers at the storehouse:");
        sb.append("\n\tRoses: " + sh.getNumber("rose") + ", costs: " + sh.getCosts("rose"));
        sb.append("\n\tClimber roses: " + sh.getNumber("climber rose") + ", costs: " + sh.getCosts("climber rose"));
        sb.append("\n\tTulips: " + sh.getNumber("tulip") + ", costs: " + sh.getCosts("tulip"));
        sb.append("\n\tPinks: " + sh.getNumber("pink") + ", costs: " + sh.getCosts("pink") + "\n");
        sb.append("Costs: " + getSummaryCosts());
        System.out.println(sb);
    }

    @Override
    public void setHeight(Request r, int height) {
        sh.setHeight(r.getFlowerType(), height);
    }

    @Override
    public void showProfit() {
        System.out.println("\nSummary costs: " + costs + "\nSummary proceeds: " + proceeds + "\nProfit: " + (proceeds - costs));
    }
}
