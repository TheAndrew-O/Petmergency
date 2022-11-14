package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CreateProfile extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView prof_img;
    private ImageButton exit;
    private Button create_prof_but;
    private AnimationDrawable anim_background;
//    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_create_profile);

        linearLayout = (LinearLayout)findViewById(R.id.create_profile_card);
        anim_background = (AnimationDrawable)linearLayout.getBackground();
        anim_background.setEnterFadeDuration(5000);
        anim_background.setExitFadeDuration(2000);

        prof_img = findViewById(R.id.profile_pic);
        Button select_pic = findViewById(R.id.select_profile_pic);
        select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,GALLERY_REQUEST_CODE);
            }
        });

        create_prof_but = findViewById(R.id.submit_profile);
        create_prof_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPetProfile();
            }
        });

        exit = findViewById(R.id.go_home);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActicity();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE){
                prof_img.setImageURI(data.getData());
            }
        }
    }

    public void openMainActicity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openPetProfile(){
        Intent intent = new Intent(this, Dog3.class);
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