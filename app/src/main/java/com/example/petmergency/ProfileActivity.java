package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
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
}