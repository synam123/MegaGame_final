package com.poly.megagame.fragment_layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.poly.megagame.R;
import com.poly.megagame.utils.HighScoreUtils;
import com.poly.megagame.view.aitp.IntroduceALTPActivity;
import com.poly.megagame.view.math.MathGameActivity;
import com.poly.megagame.view.puzzle.PuzzleGameActivity;

import java.util.ArrayList;

public class Fragment_TrangChu extends Fragment implements View.OnClickListener{

    private View container;
    private TextView txtALTPPlayCount,txtALTPHighScore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        container = inflater.inflate(R.layout.fragment_trangchu, viewGroup, false);

        initView();
        addEvents();
        loadUI();

        return container;
    }

    private void loadUI() {
        ArrayList<Integer> arrayALTPHighScore = HighScoreUtils.getInstance().getALTPHighScore();
        txtALTPPlayCount.setText("Số lượt chơi: " + arrayALTPHighScore.size());
        if (arrayALTPHighScore.size() > 0) {
            txtALTPHighScore.setText("Điểm cao nhất: " + getPrice(arrayALTPHighScore.get(arrayALTPHighScore.size() - 1)));
        }
    }

    private void initView() {
        txtALTPPlayCount = container.findViewById(R.id.txtALTPPlayCount);
        txtALTPHighScore = container.findViewById(R.id.txtALTPHighScore);
    }

    private void addEvents() {
        container.findViewById(R.id.containerAiLaTrieuPhu).setOnClickListener(this);
        container.findViewById(R.id.containerXepHinh).setOnClickListener(this);
        container.findViewById(R.id.containerDoVuiHaiNao).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.containerAiLaTrieuPhu:
                startActivity(new Intent(getActivity(), IntroduceALTPActivity.class));
                break;
            case R.id.containerXepHinh:
                startActivity(new Intent(getActivity(), PuzzleGameActivity.class));
                break;
            case R.id.containerDoVuiHaiNao:
                startActivity(new Intent(getActivity(), MathGameActivity.class));
                break;
        }
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
}
