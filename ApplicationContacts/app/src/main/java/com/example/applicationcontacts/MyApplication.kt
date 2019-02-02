package com.example.applicationcontacts

import android.app.Application
import java.util.ArrayList

class MyApplication: Application() {
    val contactList = ArrayList<Contact>()
    val guardarContact = Contact(nombre = String(),telefono = String(),email = String() )
    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    fun getContacts(): ArrayList<Contact> {
        return this.contactList
    }

    fun getContact(index: Int): Contact {
        return this.contactList[index]
    }

}