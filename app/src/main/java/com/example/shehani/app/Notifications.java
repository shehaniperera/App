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

    String temp , hum , gas, co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        temp = this.getIntent().getStringExtra("Temp");
        hum = this.getIntent().getStringExtra("Hum");
        gas = this.getIntent().getStringExtra("Gas");
        co =  this.getIntent().getStringExtra("Co");
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

        Switch switchTemp = (Switch)findViewById(R.id.switchtem);
        Switch switchHum = (Switch)findViewById(R.id.switchhum);
        Switch switchGas = (Switch)findViewById(R.id.switchgas);
        Switch switchCo = (Switch)findViewById(R.id.switchco);
        Switch switchSms = (Switch)findViewById(R.id.switchSms);

        switchTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Notifications Checked", Toast.LENGTH_LONG).show();

                    getTemperatureNotifications();

                }
            }
        });

        switchHum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Notifications Checked", Toast.LENGTH_LONG).show();


                    getHumidityNotifications();
                }
            }
        });

        switchGas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Notifications Checked", Toast.LENGTH_LONG).show();


                    getGasNotifications();
                }
            }
        });

        switchCo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Notifications Checked", Toast.LENGTH_LONG).show();


                    getCoNotifications();
                }
            }
        });
    }


    public void getTemperatureNotifications(){

        String str = temp.replaceAll("\\D+","");
        int empNo = Integer.parseInt(str);


        if(empNo > -1 ){ //48
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.t))
                    .setContentTitle("Temperature Warning!!")
                    .setContentText("Temperature at a High Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }


        if(empNo < 22 ){
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.t))
                    .setContentTitle("Temperature Warning!!")
                    .setContentText("Temperature at a Low Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }
    }


    public void getHumidityNotifications(){

        String strh = hum.replaceAll("\\D+","");
        int hu = Integer.parseInt(strh);




        if(hu > -88 ){ //95
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.dew))
                    .setContentTitle("Humidity Warning!!")
                    .setContentText("Humidity at a High Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

        if(hu < 70 ){
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.dew))
                    .setContentTitle("Humidity Warning!!")
                    .setContentText("Humidity at a Low Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

    }

    public void getGasNotifications(){

        String strg = gas.replaceAll("\\D+","");
        int g = Integer.parseInt(strg);




        if(g > 350 ){ //95
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.gas))
                    .setContentTitle("Gas Warning!!")
                    .setContentText("Gas at a High Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

        if(g < 200 ){
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.gas))
                    .setContentTitle("Gas Warning!!")
                    .setContentText("Gas at a Low Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

    }


    public void getCoNotifications(){

        String strc = co.replaceAll("\\D+","");
        int c = Integer.parseInt(strc);




        if(c > 600 ){ //95
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.c))
                    .setContentTitle("CO Warning!!")
                    .setContentText("CO at a High Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

        if(c < 350 ){
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Notifications.this)
                    .setSmallIcon(R.drawable.e)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.c))
                    .setContentTitle("CO Warning!!")
                    .setContentText("CO at a Low Level");
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

            NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(Notifications.this);
            notificationmanager.notify(1,notificationBuilder.build());
        }

    }

}
