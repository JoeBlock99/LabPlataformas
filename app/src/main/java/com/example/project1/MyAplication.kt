package com.example.project1

import android.app.Application

class MyAplication:Application() {
    val order: MyOrder = MyOrder()

    fun getMyorder() : MyOrder{
        return order
    }
}