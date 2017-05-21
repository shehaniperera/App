package com.example.shehani.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class UserDetails extends AppCompatActivity {

    EditText name,phone,age;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = (EditText)findViewById(R.id.uname);
        phone = (EditText)findViewById(R.id.no);
        age =  (EditText)findViewById(R.id.age);

        mAuth = FirebaseAuth.getInstance();


    }
}
