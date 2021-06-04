package com.example.geeknews.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.geeknews.R;
import com.google.firebase.messaging.RemoteMessage;

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
                    Thread.sleep( 1300 );
                    SharedPreferences Loginprefs =  getSharedPreferences("GeekNews", 0);
                    boolean userLoginStatus = Loginprefs.getBoolean("saveUserLogin", false);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                    if (getIntent().getExtras() != null) {

                        for (String key : getIntent().getExtras().keySet()) {
                            String value = getIntent().getExtras().getString(key);

                            if (key.equals("Category")) {
                                if (userLoginStatus == (true)) {
                                    intent.putExtra("category notification" , value);
                                    intent.putExtra("navigate notification" , "navigate");
                                    startActivity(intent);
                                } else {
                                    intent.putExtra("save user", "user not login");
                                    startActivity(intent);
                                }
                            }

                        }
                    }
                    else {
                        if (userLoginStatus == (true)) {
                            intent.putExtra("save user", "user login");
                            startActivity(intent);
                        } else {
                            intent.putExtra("save user", "user not login");
                            startActivity(intent);
                        }
                    }





                }
                catch (Exception e){

                    Log.e( TAG, "onCreate: SLEEP ERROR",e);
                }
            }
        } ).start();

    }


}