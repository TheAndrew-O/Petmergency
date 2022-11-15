package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class ProfileSelector2 extends AppCompatActivity {
    private ImageButton pet1_but;
    private ImageButton pet2_but, pet3_but;
    private ImageButton add_pet_but;
    private AnimationDrawable anim_background;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_profile_selector2);

        relativeLayout = (RelativeLayout)findViewById(R.id.select_profile_background);
        anim_background = (AnimationDrawable)relativeLayout.getBackground();
        anim_background.setEnterFadeDuration(5000);
        anim_background.setExitFadeDuration(2000);

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

        pet3_but = findViewById(R.id.dog3);
        pet3_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDog3Activity();
            }
        });

        add_pet_but = findViewById(R.id.add_pet);
        add_pet_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewPetActivity();
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

    public void openDog3Activity(){
        Intent intent = new Intent(this,Dog3.class);
        startActivity(intent);
    }

    public void openNewPetActivity(){
        Intent intent = new Intent(this, CreateProfile.class);
        startActivity(intent);
    }

    protected void onResume(){
        super.onResume();
        if(anim_background != null && !anim_background.isRunning()){
            anim_background.start();
        }
    }
    protected void onPause(){
        super.onPause();
        if(anim_background != null && !anim_background.isRunning()){
            anim_background.start();
        }
    }
}