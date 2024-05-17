package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClientActivity extends AppCompatActivity {

    EditText fullName, email, phone, additionalContactInfo, firm, website;
    Button add_client_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        fullName = findViewById(R.id.client_full_name);
        email = findViewById(R.id.client_email);
        phone = findViewById(R.id.client_phone);
        additionalContactInfo = findViewById(R.id.client_additional_contact_info);
        firm = findViewById(R.id.client_company);
        website = findViewById(R.id.client_website);
        add_client_button = findViewById(R.id.btn_add_client);
        add_client_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if (fullName.getText().toString().equals("")) {
                    Toast.makeText(AddClientActivity.this, "Введіть ПІБ", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (email.getText().toString().equals("")) {
                    Toast.makeText(AddClientActivity.this, "Введіть електронну пошту", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (phone.getText().toString().equals("")) {
                    Toast.makeText(AddClientActivity.this, "Введіть номер телефону", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddClientActivity.this);
                    myDB.addClient(fullName.getText().toString().trim(),
                            email.getText().toString().trim(),
                            phone.getText().toString().trim(),
                            additionalContactInfo.getText().toString(),
                            firm.getText().toString().trim(),
                            website.getText().toString().trim());
                }
            }
        });
    }
}