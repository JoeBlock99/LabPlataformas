package com.example.musicplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.ListView

import android.widget.MediaController.MediaPlayerControl

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;

import com.example.musicplayer.MusicService.MusicBinder

import android.media.MediaPlayer
import java.util.*


class MainActivity : AppCompatActivity(), MediaPlayerControl {


    val songList: ArrayList<Song> = ArrayList<Song>()
    lateinit var songView:ListView
    val controller: MusicController? = null
    var musicSrv: MusicService? = null
    var playIntent: Intent? = null
    var musicBound = false
    var paused = false
    var playbackPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songView = findViewById<ListView>(R.id.song_list)
        //songList = ArrayList<Song>()

        getSongList()

        Collections.sort(
            songList
        ) { a, b -> a.title!!.compareTo(b.title!!) }

        val songAdt = SongAdapter(this, songList)
        songView.adapter = songAdt

        setController()
    }

    override fun onStart() {
        super.onStart()
        if (playIntent == null) {
            playIntent = Intent(this, MusicService::class.java)
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE)
            startService(playIntent)
        }
    }

    //connect to the service
    private val musicConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicBinder
            //get service
            musicSrv = binder.getService()
            //pass list
            musicSrv!!.setList(songList)
            musicBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            musicBound = false
        }
    }

    fun getSongList() {
        //retrieve song info
        val musicResolver = contentResolver
        val musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor = musicResolver.query(musicUri, null, null, null, null)

        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            val titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
            val idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
            val artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST)
            //add songs to list
            do {
                val thisId = musicCursor.getLong(idColumn)
                val thisTitle = musicCursor.getString(titleColumn)
                val thisArtist = musicCursor.getString(artistColumn)
                songList.add(Song(thisId, thisTitle, thisArtist))
            } while (musicCursor.moveToNext())
        }
    }

    private fun setController() {
        //set the controller up
        var controller = MusicController(this)

        controller.setPrevNextListeners(object : View.OnClickListener() {
            fun onClick(v: View) {
                playNext()
            }
        }, object : View.OnClickListener() {
            fun onClick(v: View) {
                playPrev()
            }
        })

        controller.setMediaPlayer(this)
        controller.setAnchorView(findViewById(R.id.song_list))
        controller.isEnabled = true
    }

    fun songPicked(view: View) {
        musicSrv.setSong(Integer.parseInt(view.tag.toString()))
        musicSrv.playSong()
        if (playbackPaused) {
            setController()
            playbackPaused = false
        }
        controller.show(0)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //menu item selected
        when (item.itemId) {
            R.id.action_shuffle -> {
                musicSrv!!.setShuffle()
            }
            R.id.action_end -> {
                stopService(playIntent)
                musicSrv = null
                System.exit(0)
            }
        }//shuffle
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        stopService(playIntent)
        musicSrv = null
        super.onDestroy()
    }

    fun playNext(){
        songPosn=songs.size-1
        playSong()
    }

    //play previous

   /* private fun playPrev() {
        musicSrv.playPrev()
        if (playbackPaused) {
            setController()
            playbackPaused = false
        }
        controller.show(0)
    }*/

    override fun canPause(): Boolean {
        return true
    }

    override fun canSeekBackward(): Boolean {
        return true
    }

    override fun canSeekForward(): Boolean {
        return true
    }
    /*@Override
    public int getCurrentPosition() {
        if(musicSrv!=null &amp;&amp; musicBound &amp;&amp; musicSrv.isPng())
        return musicSrv.getPosn();
        else return 0;
    }
    @Override
    public int getDuration() {
        if(musicSrv!=null &amp;&amp; musicBound &amp;&amp; musicSrv.isPng())
        return musicSrv.getDur();
        else return 0;
    }
    @Override
    public boolean isPlaying() {
        if(musicSrv!=null &amp;&amp; musicBound)
        return musicSrv.isPng();
        return false;
    }*/

    override fun pause() {
        playbackPaused = true
        musicSrv!!.pausePlayer()
    }

    override fun seekTo(pos: Int) {
        musicSrv!!.seek(pos)
    }

    override fun start() {
        musicSrv!!.go()
    }

    override fun onPause() {
        super.onPause()
        paused = true
    }

    override fun onResume() {
        super.onResume()
        if (paused) {
            setController()
            paused = false
        }
    }

    override fun onStop() {
        controller!!.hide()
        super.onStop()
    }

    fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        mp.reset()
        return false
    }
   /* @Override
    public void onCompletion(MediaPlayer mp) {
        if(player.getCurrentPosition()&gt;0){
            mp.reset();
            playNext();
        }
    }*/


}