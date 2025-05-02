package com.example.database;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Dbhandler dbhandler=new Dbhandler(this,"empdb",null,1);
//        dbhandler.addEmployee(new Employee("Dhrumil", 35.5, 1));
//        dbhandler.addEmployee(new Employee("Dhrumil1", 35.6, 2));
//        dbhandler.addEmployee(new Employee("Dhrumil2", 35.7, 3));
        dbhandler.readEmployee(1);
        dbhandler.close();

    }
}