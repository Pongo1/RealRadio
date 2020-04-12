package com.pongo.agbaradio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * ChannelAdapter accepts the app Context, an ArrayList of Strings, and two textViews that can be found on the bottom
 * sheet of "active_main" layout
 * the TextViews are updated per the state of the media player that the "MessageStreamer" class uses
 * */
public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.myHolder> {
  ArrayList<String[]> channels;
  Context context;
  TextView activeChannelItem, streamStatusItem;
  Button currentlyPlayingButton = null; //the previously selected item
  String btnState = "Stopped";
  MessageStreamer AGBAStreamer;


  public ChannelAdapter(Context context, ArrayList<String[]> channels, TextView activeChannelItem, TextView streamStatusItem) {
    this.channels = channels;
    this.context = context;
    this.activeChannelItem = activeChannelItem;
    this.streamStatusItem = streamStatusItem;
  }

  @NonNull
  @Override
  public ChannelAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_card, parent, false);
    myHolder holder = new myHolder(v);
    return holder;
  }


  public void switchButtonState() {
    if (currentlyPlayingButton != null) {
      //switch old button icon from pause to play
      currentlyPlayingButton.setBackgroundResource(R.drawable.left_of_channel_card);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull final ChannelAdapter.myHolder holder, final int position) {
    holder.channelName.setText(channels.get(position)[0]);
    holder.playButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switchButtonState();
        streamContent(position, holder);
      }
    });
    holder.cardAsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switchButtonState();
        streamContent(position, holder);
      }
    });
  }

  public void paintCard(String how,ChannelAdapter.myHolder holder){

  }

  public void streamContent(int pos, ChannelAdapter.myHolder holder) {
    String[] item = channels.get(pos);
    if(currentlyPlayingButton!=null) {
      //its not the first time,  btn has already been clicked
      //if its the same button, check it's state whether playing or not,
      //if it is not the same, put the pause icon on the current btn and play btn on the prev button ( NB: pause icon indicates that the currently selected is playing
      if(currentlyPlayingButton == holder.playButton){
        if(btnState == "Playing"){
          AGBAStreamer.stopStream();
          streamStatusItem.setText("Ready when you are");
          btnState = "Stopped";
          holder.playButton.setBackgroundResource(R.drawable.left_of_channel_card);
        }else{
          AGBAStreamer = new MessageStreamer(item[1], item[0], streamStatusItem);
          AGBAStreamer.prepareAndStart();
          btnState = "Playing";
          holder.playButton.setBackgroundResource(R.drawable.pause_channel_card);
        }
      }else{
        AGBAStreamer.stopStream();
        AGBAStreamer = new MessageStreamer(item[1], item[0], streamStatusItem);
        AGBAStreamer.prepareAndStart();
        btnState = "Playing";
        holder.playButton.setBackgroundResource(R.drawable.pause_channel_card);
      }

    }else{
      //means its the first time a channel has been clicked just put the pause icon
      AGBAStreamer = new MessageStreamer(item[1], item[0], streamStatusItem);
      AGBAStreamer.prepareAndStart();
      btnState = "Playing";
      holder.playButton.setBackgroundResource(R.drawable.pause_channel_card);
    }
    activeChannelItem.setText(AGBAStreamer.channelName);
    currentlyPlayingButton = holder.playButton;
  }

  @Override
  public int getItemCount() {
    return channels.size();
  }

  class myHolder extends RecyclerView.ViewHolder {
    TextView channelName;
    CardView cardAsButton;
    Button playButton;

    public myHolder(@NonNull View itemView) {
      super(itemView);
      this.channelName = itemView.findViewById(R.id.channel_name);
      this.cardAsButton = itemView.findViewById(R.id.whole_card);
      this.playButton = itemView.findViewById(R.id.channel_play_button);

    }
  }
}
