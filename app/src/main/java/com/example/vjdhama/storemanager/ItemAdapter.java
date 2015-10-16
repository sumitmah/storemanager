package com.example.vjdhama.storemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vjdhama.storemanager.realm.models.Item;

import java.util.ArrayList;

/**
 * Created by vjdhama on 09/10/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    Context context;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.item_view, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ItemViewHolder viewHolder; // view lookup cache stored in tag
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
        // Populate the data into the template view using the data object
        viewHolder.name.setText(item.getName());
//        viewHolder.expiry.setText((CharSequence) item.getExpiry());
        viewHolder.quantity.setText(item.getQuantity());

        return convertView;
    }
}

class ItemViewHolder {
    TextView name;
    TextView expiry;
    TextView quantity;
}