package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileSelector extends AppCompatActivity {
    private ImageButton pet1_but;
    private ImageButton pet2_but;
    private ImageButton add_pet_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_select_profile);

        pet1_but = findViewById(R.id.dog1);
        pet1_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDog1Activity();
            }
        });

        pet2_but = findViewById(R.id.dog2);
        pet2_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        add_pet_but = findViewById(R.id.add_pet);
        add_pet_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Profile Button",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openDog1Activity(){
        Intent intent = new Intent(this,Dog1.class);
        startActivity(intent);
    }
}