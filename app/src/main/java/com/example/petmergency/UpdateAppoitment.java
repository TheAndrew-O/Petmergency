package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateAppoitment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText location, desc;
    Button update_appoit, update_date, delete_but;
    int day, month, year, hour, minute;
    int _day, _month, _year, _hour, _minute;
    private TextView textView;
    String get_id, old_loc, old_desc, new_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_appoitment);

        location = findViewById(R.id.update_loc);
        desc = findViewById(R.id.update_desc);
        textView = findViewById(R.id.show_appupdate);
        update_date = findViewById(R.id.update_appdate);
        update_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateAppoitment.this, UpdateAppoitment.this, year, month, day);
                datePickerDialog.show();
            }
        });

        getAppData();
        update_appoit = findViewById(R.id.update_appoint);
        update_appoit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                new_date = date;
                AppoitmentDBHelper db = new AppoitmentDBHelper(UpdateAppoitment.this);
                db.updateData(get_id, location.getText().toString().trim(), desc.getText().toString().trim(), new_date);
                Intent intent = new Intent(UpdateAppoitment.this, appointments.class);
                startActivity(intent);
            }
        });

        delete_but = findViewById(R.id.delete_appoint);
        delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateAppoitment.this, UpdateAppoitment.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
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

    void getAppData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("location") && getIntent().hasExtra("desc") && getIntent().hasExtra("date")){
            get_id = getIntent().getStringExtra("id");
            old_loc = getIntent().getStringExtra("name");
            old_desc = getIntent().getStringExtra("dosage");
            new_date = getIntent().getStringExtra("date");
            location.setText(old_loc);
            desc.setText(old_desc);
            textView.setText(new_date);
        }
        else{
            Toast.makeText(this, "NO APPOINTMENT", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete appointment?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppoitmentDBHelper db = new AppoitmentDBHelper(UpdateAppoitment.this);
                db.deleteRow(get_id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(UpdateAppoitment.this, appointments.class);
            }
        });
        builder.create().show();
    }
}