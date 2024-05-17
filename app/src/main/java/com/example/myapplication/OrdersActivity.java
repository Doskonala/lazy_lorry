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

public class OrdersActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    RecyclerView recyclerOrdersView;
    FloatingActionButton add_order_button;
    MyDatabaseHelper myDB;
    ArrayList order_id, group, type, transportation, client_id, client_name, declared_value, partner_id, partner_name, partner_company;
    OrdersCustomAdapter ordersCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
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
        recyclerOrdersView = findViewById(R.id.recyclerOrdersView);
        add_order_button = findViewById(R.id.add_order_button);
        add_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersActivity.this, AddOrderActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(OrdersActivity.this);
        order_id = new ArrayList<>();
        group = new ArrayList<>();
        type = new ArrayList<>();
        transportation = new ArrayList<>();
        client_id = new ArrayList<>();
        client_name = new ArrayList<>();
        declared_value = new ArrayList<>();
        partner_id = new ArrayList<>();
        partner_name = new ArrayList<>();
        partner_company = new ArrayList<>();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, MainActivity.class);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, MessagesActivity.class);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        lorries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, LorriesActivity.class);
            }
        });
        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, DriversActivity.class);
            }
        });
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, ClientsActivity.class);
            }
        });
        partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, PartnersActivity.class);
            }
        });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(OrdersActivity.this, CompanyActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrdersActivity.this,"Ви вийшли з системи",Toast.LENGTH_SHORT).show();
            }
        });

        storeOrdersInArrays();
        ordersCustomAdapter = new OrdersCustomAdapter(OrdersActivity.this, order_id, group, type, transportation, client_id, client_name, declared_value, partner_id, partner_name, partner_company);
        recyclerOrdersView.setAdapter(ordersCustomAdapter);
        recyclerOrdersView.setLayoutManager(new LinearLayoutManager(OrdersActivity.this));
    }

    void storeOrdersInArrays () {
        Cursor cursor = myDB.readAllOrders();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Дані про замовлення відсутні", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                order_id.add(cursor.getString(0));
                group.add(cursor.getString(1));
                type.add(cursor.getString(2));
                transportation.add(cursor.getString(3));
                client_id.add(cursor.getString(4));
                client_name.add(cursor.getString(5));
                declared_value.add(cursor.getString(6));
                partner_id.add(cursor.getString(7));
                partner_name.add(cursor.getString(8));
                partner_company.add(cursor.getString(9));
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