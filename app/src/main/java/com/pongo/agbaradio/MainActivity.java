package com.pongo.agbaradio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.channels.Channel;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

  TextView activeChannel, streamStatus;
  RequestQueue mReq;
  String API = "https://poole23.herokuapp.com/api/radios/all";
  ChannelAdapter channelAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mReq = Volley.newRequestQueue(this); //volley stuff to be continued
    //..........
    activeChannel = findViewById(R.id.active_channel);
    streamStatus = findViewById(R.id.stream_status);
    final RecyclerView recyclerView = findViewById(R.id.channel_recycler);


    JsonArrayRequest json =  new JsonArrayRequest(Request.Method.POST, API, null, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray response) {
        ChannelAdapter channelAdapter = new ChannelAdapter(MainActivity.this,response,activeChannel,streamStatus);
        Toast.makeText(MainActivity.this, "Data found!", Toast.LENGTH_SHORT).show();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(channelAdapter);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.d("Error:fetchingReq ----<",error.getMessage());
      }
    });

    mReq.add(json);


  }
}


