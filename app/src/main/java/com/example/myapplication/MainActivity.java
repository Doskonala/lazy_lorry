package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        buttonAdd.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        int flag=1;
        if(editTextFreight.getText().toString().equals("")) {
            Toast.makeText(this, "Введіть номер замовлення", Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            freight=Integer.parseInt(editTextFreight.getText().toString());
            }
        if(editTextLorry.getText().toString().equals("")){
            Toast.makeText(this,"Введіть номер вантажівки",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            lorry=editTextLorry.getText().toString();
        }
        if(editTextDriver.getText().toString().equals("")){
            Toast.makeText(this,"Введіть ім'я водія",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else {
            driver=editTextDriver.getText().toString();
        }
        if(editTextOrigin.getText().toString().equals("")){
            Toast.makeText(this,"Введіть адресу місця відправки",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            origin=editTextOrigin.getText().toString();
        }
        if(editTextDestination.getText().toString().equals("")){
            Toast.makeText(this,"Введіть адресу місця доставки",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            destination=editTextDestination.getText().toString();
        }
        if(editTextDistance.getText().toString().equals("")){
            Toast.makeText(this,"Введіть відстань (км)",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            distance=Double.parseDouble(editTextDistance.getText().toString());
        }
        if(editTextDate.getText().toString().equals("")){
            Toast.makeText(this,"Введіть дату",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
            try {
                date = formatter.parse(editTextDate.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(editTextTime.getText().toString().equals("")){
            Toast.makeText(this,"Введіть час",Toast.LENGTH_SHORT).show();
            flag=0;
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                time= LocalTime.parse(editTextTime.getText().toString()) ;
            }
        }
        if (flag==1){
            DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
            textViewSchedule.append("Номер вантажу: "+freight+"\nНомер вантажівки: "+lorry+"\nВодій: "+driver+"\nМісце відправки: "+origin+"\nМісце призначення: "+destination+"\nВідстань (км): "+distance+"\nДата: "+dateFormat.format(date)+"\nЧас: "+time+"\n\n");
        }
    }
}