package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
//import android.widget.Toast;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Button choke_but;
    private Button bleed_but;
    private Button call_but;
    private ImageButton profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        choke_but = findViewById(R.id.button_choke);
        choke_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openChokeActivity();
            }
        });

        bleed_but = findViewById(R.id.button_bleed);
        bleed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openBleedActivity();
            }

        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
    }

    public void openChokeActivity(){
        Intent intent = new Intent(this, choking.class);
        startActivity(intent);
    }

    public void openBleedActivity(){
        Intent intent = new Intent(this, Bleeding.class);
        startActivity(intent);
    }

    public void openProfileActivity(){
        Intent intent2 = new Intent(this, ProfileActivity.class);
        startActivity(intent2);
    }
}