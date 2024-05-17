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

public class LorriesActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    RecyclerView recyclerLorriesView;
    FloatingActionButton add_lorry_button;
    MyDatabaseHelper myDB;
    ArrayList<String> carNumber, loadCapacity, manufacturer, typeOfFreight, typeOfBodywork, yearOfProduction;
    LorriesCustomAdapter lorriesCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorries);
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
        recyclerLorriesView = findViewById(R.id.recyclerLorriesView);
        add_lorry_button = findViewById(R.id.add_lorry_button);
        add_lorry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LorriesActivity.this, AddLorryActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(LorriesActivity.this);
        carNumber = new ArrayList<>();
        loadCapacity = new ArrayList<>();
        manufacturer = new ArrayList<>();
        typeOfFreight = new ArrayList<>();
        typeOfBodywork = new ArrayList<>();
        yearOfProduction = new ArrayList<>();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, MainActivity.class);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, MessagesActivity.class);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, OrdersActivity.class);
            }
        });
        lorries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { recreate(); }
        });
        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, DriversActivity.class);
            }
        });
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, ClientsActivity.class);
            }
        });
        partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, PartnersActivity.class);
            }
        });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LorriesActivity.this, CompanyActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LorriesActivity.this,"Ви вийшли з системи",Toast.LENGTH_SHORT).show();
            }
        });

        storeLorriesInArrays();
        lorriesCustomAdapter = new LorriesCustomAdapter(LorriesActivity.this, carNumber, loadCapacity, manufacturer, typeOfFreight, typeOfBodywork, yearOfProduction);
        recyclerLorriesView.setAdapter(lorriesCustomAdapter);
        recyclerLorriesView.setLayoutManager(new LinearLayoutManager(LorriesActivity.this));
    }

    void storeLorriesInArrays () {
        Cursor cursor = myDB.readAllLorries();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"Дані про вантажівки відсутні",Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){
                carNumber.add(cursor.getString(0));
                loadCapacity.add(cursor.getString(1));
                manufacturer.add(cursor.getString(2));
                typeOfFreight.add(cursor.getString(3));
                typeOfBodywork.add(cursor.getString(4));
                yearOfProduction.add(cursor.getString(5));
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