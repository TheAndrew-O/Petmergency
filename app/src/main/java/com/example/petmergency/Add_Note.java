package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_Note extends AppCompatActivity {

    private Button save_but, cancel_but;
    private TextView textView;
    private EditText note_text, desc_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        note_text = findViewById(R.id.add_note);
        desc_text = findViewById(R.id.add_description);

        save_but = findViewById(R.id.saveNoteBut);
        save_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesDatabaseHelper notesDatabaseHelper = new NotesDatabaseHelper(Add_Note.this);
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date  = Calendar.getInstance().getTime();
                String format_date = sf.format(date);
                Toast.makeText(Add_Note.this, format_date, Toast.LENGTH_SHORT).show();
                notesDatabaseHelper.addNote(note_text.getText().toString().trim(), desc_text.getText().toString().trim(), format_date.toString().trim());
                Intent intent = new Intent(Add_Note.this, Notes.class);
                startActivity(intent);
            }
        });

        cancel_but = findViewById(R.id.cancelNoteBut);
        cancel_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Note.this, Notes.class);
                startActivity(intent);
            }
        });
    }
}