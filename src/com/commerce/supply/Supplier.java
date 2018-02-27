package com.commerce.supply;
import com.commerce.log.EventLogException;
import com.commerce.Request;


public interface Supplier {
   public void deliverFlowers(Request r/*, double cost*/) throws EventLogException;
}
