package com.commerce.property;

import com.commerce.log.EventLogException;

public interface Owner {
    public void setPrice(String flowerType, String color) throws EventLogException;
    public void setPricePerOne(String flowerType, String color, double price) throws EventLogException;
    public void showProfit();
    public double getSummaryCosts();
}
