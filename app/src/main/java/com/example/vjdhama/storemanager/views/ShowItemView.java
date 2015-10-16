package com.example.vjdhama.storemanager.views;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.example.vjdhama.storemanager.R;

import java.util.Date;

public class ShowItemView {
    private TextView name;
    private TextView expiry;
    private TextView quantity;

    public ShowItemView(Activity activity) {
        name = (TextView) activity.findViewById(R.id.item_name_text_view);
        quantity = (TextView) activity.findViewById(R.id.item_quantity_text_view);
        expiry = (TextView) activity.findViewById(R.id.item_expiry_text_view);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setExpiry(Date expiryDate) {
        this.expiry.setText(expiryDate.toString());
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }
}