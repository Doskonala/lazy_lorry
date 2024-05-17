package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTripActivity extends AppCompatActivity {

    EditText freight, lorry, driver, origin, destination, distance, date, time;
    Button add_trip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        freight = findViewById(R.id.freight_id);
        lorry = findViewById(R.id.lorry_number);
        driver = findViewById(R.id.driver_number);
        origin = findViewById(R.id.origin);
        destination = findViewById(R.id.destination);
        distance = findViewById(R.id.distance);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        add_trip_button = findViewById(R.id.btn_add_trip);
        add_trip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if (freight.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть номер замовлення", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (lorry.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть номер вантажівки", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (driver.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть номер посвідчення водія", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (origin.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть адресу місця відправки", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (destination.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть адресу місця доставки", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (distance.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть відстань (км)", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (date.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть дату виїзду", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (time.getText().toString().equals("")) {
                    Toast.makeText(AddTripActivity.this, "Введіть час виїзду", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddTripActivity.this);
                    myDB.addTrip(Integer.valueOf(freight.getText().toString().trim()),
                            lorry.getText().toString().trim(),
                            driver.getText().toString().trim(),
                            origin.getText().toString().trim(),
                            destination.getText().toString().trim(),
                            Double.valueOf(destination.getText().toString().trim()),
                            date.getText().toString(),
                            time.getText().toString());
                }
            }
        });
    }
}