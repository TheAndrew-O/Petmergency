package com.example.petmergency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class medication extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper medDB;
    ArrayList<String> id, name, dosage, date;
    private Button add_med;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_medication);

//        Toolbar toolbar = findViewById(R.id.toolbar_med);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //openMenu(view);
//            }
//        });
        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel channel = new NotificationChannel("WHAT","MEDICATIONS",NotificationManager.IMPORTANCE_DEFAULT);
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
            Intent intent = new Intent(getApplicationContext(),medication.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("TESTING")
                    .setContentText("PLEASSE ")
                    .setContentIntent(pendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "chat", pendingIntent)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1,notification);

        }
        recyclerView = findViewById(R.id.recycleView);
        add_med = findViewById(R.id.add_med);
        add_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medication.this, AddMedication.class);
                startActivity(intent);
            }
        });

        medDB = new MyDatabaseHelper(medication.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        dosage = new ArrayList<>();
        date = new ArrayList<>();

        initArrays();
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(medication.this, "Medication");
//        builder.setContentTitle("Title");
//        builder.setContentText("Body");
//        builder.setSmallIcon(R.drawable.ic_baseline_medical_services_24);
//        builder.setAutoCancel(true);
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(medication.this);
//        managerCompat.notify(1,builder.build());
        Intent intent = new Intent(getApplicationContext(),medication.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("dasdasd")
                .setContentText("what ")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat, "chat", pendingIntent)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notification);

        customAdapter = new CustomAdapter(medication.this, this, id, name, dosage, date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(medication.this));
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(medication.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Toast.makeText(medication.this, "You Clicked appoitments", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(medication.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(medication.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(medication.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(medication.this, MainActivity.class);
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
        Cursor cursor = medDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "NO MEDICINE AVAILABLE", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                dosage.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }
    }






}