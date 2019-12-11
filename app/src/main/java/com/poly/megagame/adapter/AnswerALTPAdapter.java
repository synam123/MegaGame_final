package com.poly.megagame.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.megagame.R;
import com.poly.megagame.callback.AnswerListener;
import com.poly.megagame.callback.TimeCoundownListener;

import java.util.ArrayList;

public class AnswerALTPAdapter extends RecyclerView.Adapter<AnswerALTPAdapter.ViewHolder> {

    private ArrayList<String>   arrayAnswer;
    private AnswerListener      answerListener;
    private int                 rightAnswer,count = 0;
    private boolean             canSelect = false;
    private TimeCoundownListener timeCoundownListener;

    public AnswerALTPAdapter(ArrayList<String> arrayAnswer) {
        this.arrayAnswer = arrayAnswer;
    }

    public AnswerListener getAnswerListener() {
        return answerListener;
    }

    public void setAnswerListener(AnswerListener answerListener) {
        this.answerListener = answerListener;
    }

    public void setTimeCoundownListener(TimeCoundownListener timeCoundownListener) {
        this.timeCoundownListener = timeCoundownListener;
    }

    public void setRightAnswer(int answer){
        rightAnswer = answer;
    }

    public void setCanSelect(){
        canSelect = true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_answer_altp,null);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (holder.getAdapterPosition()){
            case 0:
                holder.txtAnswer.setText("A: " + arrayAnswer.get(position));
                break;
            case 1:
                holder.txtAnswer.setText("B: " + arrayAnswer.get(position));
                break;
            case 2:
                holder.txtAnswer.setText("C: " + arrayAnswer.get(position));
                break;
            case 3:
                holder.txtAnswer.setText("D: " + arrayAnswer.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayAnswer.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtAnswer;

        ViewHolder(@NonNull View container) {
            super(container);

            txtAnswer = container.findViewById(R.id.txtAnswer);
            txtAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (canSelect) {
                        timeCoundownListener.onPause();
                        txtAnswer.setBackgroundResource(R.drawable.ic_custom_answer_wait);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (getAdapterPosition() == rightAnswer) {
                                    answerListener.onComplete();
                                    slideAnswer(true,txtAnswer);
                                } else {
                                    answerListener.onFailure();
                                    slideAnswer(false,txtAnswer);
                                }
                                txtAnswer.setBackgroundResource(R.drawable.ic_custom_answer);
                            }
                        }, 3000);
                        canSelect = false;
                    }
                }
            });
        }
    }

    private void slideAnswer(final boolean b, final View view) {
        count = 0;
        final Handler handler = new Handler();
        new Runnable(){
            @Override
            public void run() {
                if (b) {
                    switch (count) {
                        case 0:
                        case 2:
                        case 4:
                        case 6:
                            view.setBackgroundResource(R.drawable.ic_custom_answer_true);
                            break;
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                            view.setBackgroundResource(R.drawable.ic_custom_answer);
                            break;
                    }
                }else {
                    switch (count) {
                        case 0:
                        case 2:
                        case 4:
                        case 6:
                            view.setBackgroundResource(R.drawable.ic_custom_answer_wait);
                            break;
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                            view.setBackgroundResource(R.drawable.ic_custom_answer);
                            break;
                    }
                }
                count++;
                if (count == 8)
                    return;
                handler.postDelayed(this,150);
            }
        }.run();
    }
}