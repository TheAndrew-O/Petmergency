package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BrokenBone extends AppCompatActivity {

    Button bleeding_but, splint_but, call_vet;
    ImageView menu_but;
    private FloatingActionButton mainFab, callVetFab, homePageFab, locateVetFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken_bone);

        bleeding_but = findViewById(R.id.stop_bleeding);
        bleeding_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrokenBone.this, BleedingLocation.class);
                startActivity(intent);
            }
        });

        splint_but = findViewById(R.id.dog_splint);
        splint_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrokenBone.this, LegSplint.class);
                startActivity(intent);
            }
        });

        mainFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        callVetFab = (FloatingActionButton) findViewById(R.id.call_vet_btn);
        homePageFab = (FloatingActionButton) findViewById(R.id.home_page_btn);
        locateVetFab = (FloatingActionButton) findViewById(R.id.locate_vet_btn);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open_anim);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close_anim);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward_anim);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward_anim);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        callVetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:16127460300"));

                startActivity(call);
            }
        });

        homePageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                openMainActivity();
            }
        });

        locateVetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Veterinary Hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        call_vet = findViewById(R.id.call_vet_bone);
        call_vet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:16127460300"));

                startActivity(call);
            }
        });

        menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });
    }

    private void animateFab() {
        if (isOpen) {
            mainFab.startAnimation(rotateForward);
            callVetFab.startAnimation(fabClose);
            homePageFab.startAnimation(fabClose);
            locateVetFab.startAnimation(fabClose);

            callVetFab.setClickable(false);
            homePageFab.setClickable(false);
            locateVetFab.setClickable(false);

            isOpen = false;
        } else {
            mainFab.startAnimation(rotateBackward);
            callVetFab.startAnimation(fabOpen);
            homePageFab.startAnimation(fabOpen);
            locateVetFab.startAnimation(fabOpen);

            callVetFab.setClickable(true);
            homePageFab.setClickable(true);
            locateVetFab.setClickable(true);

            isOpen = true;
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(BrokenBone.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_appoitments) {
                    Toast.makeText(BrokenBone.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.nav_notes) {
                    Intent intent = new Intent(BrokenBone.this, Notes.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_profile) {
                    Intent intent = new Intent(BrokenBone.this, Dog3.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_med) {
                    Intent intent = new Intent(BrokenBone.this, medication.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_emergency) {
                    Intent intent = new Intent(BrokenBone.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}