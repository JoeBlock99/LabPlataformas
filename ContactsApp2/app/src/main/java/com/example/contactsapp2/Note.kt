package com.example.contactsapp2
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(

    var title: String,

    var description: String,

    var email: String,


    var priority: Int
) {
    //does it matter if these are private or not?
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}