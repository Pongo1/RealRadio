package com.pongo.agbaradio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.nio.channels.Channel;

public class MainActivity extends AppCompatActivity {

  TextView activeChannel, streamStatus;
  RequestQueue mReq;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mReq = Volley.newRequestQueue(this); //volley stuff to be continued
    //..........

    activeChannel = findViewById(R.id.active_channel);
    streamStatus = findViewById(R.id.stream_status);
    RecyclerView recyclerView = findViewById(R.id.channel_recycler);
    ChannelAdapter channelAdapter = new ChannelAdapter(this,StaticDB.DB,activeChannel,streamStatus);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(channelAdapter);
  }
}
