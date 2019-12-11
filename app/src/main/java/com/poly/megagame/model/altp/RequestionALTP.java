package com.poly.megagame.model.altp;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestionALTP implements Serializable {

    private String requestion;
    private ArrayList<String> arrayAnswer;
    private int answer;

    public String getRequestion() {
        return requestion;
    }

    public void setRequestion(String requestion) {
        this.requestion = requestion;
    }

    public ArrayList<String> getArrayAnswer() {
        return arrayAnswer;
    }

    public void setArrayAnswer(ArrayList<String> arrayAnswer) {
        this.arrayAnswer = arrayAnswer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
