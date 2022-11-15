package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class choking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choking);

//        ImageView menu_but = findViewById(R.id.menu_open);
//        menu_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openMenu(view);
//            }
//        });

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });

    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(choking.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(choking.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(choking.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(choking.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(choking.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(choking.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        popupMenu.show();
    }
}