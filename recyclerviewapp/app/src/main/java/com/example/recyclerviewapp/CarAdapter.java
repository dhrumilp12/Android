package com.example.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final Context context;
    private final String[] cars;

    public CarAdapter(Context context, String[] cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        String car = cars[position];
        holder.carName.setText(car);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Clicked: " + car, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cars.length;
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView carName;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
        }
    }
}
