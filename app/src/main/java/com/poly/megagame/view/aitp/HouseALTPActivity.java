package com.poly.megagame.view.aitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.megagame.R;
import com.poly.megagame.view.HouseActivity;


public class HouseALTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_altp);


    }

    public void onClickPlayNow(View view) {
        startActivity(new Intent(getApplicationContext(), PlayGameALTPActivity.class));
    }

    public void onClickAddRequestion(View view) {
        startActivity(new Intent(getApplicationContext(), AddRequestionALTPActivity.class));
    }

    public void onClickQuit(View view) {
        Intent intent = new Intent(getApplicationContext(), HouseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
