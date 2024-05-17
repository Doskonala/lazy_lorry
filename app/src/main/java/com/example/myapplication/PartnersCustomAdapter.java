package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartnersCustomAdapter extends RecyclerView.Adapter<PartnersCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, full_name, email, phone, additional_contact_info, country, company, website;

    PartnersCustomAdapter(Context context, ArrayList id, ArrayList full_name, ArrayList email, ArrayList phone, ArrayList additional_contact_info, ArrayList country, ArrayList company, ArrayList website) {
        this.id = id;
        this.context = context;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.additional_contact_info = additional_contact_info;
        this.country = country;
        this.company = company;
        this.website = website;
    }

    @NonNull
    @Override
    public PartnersCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.partner_row, parent, false);
        return new PartnersCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnersCustomAdapter.MyViewHolder holder, int position) {
        holder.partner_id_txt.append(String.valueOf(id.get(position)));
        holder.partner_full_name_txt.append(String.valueOf(full_name.get(position)));
        holder.partner_email_txt.append(String.valueOf(email.get(position)));
        holder.partner_phone_txt.append(String.valueOf(phone.get(position)));
        holder.partner_additional_contact_info_txt.append(String.valueOf(additional_contact_info.get(position)));
        holder.partner_country_txt.append(String.valueOf(country.get(position)));
        holder.partner_company_txt.append(String.valueOf(company.get(position)));
        holder.partner_website_txt.append(String.valueOf(website.get(position)));
    }

    @Override
    public int getItemCount() {
        return full_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView partner_id_txt, partner_full_name_txt, partner_email_txt, partner_phone_txt, partner_additional_contact_info_txt, partner_country_txt, partner_company_txt, partner_website_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            partner_id_txt = itemView.findViewById(R.id.partner_id_text);
            partner_full_name_txt = itemView.findViewById(R.id.partner_full_name_text);
            partner_email_txt = itemView.findViewById(R.id.partner_email_text);
            partner_phone_txt = itemView.findViewById(R.id.partner_phone_text);
            partner_additional_contact_info_txt = itemView.findViewById(R.id.partner_additional_info_text);
            partner_country_txt = itemView.findViewById(R.id.partner_country_text);
            partner_company_txt = itemView.findViewById(R.id.partner_company_text);
            partner_website_txt = itemView.findViewById(R.id.partner_website_text);
        }
    }
}
