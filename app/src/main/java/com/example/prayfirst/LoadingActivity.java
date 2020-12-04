package com.example.prayfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBarHorizontal;
    private  int progerStatus = 0;
    private  Handler handler = new Handler();

//    Handler handler;
//    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        textView = findViewById(R.id.txtwait);
        progressBarHorizontal = (ProgressBar)findViewById(R.id.progressbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progerStatus < 100){
                    progerStatus +=1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBarHorizontal.setProgress(progerStatus);
                            textView.setText("Wait...");
                            if (progerStatus==100){
                                textView.setText("Finished");
                                //Intent
                                Intent LogtoHome = new Intent(LoadingActivity.this, PrayerActivity.class);
                                startActivity(LogtoHome);
                            }
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        progressBar = findViewById(R.id.progressbar);
//
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(progressBar.getProgress() < 100) {
//                    progressBar.setProgress(progress);
//                    progress++;
//                    handler.postDelayed(this,100);
//                    textView.setText("Wait...");
//                }else textView.setText("Finished");
//                Intent LogtoHome = new Intent(LoadingActivity.this, PrayerActivity.class);
//                startActivity(LogtoHome);
//            }
//        },100);

    }
}