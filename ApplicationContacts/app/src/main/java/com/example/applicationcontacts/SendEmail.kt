package com.example.applicationcontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SendEmail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_email)
        val bundle = intent.extras
        val correoContacto = (this.application as MyApplication).getActualContact().getCorreo()
        val mensaje = bundle!!.getString("mensaje")

        val deView = findViewById<EditText>(R.id.editTextDe)
        val paraView = findViewById<EditText>(R.id.editTextPara)
        paraView.setText(correoContacto)
        val mensajeView = findViewById<EditText>(R.id.editTextMensaje)
        mensajeView.setText(mensaje)
    }

    fun enviarCorreo(view: View) {
        /*
        val mySnackbar = Snackbar.make(
            findViewById(R.id.myCoordinatorLayout),
            R.string.email_archived, Snackbar.LENGTH_SHORT
        )
        */

    }
}
