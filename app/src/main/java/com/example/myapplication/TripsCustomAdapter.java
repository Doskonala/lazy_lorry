package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripsCustomAdapter extends RecyclerView.Adapter<TripsCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList trip_id, order_id, car_number, driver_number, driver_name, origin, destination, distance, date, time;

    TripsCustomAdapter(Context context, ArrayList trip_id, ArrayList order_id, ArrayList car_number, ArrayList driver_number, ArrayList driver_name, ArrayList origin, ArrayList destination, ArrayList distance, ArrayList date, ArrayList time) {
        this.context = context;
        this.trip_id = trip_id;
        this.order_id = order_id;
        this.car_number = car_number;
        this.driver_number = driver_number;
        this.driver_name = driver_name;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public TripsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trip_row, parent, false);
        return new TripsCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsCustomAdapter.MyViewHolder holder, int position) {
        holder.trip_id_txt.append(String.valueOf(trip_id.get(position)));
        holder.order_id_txt.append(String.valueOf(order_id.get(position)));
        holder.lorry_number_txt.append(String.valueOf(car_number.get(position)));
        holder.driver_number_txt.append(String.valueOf(driver_number.get(position)));
        holder.driver_name_txt.append(String.valueOf(driver_name.get(position)));
        holder.origin_txt.append(String.valueOf(origin.get(position)));
        holder.destination_txt.append(String.valueOf(destination.get(position)));
        holder.distance_txt.append(String.valueOf(distance.get(position)));
        holder.date_txt.append(String.valueOf(date.get(position)));
        holder.time_txt.append(String.valueOf(time.get(position)));
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, order_id_txt, lorry_number_txt, driver_number_txt, driver_name_txt, origin_txt, destination_txt, distance_txt, date_txt, time_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_text);
            order_id_txt = itemView.findViewById(R.id.order_id_text);
            lorry_number_txt = itemView.findViewById(R.id.lorry_number_text);
            driver_number_txt = itemView.findViewById(R.id.driver_number_text);
            driver_name_txt = itemView.findViewById(R.id.driver_name_text);
            origin_txt = itemView.findViewById(R.id.origin_text);
            destination_txt = itemView.findViewById(R.id.destination_text);
            distance_txt = itemView.findViewById(R.id.distance_text);
            date_txt = itemView.findViewById(R.id.date_text);
            time_txt = itemView.findViewById(R.id.time_text);
        }
    }
}