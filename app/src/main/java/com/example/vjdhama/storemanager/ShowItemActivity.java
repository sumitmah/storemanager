package com.example.vjdhama.storemanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.vjdhama.storemanager.realm.models.Item;
import com.example.vjdhama.storemanager.views.ShowItemView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ShowItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ShowItemView viewHolder = new ShowItemView(this);

        Intent intent = getIntent();
        String itemName = (String) intent.getExtras().get("item");
        Realm realm = Realm.getInstance(this);
        RealmQuery<Item> items = realm.where(Item.class);
        Item item = items.equalTo("name", itemName).findFirst();

        viewHolder.setExpiry(item.getExpiryDate());
        viewHolder.setQuantity(item.getQuantity());
        viewHolder.setName(item.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
