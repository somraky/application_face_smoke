package com.example.testcallapi;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.testcallapi.api.ApiClient;
import com.example.testcallapi.api.ApiInterface;
import com.example.testcallapi.models.TimeNoti;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonElement;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseService extends FirebaseMessagingService {

    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        final String id = data.get("id");
        final String title = data.get("title");
        final String body = data.get("body");
        final String urlimg = data.get("image");
        final String clickaction = data.get("click_action");
        Log.d("NotiMessage", "id = "+id);
        Log.d("NotiMessage", "title = "+title);
        Log.d("NotiMessage", "body = "+body);
        Log.d("NotiMessage", "urlimg = "+urlimg);
        Log.d("NotiMessage", "clickaction = "+clickaction);

        Bitmap bitmap = getBitmapfromUrl(urlimg);
        showNotification(MyFirebaseService.this, id, title, body, bitmap, clickaction);
    }

    private void showNotification(Context context,
                                  String id,
                                  String title,
                                  String body,
                                  Bitmap bitmap,
                                  String click_action){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = new Random().nextInt(60000);
        String channelId = "edmtdev-911";
        String channelName = "EDMTDev";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent;
        if(click_action.equals("MAINACTIVITY")){ //onclick activity
            intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if(click_action.equals("PERSONACTIVITY")){ //onclick activity
            intent = new Intent(this, PersonActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else if(click_action.equals("CAMERAACTIVITY")){ //onclick activity
            intent = new Intent(this, CameraActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else {
            intent = new Intent();
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .setOnlyAlertOnce(true)
                .setAutoCancel(true);

        if(intent != null)
        {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPeningIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(resultPeningIntent);
        }

        notificationManager.notify(notificationId, notificationBuilder.build());
        Call<ResponseBody> call  = apiInterface.sentUpdateTime(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("CallStatus", "Success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("CallStatus", "Fail");
            }
        });
    }

    public Bitmap getBitmapfromUrl(String imgurl){
        try {
            URL url = new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}