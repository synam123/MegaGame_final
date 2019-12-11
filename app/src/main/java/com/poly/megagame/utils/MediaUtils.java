package com.poly.megagame.utils;

import android.media.MediaPlayer;

import com.poly.megagame.base.BaseApplication;
import com.poly.megagame.callback.PlayMediaComplete;

public class MediaUtils {
    private static MediaUtils mediaUtils;
    private static MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer1;

    public static MediaUtils getInstance(){
        if (mediaUtils == null){
            mediaUtils = new MediaUtils();
        }
        return mediaUtils;
    }

    public void play(int source, final PlayMediaComplete playMediaComplete){
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(BaseApplication.getContext(), source);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                    if (playMediaComplete != null){
                        playMediaComplete.onComplete();
                    }
                }
            });
        }else {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            play(source,playMediaComplete);
        }
    }

    public void plays(int source){
       mediaPlayer1 = MediaPlayer.create(BaseApplication.getContext(), source);
        mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer1.release();
                mediaPlayer1 = null;
            }
        });
    }

    public void stop(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
