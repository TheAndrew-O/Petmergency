package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class BleedingLocation extends AppCompatActivity {

    private Button torso_but, head_but, internal_but;
    ImageView menu_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleeding_location);

        torso_but = findViewById(R.id.torso_bleeding);
        torso_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingTorso.class);
                startActivity(intent);

            }
        });

        head_but = findViewById(R.id.head_bleeding);
        head_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingHead.class);
                startActivity(intent);

            }
        });

        internal_but = findViewById(R.id.internal_bleeding);
        internal_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingInternal.class);
                startActivity(intent);

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

    private void openMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(BleedingLocation.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_appoitments) {
                    Toast.makeText(BleedingLocation.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.nav_notes) {
                    Intent intent = new Intent(BleedingLocation.this, Notes.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_profile) {
                    Intent intent = new Intent(BleedingLocation.this, Dog3.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_med) {
                    Intent intent = new Intent(BleedingLocation.this, medication.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_emergency) {
                    Intent intent = new Intent(BleedingLocation.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}