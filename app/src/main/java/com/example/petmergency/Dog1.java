package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class Dog1 extends AppCompatActivity {
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView prof_img;
    private Button open_calendar;
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
        setContentView(R.layout.activity_dog1);

        prof_img = findViewById(R.id.imgGallery);
        swap_but = findViewById(R.id.switch_profile);
        swap_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSwapProfileActivity();
            }
        });

        open_calendar = findViewById(R.id.button_appointment);
        open_calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2022, 9, 23, 10, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2022, 9, 23, 11, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "Pet's Appoitment");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Location");
                startActivity(calendarIntent);
            }
        });

        open_med = findViewById(R.id.button_medications);
        open_med.setOnClickListener(new View.OnClickListener(){
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

        if(resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQUEST_CODE){
                prof_img.setImageURI(data.getData());
            }
        }
    }
    public void openMedActivity(){
        Intent intent = new Intent(this, medication.class);
        startActivity(intent);
    }

    public void openNotesActivity(){
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void openSwapProfileActivity(){
        Intent intent = new Intent(this, ProfileSelector.class);
        startActivity(intent);
    }
}