package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class bleedingInternal extends AppCompatActivity {
    private FloatingActionButton mainFab, callVetFab, homePageFab, locateVetFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleeding_internal);
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
                call.setData(Uri.parse("tel:8832201880"));

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
                Intent intent2 = new Intent(bleedingInternal.this, ProfileSelector.class);
                startActivity(intent2);
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

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(bleedingInternal.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Intent intent = new Intent(bleedingInternal.this, ProfileSelector.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(bleedingInternal.this,ProfileSelector.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(bleedingInternal.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(bleedingInternal.this, ProfileSelector.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(bleedingInternal.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }

}