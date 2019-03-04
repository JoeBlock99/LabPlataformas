package com.example.musicplayer

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.content.ComponentName
import com.example.musicplayer.MusicService.MusicBinder
import android.content.ServiceConnection
import android.content.ContentUris
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList


class MusicService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener{
    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompletion(mp: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //media player
    var player: MediaPlayer = MediaPlayer()
    //song list
    var songs: ArrayList<Song> = ArrayList()
    //current position
    var songPosn: Int = 0
    var musicSrv: MusicService = MusicService()
    var playIntent: Intent? = null
    var musicBound = false
    var musicBind = MusicBinder()
    var shuffle = false
    var rand: Random = Random()
    //private String songTitle=&quot;&quot
    //private static final int NOTIFY_ID=1

    override fun onCreate() {
        //create the service
        //create the service
        super.onCreate()
//initialize position
        songPosn=0
//create player
        player = MediaPlayer()
        initMusicPlayer()

        rand = Random()

    }

    private val musicConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicBinder
            //get service
            musicSrv = binder.service
            //pass list
            musicSrv!!.setList(songs)
            musicBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            musicBound = false
        }
    }

    fun initMusicPlayer() {
        //set player properties
        player!!.setWakeMode(getApplicationContext(),
            PowerManager.PARTIAL_WAKE_LOCK)
        player!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        player!!.setOnPreparedListener(this)
        player!!.setOnCompletionListener(this)
        player!!.setOnErrorListener(this)
    }

    fun setList(theSongs: ArrayList<Song>) {
        songs = theSongs
    }

    inner class MusicBinder : Binder() {
        internal val service: MusicService
            get() = this@MusicService
    }

    override fun onBind(arg0: Intent): IBinder? {
        // TODO Auto-generated method stub
        return musicBind
    }

    override fun onUnbind(intent: Intent): Boolean {
        player!!.stop()
        player!!.release()
        return false
    }

    fun playSong() {
        //play a song
        player!!.reset()
        //get song
        val playSong = songs.get(songPosn)
//get id
        val currSong = playSong.id
//set uri
        val trackUri = ContentUris.withAppendedId(
            android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            currSong
        )
        songTitle=playSong.title
        try {
            player.setDataSource(applicationContext, trackUri)
        } catch (e: Exception) {
            Log.e("MUSIC SERVICE", "Error setting data source", e)
        }
        player.prepareAsync()

    }

    override fun onPrepared(mp: MediaPlayer) {
        //start playback
        Intent notIntent = new Intent(this, MainActivity.class)
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
        notIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        Notification.Builder builder = new Notification.Builder(this)

        builder.setContentIntent(pendInt)
            .setSmallIcon(R.drawable.play)
            .setTicker(songTitle)
            .setOngoing(true)
            .setContentTitle(&quot;Playing&quot;)
        .setContentText(songTitle);
        Notification not = builder.build();

        startForeground(NOTIFY_ID, not)
        mp.start()
    }

    fun setSong(songIndex: Int) {
        songPosn = songIndex
    }

    fun getPosn(): Int {
        return player!!.getCurrentPosition()
    }

    fun getDur(): Int {
        return player!!.getDuration()
    }

    fun isPng(): Boolean {
        return player!!.isPlaying()
    }

    fun pausePlayer() {
        player!!.pause()
    }

    fun seek(posn: Int) {
        player!!.seekTo(posn)
    }

    fun go() {
        player!!.start()
    }
    public void playPrev(){
        songPosn--;
        if(songPosn&lt;0) songPosn=songs.size()-1;
        playSong();
    }
    public void playNext(){
        if(shuffle){
            int newSong = songPosn;
            while(newSong==songPosn){
                newSong=rand.nextInt(songs.size());
            }
            songPosn=newSong;
        }
        else{
            songPosn++;
            if(songPosn&gt;=songs.size()) songPosn=0;
        }
        playSong();
    }


    fun setShuffle() {
        if (shuffle)
            shuffle = false
        else
            shuffle = true
    }

    override fun onDestroy() {
        stopForeground(true)
    }




}