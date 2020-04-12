package com.pongo.agbaradio;

import org.json.JSONArray;

import java.util.ArrayList;

public class StaticDB {


  public static ArrayList<String[]> DB = new ArrayList<>();

  static{

    for (int i = 0; i < 3 ; i++) {
      String f = "Channel " + (i +1);
      if(i ==1){
        String[] d= { f, "http://edge.mixlr.com/channel/eerso"};
        DB.add(d);
      }else{
        String[] d = {f,"http://www.music.helsinki.fi/tmt/opetus/uusmedia/esim/a2002011001-e02.wav"} ;
        DB.add(d);
      }
    }

  }
}
