package com.example.petmergency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class appointments extends AppCompatActivity {
    private Button appoit_but;
    RecyclerView recyclerView;
    AppoitmentDBHelper appDB;
    ArrayList<String> id, location, desc, date;
    AppoitmentAdapter appoitmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_appointment);

        recyclerView = findViewById(R.id.recycle_appoit);
        appoit_but = findViewById(R.id.add_appoitment);
        appoit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appointments.this, Add_Appoitment.class);
                startActivity(intent);
            }
        });

        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });

        appDB = new AppoitmentDBHelper(appointments.this);
        id = new ArrayList<>();
        location = new ArrayList<>();
        desc = new ArrayList<>();
        date = new ArrayList<>();

        initArrays();
        appoitmentAdapter = new AppoitmentAdapter(appointments.this, this, id, location, desc, date);
        recyclerView.setAdapter(appoitmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(appointments.this));


    }


    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(appointments.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Intent intent = new Intent(appointments.this,appointments.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(appointments.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(appointments.this, Dog3.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(appointments.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(appointments.this, Dog3Emergency.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void initArrays(){
        Cursor cursor = appDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "NO APPOITMENTS AVAILABLE", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                location.add(cursor.getString(1));
                desc.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }
    }
}