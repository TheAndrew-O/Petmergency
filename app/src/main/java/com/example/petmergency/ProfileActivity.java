package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView prof_img;
    private Button open_appointments;
    private Button open_med;
    private Button open_notes;
    private ImageButton swap_but;
    String calendar = "com.google.android.calendar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_profile);

        prof_img = findViewById(R.id.imgGallery);
        swap_but = findViewById(R.id.switch_profile);
        swap_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSwapProfileActivity();
            }
        });

        open_appointments = findViewById(R.id.button_appointment);
        open_appointments.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openAppointmentActivity();
            }

        });

        open_med = findViewById(R.id.button_medications);
        open_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMedActivity();
            }
        });

        open_notes = findViewById(R.id.button_notes);
        open_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotesActivity();
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

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                prof_img.setImageURI(data.getData());
            }
        }
    }

    public void openMedActivity() {
        Intent intent = new Intent(this, medication.class);
        startActivity(intent);
    }

    public void openNotesActivity() {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void openAppointmentActivity(){
        Intent intent = new Intent(this, appointments.class);
        startActivity(intent);
    }

    public void openSwapProfileActivity() {
        Intent intent = new Intent(this, ProfileSelector.class);
        startActivity(intent);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(ProfileActivity.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(ProfileActivity.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(ProfileActivity.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}