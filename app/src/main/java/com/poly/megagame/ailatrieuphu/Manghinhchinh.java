package com.poly.megagame.ailatrieuphu;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.poly.megagame.R.id;
import static com.poly.megagame.R.layout;
import static com.poly.megagame.R.raw;

public class Manghinhchinh extends AppCompatActivity {
    Button star;
    public MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_manghinhchinh);
        star = findViewById(id.star);
        player = MediaPlayer.create(Manghinhchinh.this, raw.background_music);
        player.start();
//        final Drawable drawable = getResources().getDrawable(R.drawable.button3);
//        drawable.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
        star.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manghinhchinh.this, playgame.class);
                player.stop();
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStop() {
        player.stop();
        super.onStop();
    }
    // play game activity
//      new Handler().postDelayed(new Runnable() {
//        @Override
//        public void run() {
//            drawable.setColorFilter(Color.parseColor("#e65100"), PorterDuff.Mode.SRC_ATOP);
//            star.setBackgroundDrawable(drawable);
//        }
//    }, 100);
}
