package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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