package com.poly.megagame.view.math;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.megagame.R;
import com.poly.megagame.base.Constants;
import com.poly.megagame.model.math.Question;
import com.poly.megagame.model.math.User;

public class MathGamePlayActivity extends AppCompatActivity {

    public static final int MAX_TIMER = 5;

    private TextView txtScore;
    private TextView txtBest;
    private TextView txtTimer;
    private TextView txtQuestion;
    private ImageView btnTrue;
    private ImageView btnFalse;

    private Handler handler;
    private int mTimer;
    private int mScore;
    private int mLevel;
    private boolean isRunning;
    private Question mQuestion;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game_play);

        initView();

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case Constants.UPDATE_QUESTION:
                        mTimer = MAX_TIMER;
                        String question = mQuestion.show();

                        txtTimer.setText(String.valueOf(mTimer));
                        txtQuestion.setText(question);
                        break;

                    case Constants.UPDATE_TIMER:
                        mTimer = message.arg1;
                        txtTimer.setText(String.valueOf(mTimer));
                        break;

                    case Constants.QUESTION_TRUE:
                        mScore = mUser.getScore();
                        txtScore.setText("Score: " + String.valueOf(mScore));
                        Toast.makeText(getApplicationContext(), "Bạn đã trả lời đúng", Toast.LENGTH_SHORT).show();
                        break;

                    case Constants.QUESTION_FALSE:
                        Toast.makeText(getApplicationContext(), "Bạn đã trả lời sai", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        };

        playGame();
    }

    private void initView() {
        txtScore = findViewById(R.id.txtScore);
        txtBest = findViewById(R.id.txtBest);
        txtTimer = findViewById(R.id.txtTimer);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
    }

    private void playGame() {
        mUser = new User("user");
        mQuestion = new com.poly.megagame.model.math.Question();
        mLevel = com.poly.megagame.model.math.Question.EASY;
        mTimer = MAX_TIMER;
        makeQuestion();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (mTimer > -1 && isRunning) {
                    Message message = new Message();
                    message.what = Constants.UPDATE_TIMER;
                    message.arg1 = mTimer;
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mTimer--;

                }
                finish();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        isRunning = true;

    }

    public void onClickTrue(View view){
        checkQuestion(true);
    }

    public void onClickFalse(View view){
        checkQuestion(false);
    }

    private void makeQuestion() {
        checkLevel();
        mQuestion.makeQuestion(mLevel);
        handler.sendEmptyMessage(Constants.UPDATE_QUESTION);
    }

    private void checkQuestion(boolean b) {
        if (mQuestion.checkQuestion(b)) {
            mUser.increaseScore();
            handler.sendEmptyMessage(Constants.QUESTION_TRUE);
            makeQuestion();

        } else {
            isRunning = false;
            handler.sendEmptyMessage(Constants.QUESTION_FALSE);
        }
    }

    private void checkLevel() {
        if (mScore == Question.NORMAL) {
            mLevel = Question.NORMAL;
        }
        if (mScore == Question.HARD){
            mLevel = Question.HARD;
        }
    }
}
