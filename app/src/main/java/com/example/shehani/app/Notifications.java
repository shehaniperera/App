package com.example.shehani.app;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Notifications extends AppCompatActivity {

    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        temp = this.getIntent().getStringExtra("Temp");
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

        Switch switchNoti = (Switch)findViewById(R.id.switchNoti);
        Switch switchSms = (Switch)findViewById(R.id.switchSms);

        switchNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Notifications Checked", Toast.LENGTH_LONG).show();
                    String str = temp.replaceAll("\\D+","");
                    int empNo = Integer.parseInt(str);

                    if(empNo > -77 ){
                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                                .setSmallIcon(R.drawable.tem1)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                .setContentTitle("Temperature Warning!!")
                                .setContentText("Temperature at a High Level");
                        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                        NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
                        notificationmanager.notify(1,notificationBuilder.build());





                    }
                }
            }
        });
    }
}
