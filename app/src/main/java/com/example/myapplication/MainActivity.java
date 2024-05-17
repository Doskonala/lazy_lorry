package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    RecyclerView recyclerTripsView;
    FloatingActionButton add_trip_button;
    MyDatabaseHelper myDB;
    ArrayList trip_id, order_id, lorry_number, driver_number, driver_name, origin, destination, distance, date, time;
    TripsCustomAdapter tripsCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        menu=findViewById(R.id.menu);
        timetable=findViewById(R.id.timetable);
        messages=findViewById(R.id.messages);
        orders=findViewById(R.id.orders);
        lorries=findViewById(R.id.lorries);
        drivers=findViewById(R.id.drivers);
        clients=findViewById(R.id.clients);
        partners=findViewById(R.id.partners);
        company=findViewById(R.id.company);
        logout=findViewById(R.id.logout);
        recyclerTripsView = findViewById(R.id.recyclerTripsView);
        add_trip_button = findViewById(R.id.add_trip_button);
        add_trip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTripActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        trip_id = new ArrayList<>();
        order_id = new ArrayList<>();
        lorry_number = new ArrayList<>();
        driver_number = new ArrayList<>();
        driver_name = new ArrayList<>();
        origin = new ArrayList<>();
        destination = new ArrayList<>();
        distance = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, MessagesActivity.class);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, OrdersActivity.class);
            }
        });
        lorries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, LorriesActivity.class);
            }
        });
        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, DriversActivity.class);
            }
        });
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, ClientsActivity.class);
            }
        });
        partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, PartnersActivity.class);
            }
        });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, CompanyActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Ви вийшли з системи",Toast.LENGTH_SHORT).show();
            }
        });

        storeTripsInArrays();
        tripsCustomAdapter = new TripsCustomAdapter(MainActivity.this, trip_id, order_id, lorry_number, driver_number, driver_name, origin, destination, distance, date, time);
        recyclerTripsView.setAdapter(tripsCustomAdapter);
        recyclerTripsView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeTripsInArrays () {
        Cursor cursor = myDB.readAllTrips();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Дані про перевезення відсутні", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                trip_id.add(cursor.getString(0));
                order_id.add(cursor.getString(1));
                lorry_number.add(cursor.getString(2));
                driver_number.add(cursor.getString(3));
                driver_name.add(cursor.getString(4));
                origin.add(cursor.getString(5));
                destination.add(cursor.getString(6));
                distance.add(cursor.getString(7));
                date.add(cursor.getString(8));
                time.add(cursor.getString(9));
            }
        }
    }

    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}