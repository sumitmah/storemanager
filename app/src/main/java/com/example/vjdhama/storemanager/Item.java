package com.example.vjdhama.storemanager;

import java.util.Date;

/**
 * Created by vjdhama on 09/10/15.
 */
public class Item {
    private String name;
    private String quantity;
    private Date expiry;


    Item(String name, String quantity, Date expiry){
        this.name = name;
        this.quantity = quantity;
        this.expiry = expiry;
    }


    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public Date getExpiry() {
        return expiry;
    }

}
