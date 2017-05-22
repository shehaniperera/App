package com.example.shehani.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class SecondMain extends AppCompatActivity {

    public FrameLayout healthTip1Frame;
    public FrameLayout healthTip2Frame;
    public FrameLayout healthTip3Frame;
    public FrameLayout healthTip4Frame;
    public FrameLayout healthTip5Frame;


    public void healthTip1(){
        healthTip1Frame = (FrameLayout) findViewById(R.id.frameLayoutTip1);
        healthTip1Frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this,HealthTip1.class);
                startActivity(intent);
            }
        });
    }

    public void healthTip2(){
        healthTip2Frame = (FrameLayout) findViewById(R.id.frameLayoutTip2);
        healthTip2Frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this,HealthTip2.class);
                startActivity(intent);
            }
        });
    }

    public void healthTip3(){
        healthTip3Frame = (FrameLayout) findViewById(R.id.frameLayoutTip3);
        healthTip3Frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this,HealthTip3.class);
                startActivity(intent);
            }
        });
    }

    public void healthTip4(){
        healthTip4Frame = (FrameLayout) findViewById(R.id.frameLayoutTip4);
        healthTip4Frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this,HealthTip4.class);
                startActivity(intent);
            }
        });
    }

    public void healthTip5(){
        healthTip5Frame = (FrameLayout) findViewById(R.id.frameLayoutTip5);
        healthTip5Frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this,HealthTip5.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);

        healthTip1();
        healthTip2();
        healthTip3();
        healthTip4();
        healthTip5();


    }
}
