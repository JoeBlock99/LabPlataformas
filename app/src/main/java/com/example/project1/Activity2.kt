package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.*

class Activity2 : AppCompatActivity() {
    val array = arrayOf("Hot dog con pan de brownie", "Helado de tocino", "Carne con malvavisco derretido", "Flan de coco(Especialidad)", "Huevos rebueltos con sebolla y miel","Fondeu de queso con lomito")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val ShowMenu = findViewById<Button> (R.id.button3)
        ShowMenu.setOnClickListener {
            val intent = Intent(this,Activity1::class.java)
            startActivity(intent)
        }

        val listView: ListView = findViewById(R.id.lista1menu)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 , array)
        listView.setAdapter(adapter)
        listView.setOnItemClickListener { parent, view, position, id ->
            val orden: MyOrder = (this.application as MyAplication).getMyorder()
            orden.add(array.get(position))
            Toast.makeText(this, "Se añadio exitosamente: ${parent.getItemAtPosition(position)}" , Toast.LENGTH_SHORT).show()
        }


    }

}