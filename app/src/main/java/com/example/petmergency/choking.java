package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class choking extends AppCompatActivity {
//    private Button open_choking_food;
//    private Button open_choking_object;
//    private Button open_choking_general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_choking);

//        open_choking_food = findViewById(R.id.button_choke_on_food);
//        open_choking_food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openChokingFood();
//            }
//
//        });
//        Toolbar toolbar = findViewById(R.id.toolbar2);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openProfileActivity();
//            }
//        });
    }
//
//    public void openChokingFood() {
//        Intent intent = new Intent(this,ChokingOnFood.class);
//        startActivity(intent);
//    }

    public void openProfileActivity(){
        Intent intent2 = new Intent(this, ProfileActivity.class);
        startActivity(intent2);
    }
}