package com.example.applicationcontacts

import android.app.Application
import java.util.ArrayList

class MyApplication: Application() {
    val contactList = ArrayList<Contact>()

}