package com.example.project1

import java.util.ArrayList

class MyOrder: MenuOrder {

    override val menuOrder = ArrayList<String>() // Pedido

    override fun clear(){
        menuOrder.clear()
    }
    override fun add(element: String){
        menuOrder.add(element)
    }
    override fun del(elementIndex: Int){
        menuOrder.removeAt(elementIndex)
    }

    override fun done(){
        menuOrder.clear()
    }

}