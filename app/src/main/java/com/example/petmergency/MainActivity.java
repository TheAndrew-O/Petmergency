package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Button choke_but;
    private ImageButton profile_button;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });
    }

    public void openChokeActivity(){
       Intent intent = new Intent(this, choking.class);
       startActivity(intent);
    }

    public void openProfileActivity(){
        Intent intent2 = new Intent(this, ProfileActivity.class);
        startActivity(intent2);
    }
}