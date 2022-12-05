package com.example.petmergency;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dog3Emergency extends AppCompatActivity {

    private Button choke_but, heart_but, shock_but, poison_but, brokenBone_but, burn_but, heat_but, sting_but;
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

        setContentView(R.layout.activity_dog3_emergency);


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

        ImageView prof_but = findViewById(R.id.view_profile);
        prof_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
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

        heart_but = findViewById(R.id.button_cardiac_arrest);
        heart_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, HeartAttack.class);
                startActivity(intent);
            }
        });

        shock_but = findViewById(R.id.button_shock);
        shock_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, Shock.class);
                startActivity(intent);
            }
        });

        poison_but = findViewById(R.id.button_poison);
        poison_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, Poison.class);
                startActivity(intent);
            }
        });

        brokenBone_but = findViewById(R.id.button_broken_bone);
        brokenBone_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, BrokenBone.class);
                startActivity(intent);
            }
        });

        burn_but = findViewById(R.id.button_burn);
        burn_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, Burn.class);
                startActivity(intent);
            }
        });

        heat_but = findViewById(R.id.button_heat_stroke);
        heat_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, HeatStroke.class);
                startActivity(intent);
            }
        });

        sting_but = findViewById(R.id.button_bite);
        sting_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3Emergency.this, BugBite.class);
                startActivity(intent);
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
        Intent intent = new Intent(this, chokingLarge.class);
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
        Intent intent2 = new Intent(this, ProfileSelector2.class);
        startActivity(intent2);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(Dog3Emergency.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(Dog3Emergency.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(Dog3Emergency.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(Dog3Emergency.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(Dog3Emergency.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(Dog3Emergency.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }

}