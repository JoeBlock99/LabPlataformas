package com.example.applicationcontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Activity3 : AppCompatActivity() {
    val regresar = findViewById<Button>(R.id.button4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val nombre = findViewById<EditText>(R.id.textView2).getText().toString()
        val telefono = findViewById<EditText>(R.id.textView5).getText().toString()
        val correo = findViewById<EditText>(R.id.textView7).getText().toString()
    }
}
