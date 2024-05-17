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

public class DriversActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    RecyclerView recyclerDriversView;
    FloatingActionButton add_driver_button;
    MyDatabaseHelper myDB;
    ArrayList<String> fullName, email, phone, additionalContactInfo, licenseNumber, licenseSeries, licenseCategory, salary, hireDate;
    DriversCustomAdapter driversCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
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
        recyclerDriversView = findViewById(R.id.recyclerDriversView);
        add_driver_button = findViewById(R.id.add_driver_button);
        add_driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriversActivity.this, AddDriverActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(DriversActivity.this);
        fullName = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();
        additionalContactInfo = new ArrayList<>();
        licenseNumber = new ArrayList<>();
        licenseSeries = new ArrayList<>();
        licenseCategory = new ArrayList<>();
        salary = new ArrayList<>();
        hireDate = new ArrayList<>();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, MainActivity.class);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, MessagesActivity.class);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, OrdersActivity.class);
            }
        });
        lorries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, LorriesActivity.class);
            }
        });
        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, ClientsActivity.class);
            }
        });
        partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, PartnersActivity.class);
            }
        });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(DriversActivity.this, CompanyActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriversActivity.this,"Ви вийшли з системи",Toast.LENGTH_SHORT).show();
            }
        });

        storeDriversInArrays();
        driversCustomAdapter = new DriversCustomAdapter(DriversActivity.this, fullName, email, phone, additionalContactInfo, licenseNumber, licenseSeries, licenseCategory, salary, hireDate);
        recyclerDriversView.setAdapter(driversCustomAdapter);
        recyclerDriversView.setLayoutManager(new LinearLayoutManager(DriversActivity.this));
    }

    void storeDriversInArrays (){
        Cursor cursor = myDB.readAllDrivers();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"Дані про водіїв відсутні",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                fullName.add(cursor.getString(0));
                email.add(cursor.getString(1));
                phone.add(cursor.getString(2));
                additionalContactInfo.add(cursor.getString(3));
                licenseNumber.add(cursor.getString(4));
                licenseSeries.add(cursor.getString(5));
                licenseCategory.add(cursor.getString(6));
                salary.add(cursor.getString(7));
                hireDate.add(cursor.getString(8));
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