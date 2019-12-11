package com.poly.megagame.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.poly.megagame.R;

import java.util.ArrayList;

public class playgame extends AppCompatActivity {
    public MediaPlayer player1;
    public MediaPlayer player;
    Button A, B, C, D;
    TextView cauhoi;
    int count = 0;
    //ArrayList<Question> listQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        cauhoi = findViewById(R.id.cauhoi);
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        final Drawable drawable1 = getResources().getDrawable(R.drawable.answer);
        drawable1.setColorFilter(Color.parseColor("#246bce"), PorterDuff.Mode.SRC_ATOP);
        player1 = MediaPlayer.create(playgame.this, R.raw.batdauchoi);
        player1.start();
        A.setBackgroundDrawable(drawable1);
        B.setBackgroundDrawable(drawable1);
        C.setBackgroundDrawable(drawable1);
        D.setBackgroundDrawable(drawable1);
    }
    public void ChonDapAn(final View view){
       if (view.getId() == R.id.A ||
            view.getId() == R.id.B ||
            view.getId() == R.id.C ||
            view.getId() == R.id.D){
           view.setBackgroundResource(R.drawable.button3);
           A.setEnabled(false);
           B.setEnabled(false);
           C.setEnabled(false);
           D.setEnabled(false);
//           count = 1;
//           if ( count < 16 ){
//               hienthicauhoi();
//           }
//           try {
//               Thread.sleep(10000);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
//           new Handler().postDelayed(new Runnable() {
//               @Override
//               public void run() {
//                   switch ( view.getId() ) {
//                       case R.id.A:
//                           playSound(R.raw.true_a);
//                           break;
//
//                       case R.id.B:
//                           playSound(R.raw.true_b);
//                           break;
//
//                       case R.id.C:
//                           playSound(R.raw.true_c);
//                           break;
//
//                       case R.id.D:
//                           playSound(R.raw.true_d);
//                           break;
//                   }
//               }
//           }, 10000);
       }
    }
//    public void hienthicauhoi(){
////        tablevcoin(count);
////        trueCase();
////        playSound(SOUND_QUESTIONS[count - 1]);
////        action();
//       // tv_socau.setText("Câu " + count);
//        cauhoi.setText(listQuestion.get(count - 1).cauhoi);
//        A.setText("A. " +listQuestion.get(count - 1).caseA);
//        B.setText("B. " + listQuestion.get(count - 1).caseB);
//        C.setText("C. " + listQuestion.get(count - 1).caseC);
//        D.setText("D. " + listQuestion.get(count - 1).caseD);
//        Log.d("cauhoi", "hienthicauhoi: " +listQuestion);
////        time = 30;
////        tv_time.setText(time+"");
////        thoigian();
//    }
    public void playSound(int type) {
       player  = MediaPlayer.create(this, type);
        player.start();
    }
    @Override
    public void onStop() {
        player1.stop();
        super.onStop();

    }
}
