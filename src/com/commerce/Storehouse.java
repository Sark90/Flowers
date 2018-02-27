package com.commerce;

import com.commerce.annotations.Change;
import com.commerce.flowers.*;
import com.commerce.log.EventLogException;
import java.util.ArrayList;

public class Storehouse {
    private static final int MAX_NUM = 100;
    private ArrayList<Rose> roses;
    private ArrayList<ClimberRose> clroses;
    private ArrayList<Tulip> tulips;
    private ArrayList<Pink> pinks;

    protected Storehouse() {
        roses = new ArrayList<>();
        clroses = new ArrayList<>();
        tulips = new ArrayList<>();
        pinks = new ArrayList<>();
    }

    protected int getNumber(String flowerType) {
        switch (flowerType) {
            case "rose":
                if (roses.size()!=0) {return roses.size();}
                else {return 0;}
            case "climber rose":
                if (clroses.size()!=0) {return clroses.size();}
                else {return 0;}
            case "tulip":
                if (tulips.size()!=0) {return tulips.size();}
                else {return 0;}
            case "pink":
                if (pinks.size()!=0) {return pinks.size();}
                else {return 0;}
            default:
                System.out.println("getNumber: no such flower. ");
                return 0;
        }
    }
    protected double getCosts(String flowerType) {
        double costs=0;
        switch (flowerType) {
            case "rose":
                if(roses.size()!=0) {
                    for (Rose rose : roses) {
                        costs += rose.getPricePerOne();
                    }
                    return costs;
                } else { return 0; }
            case "climber rose":
                if(clroses.size()!=0) {
                    for (ClimberRose cr : clroses) {
                        costs += cr.getPricePerOne();
                    }
                    return costs;
                } else { return 0; }
            case "tulip":
                if(tulips.size()!=0) {
                    for (Tulip t : tulips) {
                        costs += t.getPricePerOne();
                    }
                    return costs;
                } else { return 0; }
            case "pink":
                if(pinks.size()!=0) {
                    for (Pink p : pinks) {
                        costs += p.getPricePerOne();
                    }
                    return costs;
                } else { return 0; }
            default:
                System.out.println("getCosts: no such flower. ");
                return 0;
        }
    }

