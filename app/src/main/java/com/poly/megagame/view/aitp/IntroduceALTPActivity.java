package com.poly.megagame.view.aitp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.megagame.R;
import com.poly.megagame.utils.MediaUtils;

public class IntroduceALTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_altp);

        MediaUtils.getInstance().play(R.raw.nhac_mo_dau,null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), HouseALTPActivity.class));
                finish();
            }
        },2500);
    }
}
