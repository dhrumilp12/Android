package com.example.kotlinmultiscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var createButton = findViewById<Button>(R.id.button)
        var createEditText = findViewById<EditText>(R.id.editTextText)
    }

    fun openActivity(view: View) {
        val editText = findViewById<EditText>(R.id.editTextText)
        val message = editText.text.toString()
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("EXTRA_MESSAGE", message)
        }
        startActivity(intent)

        
    }
}