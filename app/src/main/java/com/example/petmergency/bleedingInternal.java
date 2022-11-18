package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class bleedingInternal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleeding_internal);
        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(bleedingInternal.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(bleedingInternal.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(bleedingInternal.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(bleedingInternal.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(bleedingInternal.this, medication.class);
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