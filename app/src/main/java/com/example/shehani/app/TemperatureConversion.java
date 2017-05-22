package com.example.shehani.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureConversion extends AppCompatActivity {

    String temp ,f,c;
    RadioButton cel , fah;
    Boolean isCelciusChecked ,isFahrenheitChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conversion);

        temp = this.getIntent().getStringExtra("Temp");
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

        cel = (RadioButton)findViewById(R.id.radioButtonCel);
        fah = (RadioButton)findViewById(R.id.radioButtonFah);


        if(temp.contains("C")){
            cel.setEnabled(false);
            fah.setEnabled(true);
        }

        else if(temp.contains("F")){
            fah.setEnabled(false);
            cel.setEnabled(true);
        }


    }



    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(this, sensorDetails.class);
        intent.putExtra("Fah",f);
        intent.putExtra("Cel",c);
        intent.putExtra("F",isFahrenheitChecked);
        getIntent().putExtra("C",isCelciusChecked);
        startActivity(intent);

    }


 public void onRadioButtonClicked(View view){

     boolean isChecked = ((RadioButton)view).isChecked();

     switch(view.getId()){

         case R.id.radioButtonCel:
             if(isChecked){
                 String str = temp.replaceAll("\\D+","");
                 int fa = Integer.parseInt(str);
                 double fah = (fa - 32)* 5/9;
                 c = String.valueOf(fah);
                 Toast.makeText(getApplicationContext(), c+ " - Celcius", Toast.LENGTH_LONG).show();
                 isCelciusChecked = true;
                 break;


             }
         case R.id.radioButtonFah:
             if(isChecked ){
                 String str = temp.replaceAll("\\D+","");
                 int c = Integer.parseInt(str);
                 double fah = c* 9/5 + 32;
                 f = String.valueOf(fah);
                 Toast.makeText(getApplicationContext(), f+ " - Fahrenheit", Toast.LENGTH_LONG).show();
                 isFahrenheitChecked = true;
                 break;
             }
     }
 }




}
