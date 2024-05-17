package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LorriesCustomAdapter extends RecyclerView.Adapter<LorriesCustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList car_number, load_capacity, manufacturer, car_freight, bodywork, year;
    LorriesCustomAdapter(Context context, ArrayList car_number, ArrayList load_capacity, ArrayList manufacturer, ArrayList car_freight, ArrayList bodywork, ArrayList year) {
        this.context = context;
        this.car_number = car_number;
        this.load_capacity = load_capacity;
        this.manufacturer = manufacturer;
        this.car_freight = car_freight;
        this.bodywork = bodywork;
        this.year = year;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lorry_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.car_number_txt.setText(String.valueOf(car_number.get(position)));
        holder.load_capacity_txt.append(String.valueOf(load_capacity.get(position)) + " т");
        holder.manufacturer_txt.append(String.valueOf(manufacturer.get(position)));
        holder.car_freight_txt.append(String.valueOf(car_freight.get(position)));
        holder.bodywork_txt.append(String.valueOf(bodywork.get(position)));
        holder.production_year_txt.append(String.valueOf(year.get(position)));
    }

    @Override
    public int getItemCount() {
        return car_number.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView car_number_txt, load_capacity_txt, manufacturer_txt, car_freight_txt, bodywork_txt, production_year_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_number_txt = itemView.findViewById(R.id.lorry_number_text);
            load_capacity_txt = itemView.findViewById(R.id.lorry_capacity_text);
            manufacturer_txt = itemView.findViewById(R.id.lorry_manufacturer_text);
            car_freight_txt = itemView.findViewById(R.id.lorry_freight_text);
            bodywork_txt = itemView.findViewById(R.id.lorry_bodywork_text);
            production_year_txt = itemView.findViewById(R.id.lorry_year_text);
        }
    }
}
