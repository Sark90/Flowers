package com.commerce;

import com.commerce.annotations.Change;
import com.commerce.flowers.Bouquet;
import com.commerce.flowers.Flower;
import com.commerce.log.EventLogException;
import com.commerce.property.Owner;
import com.commerce.sale.Seller;
import com.commerce.supply.Supplier;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.commerce.Request.rNotCorrect;

public class Demo {
    private Seller seller;
    private Owner owner;
    private Supplier supplier;
    private Bouquet bouquets[];
    private ArrayList<Request> requests;
    private double costsBefore, costsAfter;

    Demo() {
        FlowerShop fs = new FlowerShop();
        seller = fs;
        owner = fs;
        supplier = fs;
        fs = null;
        requests = Request.getRequests();
    }
    public void start() {
        //System.out.println("Supply flowers & Put flowers to the storehouse");
        badDelivery();
        badSetPrice();
        delivery();
        seller.showStorehouse();
        for (Field f: getAnnotatedFields()) {
            if (f != null) {
                if (f.getName().equals("height")) {
                    seller.setHeight(requests.get(9), 30);  //change=false
                }
                if (f.getName().equals("pricePerOne")) {
                    costsBefore = reflectGetCosts(); //reflection
                    setPrice();  //change=true
                    costsAfter = reflectGetCosts(); //reflection
                    showDifference();
                }
            }
        }
        seller.showStorehouse();
        badCreateBouquet();
        createRandomBouquets();
        seller.showStorehouse();
        saleAllBouquets();
        owner.showProfit();
    }
    private Field[] getAnnotatedFields() {
        Field[] fields = Flower.class.getDeclaredFields();
        Field[] annoFields = new Field[fields.length];
        for(int i=0; i<fields.length; i++) {
            if(fields[i].isAnnotationPresent(Change.class)) {
                annoFields[i] = fields[i];
                //System.out.println(annoFields[i].getName());
            }
        }
        return annoFields;
    }
    private void badDelivery() {
        try {
            supplier.deliverFlowers(rNotCorrect); //exception 2
        } catch (EventLogException e) {
            e.printLog();
            /*System.out.println("Catched exception (reflection):");
            e.reflectClassInfo();*/
        }
    }
    private void badSetPrice() {
        try {
            owner.setPricePerOne(rNotCorrect.getFlowerType(), rNotCorrect.getColor(), 18); //exception 1
        } catch (EventLogException e) {
            e.printLog();
        }
    }
    private void delivery() {
        for (Request r: requests) {
            try {
                supplier.deliverFlowers(r);
            } catch (EventLogException e) {
                e.printLog();
            }
        }
    }
    private void setPrice() {
        System.out.println("Set price");
        for(Request r : requests) {
            try {
                owner.setPrice(r.getFlowerType(), r.getColor());
            } catch (EventLogException e) { e.printLog(); }
        }
    }
    public void showDifference() {
        System.out.println("\tCosts before setting price: " + costsBefore);
        System.out.println("\tCosts after setting price: " + costsAfter);
        System.out.println("\tDifference: " + (costsAfter - costsBefore));
    }
    private void badCreateBouquet() {
        Bouquet bNotCorrect = seller.createBouquet(rNotCorrect);    //exception 0
    }
    private void createRandomBouquets() {
        bouquets = new Bouquet[70];
        for(int i=0; i<bouquets.length; i++) {
            bouquets[i] = seller.createBouquet(Request.getRandRequest());
            if(bouquets[i].getPrice()!=0) {
                bouquets[i].showBouquetInfo();
                System.out.println("The price of the bouquet: " + bouquets[i].getPrice() + "\n");
            }
        }
    }
    private void saleAllBouquets() {
        if(bouquets.length==0) return;
        System.out.println("Sale all bouquets");
        seller.saleBouquet(bouquets);
    }
    private double reflectGetCosts() {
        Method method;
        try {  //reflection
            method = FlowerShop.class.getDeclaredMethod("getSummaryCosts");
            return (double) method.invoke(owner);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
