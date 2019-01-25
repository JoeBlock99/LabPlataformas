package com.example.project1

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity1 : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        val ShowMenu = findViewById<Button> (R.id.button)
        ShowMenu.setOnClickListener {
            val intent = Intent(this,Activity2::class.java)
            startActivity(intent)
        }
        val pedido = findViewById<Button>(R.id.button2)
        pedido.setOnClickListener {
            val intent = Intent(this,Activity3::class.java)
            startActivity(intent)
        }
    }
}
