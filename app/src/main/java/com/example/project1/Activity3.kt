package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class Activity3 : AppCompatActivity() {
    val array = arrayOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val orden: MyOrder = (this.application as MyAplication).getMyorder()
        val showList: ListView = findViewById(R.id.lista2)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 , array)
        showList.setAdapter(adapter)
        showList.setOnItemLongClickListener { parent, view, position, id ->
            orden.del(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Se elimino exitosamente: ${parent.getItemAtPosition(position)}" , Toast.LENGTH_SHORT).show()
            true
        }
        //Ir a inicio
        val ShowMenu = findViewById<Button> (R.id.button6)
        ShowMenu.setOnClickListener {
            val intent = Intent(this,Activity1::class.java)
            startActivity(intent)
        }


        val btnLimpiar = findViewById<Button>(R.id.button4)
        btnLimpiar.setOnClickListener{
            orden.clear()
            adapter.notifyDataSetChanged()
        }

        val btnHacerPedido = findViewById<Button>(R.id.button5)
        btnHacerPedido.setOnClickListener{
            orden.done()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "El pedido se ha hecho" , Toast.LENGTH_SHORT).show()

        }
    }
}