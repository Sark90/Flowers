package com.commerce.sale;

import com.commerce.Request;
import com.commerce.flowers.Bouquet;
import com.commerce.flowers.Flower;

public interface Seller {
    public Bouquet createBouquet(Request ... r);
    public void saleBouquet(Bouquet ... bouquet);
    public void showStorehouse();
    public void setHeight(Request r, int height);
}