    protected void putFlower(Flower flower) {
        switch (flower.getFlowerName()) {
            case "rose":
                if (roses.size()== MAX_NUM-10) {
                    System.out.println("Storehouse: no more than 10 available places for roses left!");
                }
                if(roses.size()>= MAX_NUM) {
                    System.out.println("Storehouse: no place for roses");
                }
                else {
                        roses.add(new Rose(flower.getMainFlowerColor()));
                }
                break;
            case "climber rose":
                if (clroses.size()== MAX_NUM-10) {
                    System.out.println("Storehouse: no more than 10 available places for climber roses left!");
                }
                if(clroses.size()>= MAX_NUM) {
                    System.out.println("Storehouse: no place for climber roses");
                }
                else {
                    clroses.add(new ClimberRose(flower.getMainFlowerColor()));
                }
                break;
            case "tulip":
                if (tulips.size()== MAX_NUM-10) {
                    System.out.println("Storehouse: no more than 10 available places for tulips left!");
                }
                if(tulips.size()>= MAX_NUM) {
                    System.out.println("Storehouse: no place for tulips!");
                }
                else {
                    tulips.add(new Tulip(flower.getMainFlowerColor()));
                }
                break;
            case "pink":
                if (pinks.size()== MAX_NUM-10) {
                    System.out.println("Storehouse: no more than 10 available places for roses left!");
                }
                if(pinks.size()>= MAX_NUM) {
                    System.out.println("Storehouse: no place for pinks!");
                }
                else {
                    pinks.add(new Pink(flower.getMainFlowerColor()));
                }
                break;
            default: {
                System.out.println("Storehouse.putFlowers: " + flower.getFlowerName() + " - no such flower");
            }
        }
    }
    protected Flower getFlower(String flowerType, String color) throws EventLogException {
        switch (flowerType) {
            case "rose":
                if(roses.size()==0) {
                    System.out.println("No roses at the storehouse!");
                    return null;
                } else {
                    Rose r;
                    for (int i=0; i<roses.size(); i++) {
                        if (roses.get(i).getMainFlowerColor().equals(color)) {
                            r = roses.get(i);   //MB, rewrite constructor Rose() for copying object
                            roses.remove(i);
                            return r;
                        }
                    }
                    System.out.println("No " + color + " roses at the storehouse");
                    return null;
                }
            case "climber rose":
                if(clroses.size()==0) {
                    System.out.println("No climber roses at the storehouse!");
                    return null;
                } else {
                    ClimberRose cr;
                    for(int i=0; i<clroses.size(); i++) {
                        if(clroses.get(i).getMainFlowerColor().equals(color)) {
                            cr = clroses.get(i);
                            clroses.remove(i);
                            return cr;
                        }
                    }
                    System.out.println("No " + color + " climber roses at the storehouse");
                    return null;
                }
            case "tulip":
                if(tulips.size()==0) {
                    System.out.println("No tulips at the storehouse!");
                    return null;
                } else {
                    Tulip t;
                    for(int i=0; i<tulips.size(); i++) {
                        if(tulips.get(i).getMainFlowerColor().equals(color)) {
                            t = tulips.get(i);
                            tulips.remove(i);
                            return t;
                        }
                    }
                    System.out.println("No " + color + " tulips at the storehouse");
                    return null;
                }
            case "pink":
                if(pinks.size()==0) {
                    System.out.println("No pinks at the storehouse!");
                    return null;
                } else {
                    Pink p;
                    for(int i=0; i<pinks.size(); i++) {
                        if(pinks.get(i).getMainFlowerColor().equals(color)) {
                            p = pinks.get(i);
                            pinks.remove(i);
                            return p;
                        }
                    }
                    System.out.println("No " + color + " pinks at the storehouse");
                    return null;
                }
            default:
                System.out.println("getFlower: " + color + " " + flowerType + " - no such flower");
                return null;
        }
    }
    protected void setRosesPrice(String color, double price) {
        if(roses.size() == 0) {
            System.out.println("No roses at the storehouse");
        } else {
            for (Rose r: roses) {
                if (r.getMainFlowerColor().equals(color)) {
                    r.setPricePerOne(price);
                }
            }
        }
    }
    protected void setClRosesPrice(String color, double price) {
        if(clroses.size() == 0) {
            System.out.println("No climber roses at the storehouse");
        } else {
            for (ClimberRose cr : clroses) {
                if (cr.getMainFlowerColor().equals(color)) {
                    cr.setPricePerOne(price);
                }
            }
        }
    }
    protected void setTulipsPrice(String color, double price) {
        if(tulips.size() == 0) {
            System.out.println("No tulips at the storehouse");
        } else {
            for (Tulip t : tulips) {
                if (t.getMainFlowerColor().equals(color)) {
                    t.setPricePerOne(price);
                }
            }
        }
    }
    protected void setPinksPrice(String color, double price) {
        if(pinks.size() == 0) {
            System.out.println("No pinks at the storehouse");
        } else {
            for (Pink p : pinks) {
                if (p.getMainFlowerColor().equals(color)) {
                    p.setPricePerOne(price);
                }
            }
        }
    }
    protected void setHeight(String flowerType, int height) {
        Change changeHeight = null;
        try {
            changeHeight = Flower.class.getDeclaredField("height").getAnnotation(Change.class);
        } catch (NoSuchFieldException e) {
            System.out.println(e);
        }
        if(changeHeight==null || !changeHeight.change()) return;
        Flower[] flowers = new Flower[0];
        switch (flowerType) {
            case "rose":
                flowers = (Flower[]) roses.toArray();
                break;
            case "climber rose":
                flowers = (Flower[]) clroses.toArray();
                break;
            case "tulip":
                flowers = (Flower[]) tulips.toArray();
                break;
            case "pink":
                flowers = (Flower[]) pinks.toArray();
                break;
            default:
                System.out.println("setHeight: " + flowerType + " - no such flower");   //to EvenLog
        }
        for(Flower f : flowers) {
            if (f == null) return;
            System.out.println(f.getFlowerName() + " height before: " + f.getHeight());
            if (height>changeHeight.maxValue()) {
                f.setHeight(changeHeight.maxValue());
            }
            else {
                f.setHeight(height);
            }
            System.out.println(f.getFlowerName() + " height after: " + f.getHeight());
        }
    }
}
