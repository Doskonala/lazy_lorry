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

public class AddDriverActivity extends AppCompatActivity {

    String[] categories = { "C1", "C", "C1E", "CE" };
    EditText fullName, email, phone, additionalContactInfo, licenseNumber, licenseSeries, salary, hireDate;
    AutoCompleteTextView autoCompleteTextViewCategories;
    ArrayAdapter<String> adapterCategories;
    Button add_driver_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        fullName = findViewById(R.id.driver_full_name);
        email = findViewById(R.id.driver_email);
        phone = findViewById(R.id.driver_phone);
        additionalContactInfo = findViewById(R.id.driver_additional_contact_info);
        licenseNumber = findViewById(R.id.driver_license_number);
        licenseSeries = findViewById(R.id.driver_license_series);
        salary = findViewById(R.id.driver_salary);
        hireDate = findViewById(R.id.driver_hire_date);
        autoCompleteTextViewCategories = findViewById(R.id.auto_complete_txt_driver_category);
        adapterCategories = new ArrayAdapter<String>(this, R.layout.list_item, categories);
        autoCompleteTextViewCategories.setAdapter(adapterCategories);
        autoCompleteTextViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddDriverActivity.this, "Категорія " + category, Toast.LENGTH_SHORT).show();
            }
        });
        add_driver_button = findViewById(R.id.btn_add_driver);
        add_driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if(fullName.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть ПІБ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(email.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть електронну пошту", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(phone.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть номер телефону", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(licenseNumber.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть номер ПВ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(licenseSeries.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть серію ПВ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(autoCompleteTextViewCategories.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Виберіть ліцензію ПВ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(salary.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть зарплату", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(hireDate.getText().toString().equals("")) {
                    Toast.makeText(AddDriverActivity.this, "Введіть дату прийому на роботу", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddDriverActivity.this);
                    myDB.addDriver(fullName.getText().toString().trim(),
                            email.getText().toString().trim(),
                            phone.getText().toString().trim(),
                            additionalContactInfo.getText().toString(),
                            licenseNumber.getText().toString().trim(),
                            licenseSeries.getText().toString().trim(),
                            autoCompleteTextViewCategories.getText().toString(),
                            Double.valueOf(salary.getText().toString().trim()),
                            hireDate.getText().toString());
                }
            }
        });
    }
}