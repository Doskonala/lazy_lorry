package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPartnerActivity extends AppCompatActivity {

    EditText fullName, email, phone, additionalContactInfo, country, company, website;
    Button add_partner_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner);
        fullName = findViewById(R.id.partner_full_name);
        email = findViewById(R.id.partner_email);
        phone = findViewById(R.id.partner_phone);
        additionalContactInfo = findViewById(R.id.partner_additional_contact_info);
        country = findViewById(R.id.partner_country);
        company = findViewById(R.id.partner_company);
        website = findViewById(R.id.partner_website);
        add_partner_button = findViewById(R.id.btn_add_partner);
        add_partner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if(fullName.getText().toString().equals("")) {
                    Toast.makeText(AddPartnerActivity.this, "Введіть ПІБ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(email.getText().toString().equals("")) {
                    Toast.makeText(AddPartnerActivity.this, "Введіть електронну пошту", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(phone.getText().toString().equals("")) {
                    Toast.makeText(AddPartnerActivity.this, "Введіть номер телефону", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(country.getText().toString().equals("")) {
                    Toast.makeText(AddPartnerActivity.this, "Введіть назву країни", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if(company.getText().toString().equals("")) {
                    Toast.makeText(AddPartnerActivity.this, "Введіть назву компанії", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddPartnerActivity.this);
                    myDB.addPartner(fullName.getText().toString().trim(),
                            email.getText().toString().trim(),
                            phone.getText().toString().trim(),
                            additionalContactInfo.getText().toString(),
                            country.getText().toString().trim(),
                            company.getText().toString().trim(),
                            website.getText().toString().trim());
                }
            }
        });
    }
}