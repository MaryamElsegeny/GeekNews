package com.example.geeknews.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.activites.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import static android.content.ContentValues.TAG;

public class FcmMsgService extends FirebaseMessagingService {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

//        String title=remoteMessage.getNotification().getTitle();
//        String massage=remoteMessage.getNotification().getBody();

        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");


            Map<String, String> params = remoteMessage.getData();

          String category = params.get("Category");
          Log.d(TAG, "onMessageReceived: "+category);

//        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("notification", category);
        intent.putExtra("notfy" , "toHome" );
//        startActivity(intent);



        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setSmallIcon(R.drawable.logo);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,builder.build());
        super.onMessageReceived(remoteMessage);
    }
}
