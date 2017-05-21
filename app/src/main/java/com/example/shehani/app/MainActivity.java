package com.example.shehani.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edemail;
    EditText edpassword;
    TextView signIn;
    Button btnSignUp;
    private ProgressDialog progressdialog;
    private FirebaseAuth auth;
    private BluetoothSocket btSocket;
    InputStream inputStream;
    BluetoothDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        progressdialog = new ProgressDialog(this);
        edemail = (EditText) findViewById(R.id.editTextEmail);
        edpassword = (EditText) findViewById(R.id.editTextPassword);
        signIn = (TextView) findViewById(R.id.txtsignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignup);

        btnSignUp.setOnClickListener(this);
        signIn.setOnClickListener(this);



    }






    private void registerUser() {

        String email = edemail.getText().toString().trim();
        String password = edpassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            showMessage("Error", "Please enter your Email Address to Sign In");
            return; // stop the function from executing it further
        }

        if (TextUtils.isEmpty(password)) {
            showMessage("Error", "Please enter your Password to Sign In");
            return;  // stop the function from executing it further
        }

        progressdialog.setMessage("Registering User......");
        progressdialog.show();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User is Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "User is  Not Registered , Please Try again!!!! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {


        if (view == btnSignUp) {
            registerUser();
        }

        if (view == signIn) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}

