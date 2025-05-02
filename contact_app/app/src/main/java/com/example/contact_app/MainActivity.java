package com.example.contact_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    contact user1 = new contact(1, "John Doe", "123-456-7890");
    contact user2 = new contact(2, "Jane Smith", "987-654-3210");
    contact user3 = new contact(3, "Bob Johnson", "555-555-5555");
    contact user4 = new contact(4, "Alice Brown", "111-222-3333");
    contact user5 = new contact(5, "Charlie White", "444-444-4444");
    contact user6 = new contact(6, "David Green", "777-777-7777");
    contact user7 = new contact(7, "Emily Black", "888-888-8888");
    contact user8 = new contact(8, "Frank Red", "999-999-9999");
    contact user9 = new contact(9, "Grace Yellow", "222-222-2222");

    contact[] contactList = {user1, user2, user3, user4, user5, user6, user7, user8, user9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter adapter = new CustomAdapter(contactList);
        recyclerView.setAdapter(adapter);

    }
}