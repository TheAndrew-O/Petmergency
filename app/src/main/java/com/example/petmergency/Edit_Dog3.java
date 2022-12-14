package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Edit_Dog3 extends AppCompatActivity {

    private Button cancel_edit, delete_acc, enter_edit;
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView prof_img;

    private AnimationDrawable anim_background;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog3);

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

        cancel_edit = findViewById(R.id.cancel_edit);
        cancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPetProfile();
            }
        });

        enter_edit = findViewById(R.id.submit_profile);
        enter_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPetProfile();
            }
        });

        delete_acc = findViewById(R.id.delete_account);
        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Dog3.this, ProfileSelector.class);
                startActivity(intent);
            }
        });

        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
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

    public void openPetProfile(){
        Intent intent = new Intent(Edit_Dog3.this, Dog3.class);
        startActivity(intent);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(Edit_Dog3.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Intent intent = new Intent(Edit_Dog3.this, appointments.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(Edit_Dog3.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(Edit_Dog3.this, Dog3.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(Edit_Dog3.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(Edit_Dog3.this, Dog3Emergency.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
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