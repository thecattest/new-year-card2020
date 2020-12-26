package com.thecattest.samsung.newyearcard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    Boolean played = false;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mp = MediaPlayer.create(this, R.raw.jb);
        anim = AnimationUtils.loadAnimation(this, R.anim.bell_anim);
        ImageView bell = findViewById(R.id.bell);
        TextView tv = findViewById(R.id.tv);

        bell.setOnClickListener(v -> {
            if(tv.getVisibility() == View.INVISIBLE) {
                played = true;
                mp.start();
                tv.setVisibility(View.VISIBLE);
                v.startAnimation(anim);
            } else {
                mp.pause();
                played = false;
                tv.setVisibility(View.INVISIBLE);
                v.getAnimation().cancel();
                v.clearAnimation();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(played) mp.start();
    }
}