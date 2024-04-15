package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.Date;

public class OrdersActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout timetable, messages, orders, lorries, drivers, clients, partners, company;
    LinearLayout logout;
    Button buttonAdd;
    EditText editTextFreight,editTextLorry,editTextDriver,editTextOrigin,editTextDestination,editTextDistance,editTextDate,editTextTime;
    TextView textViewTitle;
    TextView textViewSchedule;
    int freight;
    String lorry,driver,origin,destination;
    double distance;
    Date date;
    LocalTime time;
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
        buttonAdd=findViewById(R.id.btn_add);
        editTextFreight=findViewById(R.id.freight);
        editTextLorry=findViewById(R.id.lorry);
        editTextDriver=findViewById(R.id.driver);
        editTextOrigin=findViewById(R.id.origin);
        editTextDestination=findViewById(R.id.destination);
        editTextDistance=findViewById(R.id.distance);
        editTextDate=findViewById(R.id.date);
        editTextTime=findViewById(R.id.time);
        textViewTitle=findViewById(R.id.schedule_title);
        textViewSchedule=findViewById(R.id.schedule);
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