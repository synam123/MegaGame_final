package com.poly.megagame.view.aitp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.megagame.R;
import com.poly.megagame.adapter.AnswerALTPAdapter;
import com.poly.megagame.adapter.PointMiliestonesALTPAdapter;
import com.poly.megagame.callback.AnswerListener;
import com.poly.megagame.callback.PlayMediaComplete;
import com.poly.megagame.callback.TimeCoundownListener;
import com.poly.megagame.model.altp.RequestionALTP;
import com.poly.megagame.utils.HighScoreUtils;
import com.poly.megagame.utils.MediaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayGameALTPActivity extends AppCompatActivity {

    private ArrayList<RequestionALTP> arrayRequestion = new ArrayList<>();
    private ArrayList<String>       arrayAnswer = new ArrayList<>();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private AnswerALTPAdapter answerAdapter;
    private RecyclerView recAnswer,recPointMilestones;
    private TextView txtTimeCountDown,txtRequestion;
    private ImageView   img50_50,imgCall,imgAdvisory;
    private PointMiliestonesALTPAdapter miliestonesAdapter;
    private Handler handler = new Handler();
    private Handler handlerTimeCountDown = new Handler();
    private int countRules = 0;
    private int countRequestion = 0;
    private int countTimeCountDown = 0;
    private Runnable runnablePlayGame,runnableTimeCountDown;
    private boolean isFrom1To5 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame_altp);

        initView();
        playMedia();
        createPointMiliestones();
        createAnswer();
    }

    private void createAnswer() {
        recAnswer.setHasFixedSize(true);
        recAnswer.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.VERTICAL,false));
        answerAdapter = new AnswerALTPAdapter(arrayAnswer);
        recAnswer.setAdapter(answerAdapter);
        answerAdapter.setAnswerListener(new AnswerListener() {
            @Override
            public void onComplete() {
                audioWin();
                countRequestion++;
                handler.postDelayed(runnablePlayGame,3000);
            }
            @Override
            public void onFailure() {
                audioLoser();
                HighScoreUtils.getInstance().setALTPHighScore(countRequestion);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loserDialog();
                    }
                },2000);
            }
        });
        answerAdapter.setTimeCoundownListener(new TimeCoundownListener() {
            @Override
            public void onPause() {
                handlerTimeCountDown.removeCallbacks(runnableTimeCountDown);
            }
        });
    }

    private void audioWin() {
        switch (countRequestion){
            case 0:
            case 1:
            case 2:
            case 3:
                MediaUtils.getInstance().plays(R.raw.answer_1_4_true);
            case 4:
                MediaUtils.getInstance().plays(R.raw.answer_5_true);
                break;
            case 5:
                MediaUtils.getInstance().plays(R.raw.answer_6_true);
                break;
            case 6:
                MediaUtils.getInstance().plays(R.raw.answer_7_true);
                break;
            case 7:
                MediaUtils.getInstance().plays(R.raw.answer_8_true);
                break;
            case 8:
                MediaUtils.getInstance().plays(R.raw.answer_9_true);
                break;
            case 9:
                MediaUtils.getInstance().plays(R.raw.answer_10_true);
                break;
            case 10:
                MediaUtils.getInstance().plays(R.raw.answer_11_true);
                break;
            case 11:
                MediaUtils.getInstance().plays(R.raw.answer_12_true);
                break;
            case 12:
                MediaUtils.getInstance().plays(R.raw.answer_13_true);
                break;
            case 13:
                MediaUtils.getInstance().plays(R.raw.answer_14_true);
                break;
            case 14:
                MediaUtils.getInstance().plays(R.raw.answer_15_true);
                break;
        }
    }

    private void audioLoser() {
        switch (countRequestion){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                MediaUtils.getInstance().plays(R.raw.answer_1_5_false);
                break;
            case 5:
                MediaUtils.getInstance().plays(R.raw.answer_6_false);
                break;
            case 6:
                MediaUtils.getInstance().plays(R.raw.answer_7_false);
                break;
            case 7:
                MediaUtils.getInstance().plays(R.raw.answer_8_false);
                break;
            case 8:
                MediaUtils.getInstance().plays(R.raw.answer_9_false);
                break;
            case 9:
                MediaUtils.getInstance().plays(R.raw.answer_10_false);
                break;
            case 10:
                MediaUtils.getInstance().plays(R.raw.answer_11_false);
                break;
            case 11:
                MediaUtils.getInstance().plays(R.raw.answer_12_false);
                break;
            case 12:
                MediaUtils.getInstance().plays(R.raw.answer_13_false);
                break;
            case 13:
                MediaUtils.getInstance().plays(R.raw.answer_14_false);
                break;
            case 14:
                MediaUtils.getInstance().plays(R.raw.answer_15_false);
                break;
        }
    }

    private void playRequestionCountDown() {
        if (txtTimeCountDown.getVisibility() == View.GONE) {
            txtTimeCountDown.setVisibility(View.VISIBLE);
        }
        txtTimeCountDown.setText("30");

        countTimeCountDown = 0;

        runnableTimeCountDown = new Runnable() {
            @Override
            public void run() {
                txtTimeCountDown.setText(String.valueOf(30 - countTimeCountDown));
                countTimeCountDown ++;

                if (countTimeCountDown == 31){
                    loserDialog();
                    audioLoser();
                    return;
                }
                handlerTimeCountDown.postDelayed(this,1000);
            }
        };
        runnableTimeCountDown.run();
    }

    private void loserDialog(){
        new AlertDialog.Builder(PlayGameALTPActivity.this)
                .setTitle("Thua Cuộc")
                .setCancelable(false)
                .setMessage("Bạn đã trả lời sai câu số " + countRequestion + " bạn sẽ ra về với số tiền thưởng là " + getPrice(countRequestion))
                .setPositiveButton("Trở lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private String getPrice(int requestion){
        switch (requestion){
            case 0:
                return "200.000 VND";
            case 1:
                return "400.000 VND";
            case 2:
                return "600.000 VND";
            case 3:
                return "1.000.000 VND";
            case 4:
                return "2.000.000 VND";
            case 5:
                return "3.000.000 VND";
            case 6:
                return "6.000.000 VND";
            case 7:
                return "8.000.000 VND";
            case 8:
                return "14.000.000 VND";
            case 9:
                return "22.000.000 VND";
            case 10:
                return "30.000.000 VND";
            case 11:
                return "40.000.000 VND";
            case 12:
                return "60.000.000 VND";
            case 13:
                return "85.000.000 VND";
            case 14:
                return "150.000.000 VND";
        }
        return "";
    }

    private void createPointMiliestones() {
        recPointMilestones.setHasFixedSize(true);
        recPointMilestones.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,true));
        miliestonesAdapter = new PointMiliestonesALTPAdapter();
        recPointMilestones.setAdapter(miliestonesAdapter);
    }

    private void playMedia() {
        MediaUtils.getInstance().play(R.raw.bat_dau_tro_choi, new PlayMediaComplete() {
            @Override
            public void onComplete() {
                introducRules();
            }
        });
    }

    private void showNotifiPlayGame() {
        try {
            new AlertDialog.Builder(this)
                    .setMessage("Bạn đã hiểu rõ luật chơi của chương trình chưa ?")
                    .setNegativeButton("Tôi đã hiểu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            playGames();
                        }
                    })
                    .setPositiveButton("Tôi chưa hiểu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            introducRules();
                        }
                    })
                    .show();
        }catch (WindowManager.BadTokenException windowManager){

        }
    }

    private void playGames() {
        // Play media start
        MediaUtils.getInstance().play(R.raw.bat_dau_di_tim_ai_la_trieu_phu, new PlayMediaComplete() {
            @Override
            public void onComplete() {
                runnablePlayGame = new Runnable(){
                    @Override
                    public void run() {

                        if (countRequestion >= 0 && countRequestion <=5){
                            if (!isFrom1To5) {
                                MediaUtils.getInstance().play(R.raw.cau_hoi_1_den_5, null);
                                isFrom1To5 = true;
                            }
                        }else if (countRequestion > 6 && countRequestion <= 10){
                            MediaUtils.getInstance().play(R.raw.cau_hoi_6_den_10, null);
                        }

                        if (arrayRequestion.size() == 0){
                            Toast.makeText(getApplicationContext(), "Chưa có câu hỏi nào khả dụng", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        RequestionALTP requestion = arrayRequestion.get(countRequestion);
                        playRequestion(requestion);
                    }
                };
                runnablePlayGame.run();
            }
        });

        // Get requestion from database
        FirebaseFirestore.getInstance()
                .collection("Requestion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() == null){
                            return;
                        }

                        Random random = new Random();

                        if (!task.getResult().isEmpty()){
                            List<RequestionALTP> requestions = task.getResult().toObjects(RequestionALTP.class);

                            for (int i=0; i < 15; i++){

                                int position = random.nextInt(requestions.size() - 1);

                                arrayRequestion.add(requestions.get(position));
                                requestions.remove(requestions.get(position));
                            }
                        }
                    }
                });
    }

    private void playRequestion(final RequestionALTP requestion){
        final char[] arrayReq = requestion.getRequestion().toCharArray();
        final StringBuilder requestionBuilder = new StringBuilder("Câu hỏi " + (countRequestion + 1) + ": ");
        new Runnable(){
            int requestionCount = 0;
            @Override
            public void run() {
                requestionBuilder.append(arrayReq[requestionCount]);
                txtRequestion.setText(requestionBuilder);

                requestionCount++;
                
                if (requestionCount == arrayReq.length){
                    addAnswer(requestion);
                    return;
                }
                
                handler.postDelayed(this,100);
            }
        }.run();
    }

    private void addAnswer(final RequestionALTP requestion) {
        arrayAnswer.clear();
        new Handler().postDelayed(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                arrayAnswer.add(requestion.getArrayAnswer().get(count));
                answerAdapter.notifyDataSetChanged();
                count++;

                if (count == 4){
                    answerAdapter.setRightAnswer(requestion.getAnswer());
                    answerAdapter.setCanSelect();
                    playRequestionCountDown();
                    return;
                }

                handler.postDelayed(this,2000);
            }
        },1000);
    }

    private void introducRules() {
        img50_50.setVisibility(View.INVISIBLE);
        imgCall.setVisibility(View.INVISIBLE);
        imgAdvisory.setVisibility(View.INVISIBLE);

        // Play media
        MediaUtils.getInstance().play(R.raw.gioi_thieu_luat_choi,null);

        drawerLayout.openDrawer(GravityCompat.START);
        miliestonesAdapter.sliderPoint();

        // Slide introduce
        countRules = 0;
        String introduceRulesText = "Có tất cả 15 câu hỏi đang đợi bạn giải quyết. Có 3 mốc quan trọng bạn cần phải vượt qua là 5, 10, 15 và bạn có 3 sự trợ giúp là 50-50, Gọi điện thoại cho người thân và Hỏi ý kiến trong trường quay.";
        final char[] arrayRules = introduceRulesText.toCharArray();
        final StringBuilder introduceDelay = new StringBuilder();
        new Runnable(){
            @Override
            public void run() {

                introduceDelay.append(arrayRules[countRules]);
                txtRequestion.setText(introduceDelay);
                countRules++;

                // Play right to help
                if (countRules == 130){
                    MediaUtils.getInstance().plays(R.raw.nhan_3_su_tro_giup);
                }

                // Display help right 1
                if (countRules == 133){
                    img50_50.setVisibility(View.VISIBLE);
                }

                // Display help right 2
                if (countRules == 150){
                    imgCall.setVisibility(View.VISIBLE);
                }

                // Display help right 3
                if (countRules == 165){
                    imgAdvisory.setVisibility(View.VISIBLE);
                }

                if (countRules == 90){
                    miliestonesAdapter.slidePointMiliestones();
                }

                if (countRules == 115){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                // Show notification play game
                if (countRules == arrayRules.length){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotifiPlayGame();
                        }
                    },2000);
                    return;
                }
                handler.postDelayed(this,100);
            }
        }.run();
    }

    private void initView() {
        txtTimeCountDown = findViewById(R.id.txtTimeCountDown);
        drawerLayout = findViewById(R.id.drawerLayout);
        txtRequestion = findViewById(R.id.txtRequestion);
        img50_50 = findViewById(R.id.img50_50);
        imgCall = findViewById(R.id.imgCall);
        imgAdvisory = findViewById(R.id.imgAdvisory);
        recAnswer = findViewById(R.id.recAnswer);
        navigationView = findViewById(R.id.navigationView);
        recPointMilestones = navigationView.getHeaderView(0).findViewById(R.id.recPointMilestones);
    }
}
