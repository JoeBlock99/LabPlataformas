package com.example.musicplayer
class Song {

    var id: Long
    var title: String
    var artist: String

    constructor(songID: Long, songTitle: String, songArtist: String){
        id = songID
        title = songTitle
        artist = songArtist
    }
}
