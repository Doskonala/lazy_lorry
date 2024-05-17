package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersCustomAdapter extends RecyclerView.Adapter<OrdersCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList order_id, group, type, transportation, client_id, client_name, declared_value, partner_id, partner_name, partner_company;

    OrdersCustomAdapter(Context context, ArrayList order_id, ArrayList group, ArrayList type, ArrayList transportation, ArrayList client_id, ArrayList client_name, ArrayList declared_value, ArrayList partner_id, ArrayList partner_name, ArrayList partner_company) {
        this.context = context;
        this.order_id = order_id;
        this.group = group;
        this.type = type;
        this.transportation = transportation;
        this.client_id = client_id;
        this.client_name = client_name;
        this.declared_value = declared_value;
        this.partner_id = partner_id;
        this.partner_name = partner_name;
        this.partner_company = partner_company;
    }

    @NonNull
    @Override
    public OrdersCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row, parent, false);
        return new OrdersCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersCustomAdapter.MyViewHolder holder, int position) {
        holder.order_id_txt.append(String.valueOf(order_id.get(position)));
        holder.group_txt.append(String.valueOf(group.get(position)));
        holder.type_txt.append(String.valueOf(type.get(position)));
        holder.transportation_txt.append(String.valueOf(transportation.get(position)));
        holder.client_id_txt.append(String.valueOf(client_id.get(position)));
        holder.client_name_txt.append(String.valueOf(client_name.get(position)));
        holder.declared_value_txt.append(String.valueOf(declared_value.get(position)));
        holder.partner_id_txt.append(String.valueOf(partner_id.get(position)));
        holder.partner_name_txt.append(String.valueOf(partner_name.get(position)));
        holder.partner_company_txt.append(String.valueOf(partner_company.get(position)));
    }

    @Override
    public int getItemCount() {
        return order_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_id_txt, group_txt, type_txt, transportation_txt, client_id_txt, client_name_txt, declared_value_txt, partner_id_txt, partner_name_txt, partner_company_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            order_id_txt = itemView.findViewById(R.id.order_id_text);
            group_txt = itemView.findViewById(R.id.order_group_text);
            type_txt = itemView.findViewById(R.id.order_type_text);
            transportation_txt = itemView.findViewById(R.id.order_transportation_text);
            client_id_txt = itemView.findViewById(R.id.client_id_text);
            client_name_txt = itemView.findViewById(R.id.client_name_text);
            declared_value_txt = itemView.findViewById(R.id.order_value_text);
            partner_id_txt = itemView.findViewById(R.id.partner_id_text);
            partner_name_txt = itemView.findViewById(R.id.partner_name_text);
            partner_company_txt = itemView.findViewById(R.id.partner_company_text);
        }
    }
}