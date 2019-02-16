package com.example.applicationcontacts

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val regresar = findViewById<Button>(R.id.button2)
        val crear = findViewById<Button>(R.id.button3)
        regresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        crear.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.name).getText().toString()
            val telefono = findViewById<EditText>(R.id.phone).getText().toString()
            val correo = findViewById<EditText>(R.id.email).getText().toString()
            val contacto = Contact(nombre, telefono, correo)
            (this.application as MyApplication).addContact(contacto)
            Toast.makeText(this, "Se agrego: ${nombre} correctamente" , Toast.LENGTH_SHORT).show()
        }
    }
}
