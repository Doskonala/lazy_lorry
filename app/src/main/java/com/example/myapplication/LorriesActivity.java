package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LorriesActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    RecyclerView recyclerView;
    FloatingActionButton add_lorry_button;
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
        recyclerView = findViewById(R.id.recyclerView);
        add_lorry_button = findViewById(R.id.add_lorry_button);
        add_lorry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LorriesActivity.this, AddLorryActivity.class);
                startActivity(intent);
            }
        });
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