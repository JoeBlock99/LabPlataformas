package com.example.applicationcontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity3 : AppCompatActivity() {
    val regresar = findViewById<Button>(R.id.button4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val nombreTextView = findViewById<TextView>(R.id.textView2)
        nombreTextView.text = contacto.getNombre()
        val telefonoTextView = findViewById<TextView>(R.id.textView5)
        telefonoTextView.text = contacto.getTelefono()
        val correoTextView = findViewById<TextView>(R.id.textView7)


    }
   // fun setContacto(contacto: Contact) {

      //  correoTextView.text = contacto.getCorreo()
    //}
}
