package com.example.multiscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView name;
    Button button;

    public static final String EXTRA_MESSAGE = "com.example.multiscreen.EXTRA_MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
    public void openActivity(View view) {

        Intent intent = new Intent(this, MainActivity2.class);
        name = findViewById(R.id.name);
        button = findViewById(R.id.button);
        String name1 = name.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name1);
        startActivity(intent);

    }
}