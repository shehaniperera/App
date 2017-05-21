package com.example.shehani.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class TemperatureConversion extends AppCompatActivity {

    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conversion);

        temp = this.getIntent().getStringExtra("Temp");
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
    }

 public void onRadioButtonClicked(View view){

     boolean isChecked = ((RadioButton)view).isChecked();

     switch(view.getId()){

         case R.id.radioButtonCel:
             if(isChecked){
                 Toast.makeText(getApplicationContext(), "Celcius", Toast.LENGTH_LONG).show();
                 break;
             }
         case R.id.radioButtonFah:
             if(isChecked){
                 String str = temp.replaceAll("\\D+","");
                 int c = Integer.parseInt(str);
                 double fah = c* 9/5 + 32;
                 String f = String.valueOf(fah);
                 Toast.makeText(getApplicationContext(), f+ " - Fahrenheit", Toast.LENGTH_LONG).show();
                 break;
             }
     }
 }

}
