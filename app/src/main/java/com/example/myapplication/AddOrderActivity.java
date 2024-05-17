package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOrderActivity extends AppCompatActivity {

    String[] groups = { "товарна", "нетоварна" };
    String[] typesOfFreight = { "швидкопсувний", "наливний", "пилоподібний", "газоподібний", "навалочний і насипний", "небезпечний", "збірний", "живий" };
    String[] waysOfTransportation = { "генеральний", "негабаритний" };
    EditText client, declaredValue, partner;
    AutoCompleteTextView autoCompleteTextViewGroups, autoCompleteTextViewTypesOfFreight, autoCompleteTextViewWaysOfTransportation;
    ArrayAdapter<String> adapterGroups, adapterTypesOfFreight, adapterWaysOfTransportation;
    Button add_order_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        autoCompleteTextViewGroups = findViewById(R.id.auto_complete_txt_freight_group);
        adapterGroups = new ArrayAdapter<String>(this, R.layout.list_item, groups);
        autoCompleteTextViewGroups.setAdapter(adapterGroups);
        autoCompleteTextViewGroups.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String group = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddOrderActivity.this, "Група вантажу " + group, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextViewTypesOfFreight = findViewById(R.id.auto_complete_txt_freight_type);
        adapterTypesOfFreight = new ArrayAdapter<String>(this, R.layout.list_item, typesOfFreight);
        autoCompleteTextViewTypesOfFreight.setAdapter(adapterTypesOfFreight);
        autoCompleteTextViewTypesOfFreight.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddOrderActivity.this, "Тип вантажу " + type, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextViewWaysOfTransportation = findViewById(R.id.auto_complete_txt_freight_way_of_transportation);
        adapterWaysOfTransportation = new ArrayAdapter<String>(this, R.layout.list_item, waysOfTransportation);
        autoCompleteTextViewWaysOfTransportation.setAdapter(adapterWaysOfTransportation);
        autoCompleteTextViewWaysOfTransportation.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String way = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddOrderActivity.this, "Спосіб перевезення вантажу " + way, Toast.LENGTH_SHORT).show();
            }
        });
        client = findViewById(R.id.freight_client_id);
        declaredValue = findViewById(R.id.freight_declared_value);
        partner = findViewById(R.id.freight_partner_id);
        add_order_button = findViewById(R.id.btn_add_order);
        add_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if (autoCompleteTextViewGroups.getText().toString().equals("")) {
                    Toast.makeText(AddOrderActivity.this, "Виберіть групу вантажу", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (autoCompleteTextViewTypesOfFreight.getText().toString().equals("")) {
                    Toast.makeText(AddOrderActivity.this, "Виберіть тип вантажу", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (autoCompleteTextViewWaysOfTransportation.getText().toString().equals("")) {
                    Toast.makeText(AddOrderActivity.this, "Виберіть спосіб перевезення", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (client.getText().toString().equals("")) {
                    Toast.makeText(AddOrderActivity.this, "Введіть ID клієнта", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (declaredValue.getText().toString().equals("")) {
                    Toast.makeText(AddOrderActivity.this, "Введіть оголошену вартість вантажу", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddOrderActivity.this);
                    myDB.addOrder(autoCompleteTextViewGroups.getText().toString(),
                            autoCompleteTextViewTypesOfFreight.getText().toString(),
                            autoCompleteTextViewWaysOfTransportation.getText().toString(),
                            Integer.valueOf(client.getText().toString().trim()),
                            Double.valueOf(declaredValue.getText().toString().trim()),
                            Integer.valueOf(partner.getText().toString().trim())
                    );
                }
            }
        });
    }
}