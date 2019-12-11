package com.poly.megagame.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoreUtils {

    private static HighScoreUtils highScoreUtils;

    public static HighScoreUtils getInstance(){
        if (highScoreUtils == null){
            highScoreUtils = new HighScoreUtils();
        }
        return highScoreUtils;
    }

    public void setALTPHighScore(int requestionCount){
        ArrayList<Integer> arrayALTPScore = new Gson().fromJson(SharedPresUtils.getInstance().get("ALTP",String.class),new TypeToken<ArrayList<Integer>>(){}.getType());
        if (arrayALTPScore == null){
            arrayALTPScore = new ArrayList<>();
        }

        arrayALTPScore.add(requestionCount);
        Collections.sort(arrayALTPScore);
        SharedPresUtils.getInstance().put("ALTP",new Gson().toJson(arrayALTPScore));
    }

    public ArrayList<Integer> getALTPHighScore(){
        ArrayList<Integer> arrayALTPScore = new Gson().fromJson(SharedPresUtils.getInstance().get("ALTP",String.class),new TypeToken<ArrayList<Integer>>(){}.getType());
        if (arrayALTPScore == null){
            arrayALTPScore = new ArrayList<>();
        }

        return arrayALTPScore;
    }
}
