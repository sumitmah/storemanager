package com.example.vjdhama.storemanager;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vjdhama.storemanager.realm.models.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ItemAdapter extends ArrayAdapter<Item> {
    Context context;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.item_view, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        ItemViewHolder viewHolder;
        
        if (convertView == null) {
            viewHolder = new ItemViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_view, null);
            viewHolder.name = (TextView) convertView
                    .findViewById(R.id.item_name_text_view);
            viewHolder.quantity= (TextView) convertView
                    .findViewById(R.id.item_quantity_text_view);
            viewHolder.expiry = (TextView) convertView
                    .findViewById(R.id.item_expiry_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) convertView.getTag();
        }

        viewHolder.setName(item.getName());
        viewHolder.setQuantity(item.getQuantity());
        viewHolder.setExpiry(item.getExpiryDate());

        return convertView;
    }
}

class ItemViewHolder {
    TextView name;
    TextView expiry;
    TextView quantity;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public void setExpiry(Date expiryDate) {
        Calendar c = Calendar.getInstance();
        String relativeExpiryTimeSpan = DateUtils.getRelativeTimeSpanString(expiryDate.getTime(),
                c.getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS).toString();

        if (c.getTime().compareTo(expiryDate) <= 0) {
            this.expiry.setText("Will be expired " + relativeExpiryTimeSpan);
        } else {
            this.expiry.setText("Expired " + relativeExpiryTimeSpan);
        }
    }
}