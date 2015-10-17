package com.example.vjdhama.storemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vjdhama.storemanager.realm.models.Item;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ListView itemsListView = (ListView) findViewById(R.id.items_list_view);
        load_items(itemsListView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        NewItemActivity.class);
                startActivity(i);
            }
        });

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemAdapter adapter = (ItemAdapter)parent.getAdapter();
                Item item = (Item) adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, ShowItemActivity.class);
                intent.putExtra("item", item.getName());
                startActivity(intent);

                Log.d("adapter", item.getName());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView itemsListView = (ListView) findViewById(R.id.items_list_view);
        load_items(itemsListView);
    }

    private void load_items(ListView itemsListView) {
        ArrayList<Item> items = new ArrayList<Item>();

        realm = Realm.getInstance(this);
        RealmResults<Item> results = realm.where(Item.class).findAll();

        for (Item item : results) {
            items.add(item);
        }

        itemsListView.setAdapter(new ItemAdapter(this.getBaseContext(), items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
