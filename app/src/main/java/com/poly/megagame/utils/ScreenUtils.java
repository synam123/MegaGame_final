package com.poly.megagame.utils;

import android.view.Display;
import android.view.WindowManager;

import com.poly.megagame.base.BaseApplication;

import static android.content.Context.WINDOW_SERVICE;

public class ScreenUtils {

    private static ScreenUtils screenUtils;

    public static ScreenUtils getInstance(){
        if (screenUtils == null){
            screenUtils = new ScreenUtils();
        }

        return screenUtils;
    }

    public int getWithd(){
        Display display = ((WindowManager) BaseApplication.getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        return display.getWidth();
    }

    public int getHeight(){
        Display display = ((WindowManager) BaseApplication.getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        return display.getHeight();
    }
}
