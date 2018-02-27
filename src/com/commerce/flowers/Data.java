package com.commerce.flowers;

import java.io.*;

public class Data {
    public static final double DEFAULT_PRICE = 25; //TODO: task 2 - default price
    public final static String FILE = "prices.txt";
    private double price;
    private String flowerName, color;

    public Data(String name, String color, double price) {
        flowerName = name;
        this.color = color;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public String getColor() {
        return color;
    }

    public static void write(Data data) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static double readPrice(String name, String color) {
        Data data = null;
        FileOutputStream fos = null;
        ObjectInputStream oin = null;
        FileInputStream fis = null;
/*        try {
            fos = new FileOutputStream(FILE, true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }*/
        try {
            fis = new FileInputStream(FILE);
            oin = new ObjectInputStream(fis);
        } catch (IOException ioe) {
            //System.out.println(ioe);
            return DEFAULT_PRICE;
        }
        /*if (oin == null) {
            return DEFAULT_PRICE;
        }*/
        do {
            try {
                data = (Data) oin.readObject();
            } catch (EOFException e) {
                System.out.println("EOF");
            } catch (IOException | ClassNotFoundException ioe) {
                System.out.println(ioe);
            }
        } while (!data.getFlowerName().equals(name) && !data.getColor().equals(color));
            //fis.close();
            //oin.close();
        try {}
        finally {
            try {
                oin.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("Closing ObjectInputStream exception: " + e);
            }
            if (data != null) {
                return data.getPrice();
            } else {
                return DEFAULT_PRICE;
            }
        }
    }
}
