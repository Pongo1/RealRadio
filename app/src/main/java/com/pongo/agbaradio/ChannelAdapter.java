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

  public ChannelAdapter(Context context, ArrayList<String[]> channels,TextView activeChannelItem, TextView streamStatusItem) {
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

  @Override
  public void onBindViewHolder(@NonNull final ChannelAdapter.myHolder holder, final int position) {
    final String url = channels.get(position)[1];
    holder.channelName.setText(channels.get(position)[0]);
    holder.playButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
       streamContent(position);
      }
    });
    holder.cardAsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
       streamContent(position);
      }
    });
  }

  public void streamContent(int pos) {
    String[] item = channels.get(pos);
    MessageStreamer AGBAStreamer = new MessageStreamer(item[1],item[0],streamStatusItem);
    AGBAStreamer.prepareAndStart();
    activeChannelItem.setText(AGBAStreamer.channelName);
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
