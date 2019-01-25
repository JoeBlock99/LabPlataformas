package com.example.applicationcontacts

class Contact(
    private val nombre: String,
    private val telefono: String,
    private val email: String
) {
    fun getNombre():String{
        return nombre
    }
    fun getTelefono():String{
        return telefono
    }
    fun getEmail():String{
        return email
    }
}