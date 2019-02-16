package com.example.musicplayer

import android.content.Context
import android.view.View
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView


class SongAdapter(c: Context, songs:ArrayList<Song>): BaseAdapter(){
     var songs: ArrayList<Song>? = songs
     var songInf: LayoutInflater? = LayoutInflater.from(c)

     override fun getCount(): Int {
         return songs!!.size
     }

     override fun getItem(pos: Int): Any{

         return songs!![pos]
     }

     override fun getItemId(pos: Int): Long {
         val mainSong = songs!![pos]
         return mainSong.id
     }

     override fun getView(pos: Int, arg1: View, arg2: ViewGroup): View? {
         //map to song layout
         val songLay = songInf!!.inflate(R.layout.song, arg2, false) as LinearLayout
         //get title and artist views
         val songView = songLay.findViewById<View>(R.id.song_title) as TextView
         val artistView = songLay.findViewById<View>(R.id.song_artist) as TextView
         //get song using position
         val currSong = songs!!.get(pos)
         //get title and artist strings
         songView.setText(currSong.title)
         artistView.setText(currSong.artist)
         //set position as tag
         songLay.tag = pos
         return songLay
     }
 }