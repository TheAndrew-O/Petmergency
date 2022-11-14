package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddMedication extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    int day, month, year, hour, minute;
    int _day, _month, _year, _hour, _minute;
    private Button get_date, enter;
    private TextView textView;
    private EditText name, dosage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        name = findViewById(R.id.add_med_name);
        dosage = findViewById(R.id.add_dosage);
        textView = findViewById(R.id.show_date);
        get_date = findViewById(R.id.pick_date);
        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMedication.this, AddMedication.this, year, month, day);
                datePickerDialog.show();
            }
        });

        enter = findViewById(R.id.submit_medication);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddMedication.this);
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
                myDB.addMedicine(name.getText().toString().trim(), Integer.valueOf(dosage.getText().toString().trim()), date);
                Intent intent = new Intent(AddMedication.this, medication.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        _year = year;
        _day = dayOfMonth;
        _month = month;
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMedication.this, AddMedication.this, hour, minute, DateFormat.is24HourFormat(this));
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
        //System.out.println(_month);
    }
}