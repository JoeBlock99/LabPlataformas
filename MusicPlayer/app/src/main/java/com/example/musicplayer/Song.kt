package com.example.musicplayer
class Song (var id: Long = 0,
            var title: String,
            var artist: String){

   fun Song(songID: Long, songTitle: String, songArtist: String){
        id = songID
        title = songTitle
        artist = songArtist
    }
}
