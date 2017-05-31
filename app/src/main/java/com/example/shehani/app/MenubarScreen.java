package com.example.shehani.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenubarScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String temp,t,hum ,h,gas,g,co,c;
    String u , remove;
    private FirebaseAuth mAuth; //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubar_screen);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        temp = this.getIntent().getStringExtra("Temp");
        t = temp;
        hum = this.getIntent().getStringExtra("Humi");
        h = hum;
        gas = this.getIntent().getStringExtra("Gas");
        g = gas;
        co = this.getIntent().getStringExtra("Co");
        c = co;
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(MenubarScreen.this,Main.class));
                }
            }
        };
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.shehani.app.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "com.example.shehani.app.Home", Toast.LENGTH_SHORT).show();
            Intent home = new Intent(this,sensorDetails.class);
            startActivity(home);

        }else if (id == R.id.nav_Help) {
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
            Intent contact = new Intent(this,Contactus.class);
            startActivity(contact);

        }else if (id == R.id.item_temp){
            Toast.makeText(this, "Temperature Settings", Toast.LENGTH_SHORT).show();
            Intent temp = new Intent(this,TemperatureConversion.class);
            temp.putExtra("Temp",t);
            startActivity(temp);

        }else if (id == R.id.item_notify){
            Toast.makeText(this, "Notification Settings", Toast.LENGTH_SHORT).show();
            Intent notify = new Intent(this,Notifications.class);
            notify.putExtra("Temp",t);
            notify.putExtra("Hum",h);
            notify.putExtra("Gas",g);
            notify.putExtra("Co",c);
            startActivity(notify);

        }else if (id == R.id.nav_appInfo) {
            Toast.makeText(this, "App info", Toast.LENGTH_SHORT).show();
            Intent appInfo = new Intent(this,AppInfo.class);
            startActivity(appInfo);

        }
        else if(id == R.id.health_tips){
            Toast.makeText(this, "Health Tips", Toast.LENGTH_SHORT).show();
            Intent healthT = new Intent(this,SecondMain.class);
             startActivity(healthT);
        }


        else if(id == R.id.exit){
            Toast.makeText(this, "Logout from the system", Toast.LENGTH_SHORT).show();
            showMessage("Confirmation!!!!", "Are you sure that you want to logout from the System?");
        }


        else if(id == R.id.appuser){
            Intent i = new Intent(MenubarScreen.this,CurrentUser.class);
            u = mAuth.getCurrentUser().getEmail();
            i.putExtra("current",u);
            Toast.makeText(this, "current u"+u, Toast.LENGTH_SHORT).show();
            startActivity(i);

        }

        else if(id == R.id.deleteuser){
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MenubarScreen.this, "User is Deleted"+user, Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(MenubarScreen.this, "User is not Deleted"+user, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }


        else if(id == R.id.updateuser){

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.updateEmail("user@example.com")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MenubarScreen.this, "User is Updated", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(MenubarScreen.this, "User is not Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // create object of AlertDialogBuilder which an inner class of AlertDialog
        builder.setCancelable(true); // sets the property that the dialog can be cancelled or not
        builder.setPositiveButton("Yes" ,null);
        builder.setNegativeButton("No",null);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // onclick on positive button
                mAuth.signOut();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // onclick on negative button
                dialogInterface.dismiss(); // close dialog
            }
        });
        builder.setTitle(title); //  set the title to be appear in the dialog
        builder.setMessage(Message); //  set the message to be appear in the dialog
        builder.show(); // show the alert
    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
