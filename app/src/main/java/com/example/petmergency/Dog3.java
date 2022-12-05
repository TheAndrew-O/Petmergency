package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class Dog3 extends AppCompatActivity {
    private final int GALLERY_REQUEST_CODE = 1000;
    ImageView prof_img;
    private Button open_calendar;
    private Button open_med;
    private Button open_notes;
    private ImageButton swap_but;
    private ImageButton edit_but;


    String calendar = "com.google.android.calendar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_dog3);

        prof_img = findViewById(R.id.imgGallery);
        swap_but = findViewById(R.id.switch_profile2);
        swap_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSwapProfileActivity();
            }
        });

        edit_but = findViewById(R.id.edit_profile);
        edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog3.this, Edit_Dog3.class);
                startActivity(intent);
            }
        });



        open_calendar = findViewById(R.id.button_appointment);
        open_calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openAppoitments();
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
        Intent intent = new Intent(this, ProfileSelector2.class);
        startActivity(intent);
    }

    public void openAppoitments(){
//        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2022, 9, 23, 10, 30);
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2022, 9, 23, 11, 30);
//        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
//        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
//        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Pet's Appoitment");
//        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Location");
//        startActivity(calendarIntent);
        Intent intent = new Intent(this, appointments.class);
        startActivity(intent);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(Dog3.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    openAppoitments();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(Dog3.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(Dog3.this, Dog3.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(Dog3.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(Dog3.this, Dog3Emergency.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        popupMenu.show();
    }
}