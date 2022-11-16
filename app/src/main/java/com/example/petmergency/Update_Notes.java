package com.example.petmergency;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Update_Notes extends AppCompatActivity {

    EditText notes_edit, desc;
    TextView date_view;
    Button update_but, delete_but;
    String _id, _note, _desc, _date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_update);

        //date_view = findViewById(R.id.dateTv);

        notes_edit = findViewById(R.id.mEditTextNote);
        desc = findViewById(R.id.mEditTextDescription);

        getNotesData();

        update_but = findViewById(R.id.btnUpdate);
        update_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesDatabaseHelper notesDatabaseHelper = new NotesDatabaseHelper(Update_Notes.this);
                //Calendar cal = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                //String date = sdf.format(cal.getTime());
//                LocalDate d = LocalDate.now();
//                String date = sdf.format(d);
                notesDatabaseHelper.updateData(_id, notes_edit.getText().toString().trim(), desc.getText().toString().trim());
                Intent intent = new Intent(Update_Notes.this, Notes.class);
                startActivity(intent);
            }
        });

        delete_but = findViewById(R.id.btnDelete);
        delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AffirmDelete();
            }
        });
    }

    void getNotesData() {
        //Toast.makeText(this, "HERE",Toast.LENGTH_SHORT).show();
        if(getIntent().hasExtra("id") && getIntent().hasExtra("notes") && getIntent().hasExtra("desc")){
            _id = getIntent().getStringExtra("id");
            _note = getIntent().getStringExtra("notes");
            _desc = getIntent().getStringExtra("desc");
            //_date = getIntent().getStringExtra("date");
            //date_view.setText(_date);
            notes_edit.setText(_note);
            desc.setText(_desc);
        }
        else{
            Toast.makeText(this, "NO NOTES",Toast.LENGTH_SHORT).show();
        }
    }

    void AffirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + _note + "?");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NotesDatabaseHelper notesDatabaseHelper = new NotesDatabaseHelper(Update_Notes.this);
                notesDatabaseHelper.deleteRow(_id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Update_Notes.this, Notes.class);
                startActivity(intent);
            }
        });
        builder.create().show();
    }
}
