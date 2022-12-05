package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Add_Appoitment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    int day, month, year, hour, minute;
    int _day, _month, _year, _hour, _minute;
    private Button get_date, enter;
    private TextView textView;
    private EditText location, desc;
    String glob_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appoitment);

        location = findViewById(R.id.add_loc);
        desc = findViewById(R.id.add_app_desc);
        textView = findViewById(R.id.show_date);
        get_date = findViewById(R.id.pick_date);
        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Appoitment.this, Add_Appoitment.this, year, month, day);
                datePickerDialog.show();
            }
        });

        ImageView menu_but = findViewById(R.id.menu_open);
        menu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view);
            }
        });

        enter = findViewById(R.id.submit_appoint);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppoitmentDBHelper myDB = new AppoitmentDBHelper(Add_Appoitment.this);
                String temp_hour = "" + _hour;
                String temp_min = "" + _minute;
                String temp_month = "" + _month;
                String temp_day = "" + _day;
                if(_minute < 10){
                    temp_min = "0" + _minute;
                    //textView.setText(_month + "/" + _day + "/" + _year + " - " + _hour + ":0" + _minute);
                }
                if(_hour < 10){
                    temp_hour = "0" + _hour;
                }
                if(_day < 10){
                    temp_day = "0" + _day;
                }
                if(_month < 10){
                    temp_month = "0" + _month;
                }
                String date = ("" + _year + "-" + temp_month + "-" + temp_day + " " + temp_hour + ":" + temp_min + ":00");
                glob_date = date;
                myDB.addAppoitment(location.getText().toString().trim(), desc.getText().toString().trim(), date);
                confirmCalendar();
                //Intent intent = new Intent(Add_Appoitment.this, appointments.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        _year = year;
        _day = dayOfMonth;
        _month = month;
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Add_Appoitment.this, Add_Appoitment.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        _hour = hour;
        _minute = minute;
        String temp_hour = "" + _hour;
        String temp_min = "" + _minute;
        _month += 1;
        if(_minute < 10){
            temp_min = "0" + _minute;
            //textView.setText(_month + "/" + _day + "/" + _year + " - " + _hour + ":0" + _minute);
        }
        if(_hour < 10){
            temp_hour = "0" + _hour;
        }
        textView.setText(_month + "/" + _day + "/" + _year + " - " + temp_hour + ":" + temp_min);
    }

    private void openMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(Add_Appoitment.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.nav_appoitments){
                    Intent intent = new Intent(Add_Appoitment.this, appointments.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_notes){
                    Intent intent = new Intent(Add_Appoitment.this,Notes.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_profile){
                    Intent intent = new Intent(Add_Appoitment.this, Dog3.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_med){
                    Intent intent = new Intent(Add_Appoitment.this, medication.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.nav_emergency){
                    Intent intent = new Intent(Add_Appoitment.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    void confirmCalendar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Google Calendar");
        builder.setMessage("Add appoitment  to calendar app?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(_year, _month-1, _day, _hour, _minute);
                Calendar endTime = Calendar.getInstance();
                endTime.set(_year, _month-1, _day, _hour + 1, _minute);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, desc.getText().toString().trim());
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString().trim());
                startActivity(calendarIntent);
//                Intent intent = new Intent(Add_Appoitment.this, appointments.class);
//                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Add_Appoitment.this, appointments.class);
                startActivity(intent);
            }
        });
        builder.create().show();
    }
    @Override
    public void onStop() {
        super.onStop();
        Intent intent = new Intent(Add_Appoitment.this, appointments.class);
        startActivity(intent);
    }
}