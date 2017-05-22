package com.example.shehani.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenubarScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String temp,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubar_screen);

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
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
