package com.pongo.agbaradio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {


  private int SPLASH_TIME = 3000;
  Animation topBottomAnimation, bottomTopAnimation;
  ImageView logo;
  TextView theme, AGBA;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    getSupportActionBar().hide();
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    bottomTopAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
    topBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
    logo = findViewById(R.id.music_logo);
    theme = findViewById(R.id.theme);
    AGBA = findViewById(R.id.agba);
    theme.setAnimation(topBottomAnimation);
    logo.setAnimation(topBottomAnimation);
    AGBA.setAnimation(bottomTopAnimation);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mainPage();
      }
    }, SPLASH_TIME);
  }

  public void mainPage() {
    Intent nextpage = new Intent(this, MainActivity.class);
    startActivity(nextpage);
    finish();
  }

}
