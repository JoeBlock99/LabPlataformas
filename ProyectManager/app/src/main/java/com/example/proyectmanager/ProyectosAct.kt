package com.example.proyectmanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ProyectosAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proyectos2)
        val proyectos = (this.application as MyApplication).getProyectos()
        val nombres = arrayListOf("Menú App", "Contactos App", "Proyectos Manager App")

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
        (this.application as MyApplication).setProyectos(proyectos[position])
        val intentProyecto = Intent(this, ProyectoAct::class.java)
            startActivity(intentProyecto)
        }
    }
}
