package com.example.timerppo.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;

import com.example.timerppo.R;

import java.io.IOException;
import java.util.ArrayList;

public class TimerService extends Service {
    long currentTime;
    int index;
    ArrayList<Integer> timeSteps;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.final_sound);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        index = intent.getExtras().getInt("current_index");
        timeSteps = intent.getExtras().getIntegerArrayList("timeSteps");
        startTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    private void startTimer(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        countDownTimer =
                new CountDownTimer(currentTime, 1000) {
                    @Override
                    public void onTick(long l) {
                        currentTime = l;
                        Intent intent = new Intent("TIMER_ACTION");
                        intent.putExtra("currentTime", currentTime / 1000);
                        intent.putExtra("trainingIndexStep", index);
                        sendBroadcast(intent);
                    }

                    @Override
                    public void onFinish() {
                        index++;
                        if (index < timeSteps.size()) {
                            currentTime = timeSteps.get(index);
                            mediaPlayer.start();
                            startTimer();
                        }
                    }
                };
        countDownTimer.start();
    }

    public void changeIndex(int currentIndexStep){
        index = currentIndexStep;
        currentTime = timeSteps.get(index);
        countDownTimer.cancel();
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        startTimer();
    }

    public void timerPause(boolean isActive){
        if (isActive){
            if(countDownTimer != null){
                countDownTimer.cancel();
            }
        }
        else{
            startTimer();
        }
    }

    public class MyBinder extends Binder {
        public TimerService getService() {
            return TimerService.this;
        }
    }
}