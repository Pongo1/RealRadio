package com.pongo.agbaradio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

//This class accepts a string URL and a String Channel Name to be passed to the android media player
// All streaming happen here
public class MessageStreamer {
  public String URL, channelName;
  String streamStatus =null;
  TextView streamStatusItem;
  MediaPlayer mediaPlayer = new MediaPlayer();
  MessageStreamer(String URL, String channelName, TextView streamStatusItem){
    this.URL = URL;
    this.channelName = channelName;
    this.streamStatusItem = streamStatusItem;
  }

  public void prepareAndStart(){
    if(streamStatusItem != null){
      stopStream();
    }
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    try {
      mediaPlayer.setDataSource(URL);
    } catch (IOException e) {
      e.printStackTrace();
    }
    mediaPlayer.prepareAsync();
    streamStatus = "Preparing to play...";
    streamStatusItem.setText(streamStatus);
    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mediaPlayer) {
        streamStatus = "Playing now...";
        streamStatusItem.setText(streamStatus);
        mediaPlayer.start();
      }
    });

  }

  public void stopStream(){
    try {
      if (mediaPlayer.isPlaying()) {
        mediaPlayer.stop();
        mediaPlayer.release();
        //mediaPlayer = null;
      }
    }catch(Exception e){

    }
    streamStatus = "Stopped, not playing";
  }

  public String currentChannelName(){
    return channelName;
  }

  public String streamStatus(){
    return streamStatus;
  }
}
