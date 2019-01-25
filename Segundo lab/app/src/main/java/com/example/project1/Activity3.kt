package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val orden: MyOrder = (this.application as MyAplication).getMyorder()

        val showList: ListView = findViewById(R.id.lista2)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 , orden.menuOrder)
        showList.setAdapter(adapter)
        showList.setOnItemLongClickListener { parent, view, position, id ->
            orden.del(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Se elimino exitosamente: ${parent.getItemAtPosition(position)}" , Toast.LENGTH_SHORT).show()
            true
        }
        //Ir a inicio
        val showMenu = findViewById<Button> (R.id.button6)
        showMenu.setOnClickListener {
            val intent = Intent(this,Activity1::class.java)
            startActivity(intent)
        }


        val quitarPedido = findViewById<Button>(R.id.button4)
        quitarPedido.setOnClickListener{
            orden.clear()
            adapter.notifyDataSetChanged()
        }

        val hacerPedido = findViewById<Button>(R.id.button5)
        hacerPedido.setOnClickListener{
            orden.done()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "El pedido se ha hecho" , Toast.LENGTH_SHORT).show()

        }
    }
}