package com.example.exam_sec;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String name = editText.getText().toString();
            SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("name", name);
            ed.apply();
            textView.setText(name);
        });
        SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);
        String editVal = sp.getString("name", "No value as of now");
        textView.setText(editVal);
    }
}