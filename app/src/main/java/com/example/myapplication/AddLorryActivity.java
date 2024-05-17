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

public class AddLorryActivity extends AppCompatActivity {

    String[] typesOfFreight = { "швидкопсувний", "наливний", "пилоподібний", "газоподібний", "навалочний і насипний", "небезпечний", "збірний", "живий" };
    String[] typesOfBodywork = { "Самоскиди", "Бортові", "Криті", "З тентом", "Автоцистерни", "Бетономішалки", "Авторефрижератори", "Автовози", "Контейнерні", "Тягачі" };
    EditText carNumber, loadCapacity, manufacturer, yearOfProduction;
    AutoCompleteTextView autoCompleteTextViewTypesOfFreight;
    ArrayAdapter<String> adapterTypesOfFreight;
    AutoCompleteTextView autoCompleteTextViewTypesOfBodywork;
    ArrayAdapter<String> adapterTypesOfBodywork;
    Button add_lorry_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lorry);
        carNumber = findViewById(R.id.number);
        loadCapacity = findViewById(R.id.load_capacity);
        manufacturer = findViewById(R.id.manufacturer);
        yearOfProduction = findViewById(R.id.production_year);
        autoCompleteTextViewTypesOfFreight = findViewById(R.id.auto_complete_txt_type_of_freight_for_lorry);
        adapterTypesOfFreight = new ArrayAdapter<String>(this, R.layout.list_item, typesOfFreight);
        autoCompleteTextViewTypesOfFreight.setAdapter(adapterTypesOfFreight);
        autoCompleteTextViewTypesOfFreight.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddLorryActivity.this, "Тип вантажу " + type, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextViewTypesOfBodywork = findViewById(R.id.auto_complete_txt_bodywork_for_lorry);
        adapterTypesOfBodywork = new ArrayAdapter<String>(this, R.layout.list_item, typesOfBodywork);
        autoCompleteTextViewTypesOfBodywork.setAdapter(adapterTypesOfBodywork);
        autoCompleteTextViewTypesOfBodywork.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bodywork = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddLorryActivity.this, "Тип кузова " + bodywork, Toast.LENGTH_SHORT).show();
            }
        });
        add_lorry_button = findViewById(R.id.btn_add_lorry);
        add_lorry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if(carNumber.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Введіть номер автомобіля", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(loadCapacity.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Введіть вантажність", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(manufacturer.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Введіть виробника", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(autoCompleteTextViewTypesOfFreight.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Виберіть тип вантажу", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(autoCompleteTextViewTypesOfBodywork.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Виберіть тип кузова", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(yearOfProduction.getText().toString().equals("")) {
                    Toast.makeText(AddLorryActivity.this, "Введіть рік виробництва", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddLorryActivity.this);
                    myDB.addLorry(carNumber.getText().toString().trim(),
                            Double.valueOf(loadCapacity.getText().toString().trim()),
                            manufacturer.getText().toString().trim(),
                            autoCompleteTextViewTypesOfFreight.getText().toString().trim(),
                            autoCompleteTextViewTypesOfBodywork.getText().toString().trim(),
                            Integer.valueOf(yearOfProduction.getText().toString().trim())
                    );
                }
            }
        });
    }
}