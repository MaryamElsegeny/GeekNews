package com.example.geeknews.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.geeknews.R;

public class SplashActivity extends AppCompatActivity {

    boolean isLogin  ;
    private static final String TAG = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep( 1800 );
                    Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                    startActivity(intent);
                }
                catch (Exception e){

                    Log.e( TAG, "onCreate: SLEEP ERROR",e);
                }
            }
        } ).start();

    }
}