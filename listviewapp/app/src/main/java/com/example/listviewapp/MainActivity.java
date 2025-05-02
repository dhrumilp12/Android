package com.example.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] cars = {"Toyota", "Honda", "Ford", "BMW", "Audi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Create an ArrayAdapter for the car names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cars
        );

        listView.setAdapter(adapter);

        // Item click listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCar = cars[position];
            Toast.makeText(MainActivity.this, "Clicked: " + selectedCar, Toast.LENGTH_SHORT).show();
        });
    }
}
