package com.example.shehani.app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class sensorDetails extends AppCompatActivity {


TextView Temp,Humidity,Gas,Co;
DatabaseReference root = FirebaseDatabase.getInstance().getReference();
int hour = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);




        Button btn1 = (Button) findViewById(R.id.btnTemp);
        Button btn2 = (Button) findViewById(R.id.btnHumidity);
        Button btn3 = (Button) findViewById(R.id.btngas);
        Button btn4 = (Button) findViewById(R.id.btnco);
        Button menu = (Button) findViewById(R.id.btnmenu);
        Button save = (Button) findViewById(R.id.btnsave);

        Temp = (TextView) findViewById(R.id.textView3);
        Humidity = (TextView) findViewById(R.id.textView4);
        Gas = (TextView) findViewById(R.id.textView5);
        Co = (TextView) findViewById(R.id.textView6);

        Intent i = this.getIntent();
        String g = i.getStringExtra("gas");
        String t = i.getStringExtra("temp");
        String h = i.getStringExtra("humi");
        String c = i.getStringExtra("co");


        String extra = getIntent().getStringExtra("Fah");
        String ex = getIntent().getStringExtra("Cel");
        Boolean isCelciusSelected = getIntent().getBooleanExtra("C",true);
        Boolean isFahrenheitChecked = getIntent().getBooleanExtra("F",true);

        if(extra != null && isFahrenheitChecked){

            Temp.setText("Temp - "+extra+ (char) 0x00B0+"F");

        }

        else if(ex != null && isCelciusSelected){
            Temp.setText("Temp- "+t+ (char) 0x00B0+"C");
        }

        else {
            Temp.setText("Temp- "+t+ (char) 0x00B0+"C");
        }

        Humidity.setText("Humidity- 91"+"%");
        Gas.setText("Gas - "+g);
        Co.setText("CO - "+c);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(sensorDetails.this, TemperaturePage.class);
                startActivity(int1);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(sensorDetails.this, HumidityPage.class);
                startActivity(int2);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(sensorDetails.this, GasPage.class);
                startActivity(int3);
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(sensorDetails.this, CoPage.class);
                startActivity(int4);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour++;
                String h = String.valueOf(hour);
                DatabaseReference childRef = root.child("Hour: "+h);
                Map<String,Object> map = new HashMap<String, Object>();

                map.put("Temperature",Temp.getText().toString());
                map.put("Humidity",Humidity.getText().toString());
                map.put("Gas",Gas .getText().toString());
                map.put("CO",Co.getText().toString());
                childRef.setValue(map);

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(sensorDetails.this, MenubarScreen.class);
                int1.putExtra("Temp",Temp.getText().toString());
                startActivity(int1);
            }
        });



    }





}



