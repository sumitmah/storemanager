package com.example.vjdhama.storemanager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import io.realm.Realm;
import com.example.vjdhama.storemanager.realm.models.Item;

public class NewItemActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemQuantityEditText;
    private EditText itemExpiryEditText;
    private Button addItemButton;
    private final String myFormat = "MM/dd/yy";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

    Realm realm;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewsById();
        realm= Realm.getInstance(this);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                itemExpiryEditText.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        itemExpiryEditText.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      new DatePickerDialog(NewItemActivity.this, date, myCalendar
                                                              .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                                              myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                                                  }
                                              }
        );

        context = getApplicationContext();

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    realm.beginTransaction();
                    Item item = realm.createObject(Item.class);
                    item.setName(itemNameEditText.getText().toString());
                    item.setCreatedDate(myCalendar.getTime());
                    item.setExpiryDate(dateFormat.parse(itemExpiryEditText.getText().toString()));
//                    item.setPurchaseDate();
                    item.setQuantity(itemQuantityEditText.getText().toString());
                    realm.commitTransaction();
                    Log.d("realm", "successfully saved item");
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void findViewsById() {
        itemNameEditText = (EditText) findViewById(R.id.item_name_edit_text);
        itemQuantityEditText = (EditText) findViewById(R.id.item_quantity_edit_text);
        itemExpiryEditText = (EditText) findViewById(R.id.item_expiry_edit_text);
        addItemButton = (Button) findViewById(R.id.add_item_button);
    }
}
