package com.example.proyectmanager

class Proyecto (
    private var name: String,
    private var link: String
) {
        fun getNombre(): String {
            return this.name
        }

        fun getPlink(): String {
            return this.link
        }

}