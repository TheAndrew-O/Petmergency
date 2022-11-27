package com.example.petmergency;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
//import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button choke_but;
    private Button call_but;
    private Button bleed_but;
    private Button heart_but;
    private Button shock_but;
    private Button poison_but;
    private Button broken_bone_but;

    private ImageButton profile_button;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        heart_but = findViewById(R.id.button_heart_attack);
        heart_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openHeartAttackActivity();
            }
        });
        shock_but = findViewById(R.id.button_shock);
        shock_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openShockActivity();
            }
        });

        poison_but = findViewById(R.id.button_poison);
        poison_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openPoisonActivity();
            }
        });

        broken_bone_but = findViewById(R.id.button_broken_bone);
        broken_bone_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openBrokenBoneActivity();
            }
        });


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

    private void openHeartAttackActivity() {
        Intent intent = new Intent(this, heartAttack.class);
        startActivity(intent);
    }

    private void openShockActivity() {
        Intent intent = new Intent(this, shock.class);
        startActivity(intent);
    }

    private void openPoisonActivity() {
        Intent intent = new Intent(this, SelectPoison.class);
        startActivity(intent);
    }

    private void openBrokenBoneActivity() {
        Intent intent = new Intent(this, BrokenBone.class);
        startActivity(intent);
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