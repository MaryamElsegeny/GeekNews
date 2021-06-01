package com.example.geeknews.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
                    SharedPreferences Loginprefs =  getSharedPreferences("GeekNews", 0);
                    boolean userLoginStatus = Loginprefs.getBoolean("saveUserLogin", false);

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                    if(userLoginStatus == (true)) {
                        intent.putExtra("save user", "user login");
                        startActivity(intent);
                    }
                    else {
                        intent.putExtra("save user", "user not login");
                        startActivity(intent);
                    }
                }
                catch (Exception e){

                    Log.e( TAG, "onCreate: SLEEP ERROR",e);
                }
            }
        } ).start();

    }
}