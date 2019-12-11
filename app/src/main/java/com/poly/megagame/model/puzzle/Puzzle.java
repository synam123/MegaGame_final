package com.poly.megagame.model.puzzle;

import android.graphics.Bitmap;

public class Puzzle {
    private Bitmap      bitmap;
    private String      index;

    public Puzzle(Bitmap bitmap, String index) {
        this.bitmap = bitmap;
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
