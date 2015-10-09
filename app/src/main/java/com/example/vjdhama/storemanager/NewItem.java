package com.example.vjdhama.storemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewItem extends AppCompatActivity {

    EditText itemNameEditText;
    EditText itemQuantityEditText;
    EditText itemExpiryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewsById();

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                itemExpiryEditText.setText(sdf.format(myCalendar.getTime()));
            }
        };

        itemExpiryEditText.setOnTouchListener(new View.OnTouchListener() {
                                                  @Override
                                                  public boolean onTouch(View v, MotionEvent event) {
                                                      new DatePickerDialog(NewItem.this, date, myCalendar
                                                              .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                                              myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                                                      return false;
                                                  }
                                              }
        );
    }

    private void findViewsById() {
        itemNameEditText = (EditText) findViewById(R.id.item_name_edit_text);
        itemQuantityEditText = (EditText) findViewById(R.id.item_quantity_edit_text);
        itemExpiryEditText = (EditText) findViewById(R.id.item_expiry_edit_text);
    }

}
