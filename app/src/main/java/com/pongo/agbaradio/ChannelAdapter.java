package com.pongo.agbaradio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.myHolder> {
  ArrayList<String[]> channels;

  public ChannelAdapter(ArrayList<String[]> channels) {
    this.channels = channels;
  }

  @NonNull
  @Override
  public ChannelAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_card, parent, false);
    myHolder holder = new myHolder(v);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ChannelAdapter.myHolder holder, int position) {
    holder.channelName.setText(channels.get(position)[0]);
  }

  @Override
  public int getItemCount() {
    return channels.size();
  }

  class myHolder extends RecyclerView.ViewHolder {
    TextView channelName;

    public myHolder(@NonNull View itemView) {
      super(itemView);
      this.channelName = itemView.findViewById(R.id.channel_name);
    }
  }
}
