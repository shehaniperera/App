package com.example.shehani.app;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Main extends AppCompatActivity {

    private BluetoothSocket btSocket;
    InputStream inputStream;
    BluetoothDevice device;
    private FirebaseAnalytics mFirebaseAnalytics;

    String gas,temp,humidity,co;
    private GestureDetectorCompat gestureObject;
    //data is transfered serially
    final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // uniquely identify info defines a service provided by the bluetooth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gestureObject = new GestureDetectorCompat(this , new LearnGesture());

//        Toast.makeText(getApplicationContext(), "Test1", Toast.LENGTH_LONG).show();

        if(connectBluetooth()){ //if Bluettoth connectivity is available
          getData(); // get data from the arduino to the app
       }

        else{
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }


    public boolean connectBluetooth() {
        Toast.makeText(this,"ll",Toast.LENGTH_LONG).show();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); //device that transmits wireless data signals
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice iterator : bondedDevices) {
            if (iterator.getAddress().equals("98:D3:32:30:A0:65")) { // mac address of the bluetooth sensor
                //("00:21:13:00:3D:68")
                device = iterator;
                break;
            }
        }
        boolean connected = true; // boolean value true if bluetooth connectivity is available
        Toast.makeText(getApplicationContext(), "Came2", Toast.LENGTH_LONG).show();
        try {
            //socket is used to receive connection
            btSocket = device.createRfcommSocketToServiceRecord(PORT_UUID); // connecting to a known device
            btSocket.connect();
        } catch (Exception e) {
            e.printStackTrace();
            connected = false;
        }
        if (connected) {
            try {
                inputStream = btSocket.getInputStream(); // input stream to get the data
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return connected;
    }

    void getData() {
        final android.os.Handler handler = new android.os.Handler();
//        buffer = new byte[1024];
        Toast.makeText(getApplicationContext(), "Came3", Toast.LENGTH_LONG).show();
        final Thread thread = new Thread(new Runnable() {
            public void run() {
//                Toast.makeText(getApplicationContext(), "Why", Toast.LENGTH_LONG).show();
                while (true) {
//                    Toast.makeText(getApplicationContext(), "VVVV", Toast.LENGTH_LONG).show();
                    try {
                        int byteCount = inputStream.available();
//                        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                        if (byteCount > 0) {
//                            Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
//                            byte[] rawBytes = new byte[byteCount];
//                            int bytes = inputStream.read(rawBytes);
//                            final String string = new String(rawBytes, 0, bytes);
                            byte[] rawBytes = new byte[byteCount];
                            inputStream.read(rawBytes);
                            final String string = new String(rawBytes, "UTF-8");

                            try {
//                                Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
//                                final String[] arr = string.split(";");
//                                final String[] data = arr[0].split(",");
////
//                                final int ort = Integer.parseInt(data[0]);
//                                final int nmb = Integer.parseInt(data[1]);

                                handler.post(new Runnable() {
                                    public void run() {
                                        while (true) {
                                       Toast.makeText(getApplicationContext(),string, Toast.LENGTH_LONG).show();


                                        gas =string.split(",")[0].trim();
                                        temp = string.split(",")[1].trim();
                                        humidity = string.split(",")[2].trim();
                                        co = string.split(",")[3].trim();

                                            Toast.makeText(getApplicationContext(),"Gas"+gas, Toast.LENGTH_LONG).show();
                                            Toast.makeText(getApplicationContext(),"Temp"+temp, Toast.LENGTH_LONG).show();
                                            Toast.makeText(getApplicationContext(),"Humidity"+humidity, Toast.LENGTH_LONG).show();
                                            Toast.makeText(getApplicationContext(),"CO"+co, Toast.LENGTH_LONG).show();


                                            break;
                                        }
                                    }
                                });
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Bluetooth Connectivity Error!!!!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception ex) {
                        Thread.currentThread().interrupt();
                    }
                    SystemClock.sleep(800);
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent event1 , MotionEvent event2 , float velosityX , float velosityY){

            if (event2.getX() > event1.getX() ){

                Intent intent = new Intent(Main.this , SignIn.class);
                intent.putExtra("gas",gas);
                intent.putExtra("temp",temp);
                intent.putExtra("humidity",humidity);
                intent.putExtra("co",co);
                Toast.makeText(getApplicationContext(), gas+"Main intent", Toast.LENGTH_LONG).show();
                finish();
                startActivity(intent);
            }

            else if (event2.getX() < event1.getX() ){

            }

            return true;
        }
    }



}


