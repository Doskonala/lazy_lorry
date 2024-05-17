package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriversCustomAdapter extends RecyclerView.Adapter<DriversCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList full_name, email, phone, additional_contact_info, license_number, license_series, license_category, salary, hire_date;

    DriversCustomAdapter(Context context, ArrayList full_name, ArrayList email, ArrayList phone, ArrayList additional_contact_info, ArrayList license_number, ArrayList license_series, ArrayList license_category, ArrayList salary, ArrayList hire_date) {
        this.context = context;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.additional_contact_info = additional_contact_info;
        this.license_number = license_number;
        this.license_series = license_series;
        this.license_category = license_category;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    @NonNull
    @Override
    public DriversCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.driver_row, parent, false);
        return new DriversCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriversCustomAdapter.MyViewHolder holder, int position) {
        holder.driver_full_name_txt.append(String.valueOf(full_name.get(position)));
        holder.driver_email_txt.append(String.valueOf(email.get(position)));
        holder.driver_phone_txt.append(String.valueOf(phone.get(position)));
        holder.driver_additional_contact_info_txt.append(String.valueOf(additional_contact_info.get(position)));
        holder.driver_license_number_txt.append(String.valueOf(license_number.get(position)));
        holder.driver_license_series_txt.append(String.valueOf(license_series.get(position)));
        holder.driver_license_category_txt.append(String.valueOf(license_category.get(position)));
        holder.driver_salary_txt.append(String.valueOf(salary.get(position)) + " грн");
        holder.driver_hire_date_txt.append(String.valueOf(hire_date.get(position)));
    }

    @Override
    public int getItemCount() {
        return full_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView driver_full_name_txt, driver_email_txt, driver_phone_txt, driver_additional_contact_info_txt, driver_license_number_txt, driver_license_series_txt, driver_license_category_txt, driver_salary_txt, driver_hire_date_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            driver_full_name_txt = itemView.findViewById(R.id.driver_full_name_text);
            driver_email_txt = itemView.findViewById(R.id.driver_email_text);
            driver_phone_txt = itemView.findViewById(R.id.driver_phone_text);
            driver_additional_contact_info_txt = itemView.findViewById(R.id.driver_additional_info_text);
            driver_license_number_txt = itemView.findViewById(R.id.driver_license_number_text);
            driver_license_series_txt = itemView.findViewById(R.id.driver_license_series_text);
            driver_license_category_txt = itemView.findViewById(R.id.driver_license_category_text);
            driver_salary_txt = itemView.findViewById(R.id.driver_salary_text);
            driver_hire_date_txt = itemView.findViewById(R.id.driver_hire_date_text);
        }
    }
}
