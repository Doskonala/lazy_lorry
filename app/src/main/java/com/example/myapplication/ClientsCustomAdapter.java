package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientsCustomAdapter extends RecyclerView.Adapter<ClientsCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, full_name, email, phone, additional_contact_info, company, website;

    ClientsCustomAdapter(Context context, ArrayList id, ArrayList full_name, ArrayList email, ArrayList phone, ArrayList additional_contact_info, ArrayList company, ArrayList website) {
        this.context = context;
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.additional_contact_info = additional_contact_info;
        this.company = company;
        this.website = website;
    }

    @NonNull
    @Override
    public ClientsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.client_row, parent, false);
        return new ClientsCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsCustomAdapter.MyViewHolder holder, int position) {
        holder.client_id_txt.append(String.valueOf(id.get(position)));
        holder.client_full_name_txt.append(String.valueOf(full_name.get(position)));
        holder.client_email_txt.append(String.valueOf(email.get(position)));
        holder.client_phone_txt.append(String.valueOf(phone.get(position)));
        holder.client_additional_contact_info_txt.append(String.valueOf(additional_contact_info.get(position)));
        holder.client_company_txt.append(String.valueOf(company.get(position)));
        holder.client_website_txt.append(String.valueOf(website.get(position)));
    }

    @Override
    public int getItemCount() {
        return full_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView client_id_txt, client_full_name_txt, client_email_txt, client_phone_txt, client_additional_contact_info_txt, client_company_txt, client_website_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            client_id_txt = itemView.findViewById(R.id.client_id_text);
            client_full_name_txt = itemView.findViewById(R.id.client_full_name_text);
            client_email_txt = itemView.findViewById(R.id.client_email_text);
            client_phone_txt = itemView.findViewById(R.id.client_phone_text);
            client_additional_contact_info_txt = itemView.findViewById(R.id.client_additional_info_text);
            client_company_txt = itemView.findViewById(R.id.client_company_text);
            client_website_txt = itemView.findViewById(R.id.client_website_text);
        }
    }
}