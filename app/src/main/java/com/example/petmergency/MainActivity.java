package com.example.petmergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Toast;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button choke_but;
    private Button call_but;
    private Button bleed_but;
    private ImageButton profile_button;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        choke_but = findViewById(R.id.button_choke);
        choke_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openChokeActivity();
            }
        });

        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });

        call_but = findViewById(R.id.button_call);
        call_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:16127460300"));

//                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//                    return;
//                }
                startActivity(call);
            }
        });

        bleed_but = findViewById(R.id.button_bleed);
        bleed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBleedingActivity();
            }
        });
    }

    private void openMedActivity() {
        Intent intent = new Intent(this, medication.class);
        startActivity(intent);
    }

    private void openNotesActivity() {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void openChokeActivity(){
        Intent intent = new Intent(this, SelectDogSize.class);
        startActivity(intent);
    }

    public void openNotBreathingActivity(){
        Intent intent = new Intent(this, notBreathing.class);
        startActivity(intent);
    }

    public void openBleedingActivity(){
        Intent intent = new Intent(this, BleedingLocation.class);
        startActivity(intent);
    }

    public void openProfileActivity(){
        Intent intent2 = new Intent(this, ProfileActivity.class);
        startActivity(intent2);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(MainActivity.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(MainActivity.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(MainActivity.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }


}