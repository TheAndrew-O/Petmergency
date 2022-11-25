package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Shock extends AppCompatActivity {

    private Button bleed_but;
    FloatingActionButton call_emerg;
    ImageView menu_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shock);

        bleed_but = findViewById(R.id.stop_bleeding);
        bleed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shock.this, BleedingLocation.class);
                startActivity(intent);
            }
        });

        call_emerg = findViewById(R.id.emerg_call);
        call_emerg.setOnClickListener(new View.OnClickListener() {
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


    private void openMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(Shock.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_appoitments) {
                    Toast.makeText(Shock.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.nav_notes) {
                    Intent intent = new Intent(Shock.this, Notes.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_profile) {
                    Intent intent = new Intent(Shock.this, Dog3.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_med) {
                    Intent intent = new Intent(Shock.this, medication.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_emergency) {
                    Intent intent = new Intent(Shock.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}