package com.example.applicationcontacts

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val agregar = findViewById<Button>(R.id.button)
        val listView = findViewById<ListView>(R.id.lista1)

        agregar.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 , array)
        listView.setAdapter(adapter)
        listView.setOnItemClickListener { parent, view, position, id ->
            val orden: Contact = (this.application as MyApplication).()


    }
}
