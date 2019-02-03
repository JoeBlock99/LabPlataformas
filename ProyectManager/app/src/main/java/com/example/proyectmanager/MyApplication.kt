package com.example.proyectmanager

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MyApplication : Application() {
    private val proyectos = arrayListOf<Proyecto>(
        Proyecto("Menu App", "https://github.com/JoeBlock99/LabPlataformas/tree/master/Segundo%20lab"),
        Proyecto("Contactos App", "https://github.com/JoeBlock99/LabPlataformas/tree/master/ApplicationContacts"),
        Proyecto("Proyectos Manager App", "https://github.com/JoeBlock99/LabPlataformas/tree/master/ProyectManager")
    )

    private var todosProyectos = Proyecto("Github", "https://github.com/JoeBlock99/LabPlataformas")

    fun getTodosProyectos(): ArrayList<Proyecto> {
        return this.proyectos
    }

    fun setProyectos(p: Proyecto) {
        this.todosProyectos = p
    }

    fun getProyectos(): Proyecto {
        return this.todosProyectos
    }
}
