package com.poly.megagame.view.math;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.megagame.R;

public class MathGameActivity extends AppCompatActivity {

    private TextView txtBestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        initView();
    }

    private void initView() {
        txtBestScore = findViewById(R.id.txtBestScore);
    }

    public void onClickPlayGame(View view){
        Intent itent = new Intent(this, MathGamePlayActivity.class);
        startActivity(itent);
    }
}
