package com.example.petmergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity {
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineUserID;
    private FloatingActionButton addNoteButton;

    private ProgressDialog loader;

    private String key = "";
    private String note;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_notes_main);

        mUser = mAuth.getCurrentUser();
        onlineUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("notes").child(onlineUserID);

        loader = new ProgressDialog(this);

        addNoteButton = findViewById(R.id.addNoteBtn);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void addNote() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.activity_notes_input, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        final EditText note = myView.findViewById(R.id.note);
        final EditText description = myView.findViewById(R.id.description);
        Button save = myView.findViewById(R.id.saveNoteBtn);
        Button cancel = myView.findViewById(R.id.cancelNoteBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNote = note.getText().toString().trim();
                String mDescription = description.getText().toString().trim();
                String id = reference.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                if (TextUtils.isEmpty(mNote)) {
                    note.setError("Note Required");
                    return;
                }
                if (TextUtils.isEmpty(mDescription)) {
                    description.setError("Description Required");
                    return;
                } else {
                    loader.setMessage("Adding your data");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    Notes_Model model = new Notes_Model(mNote, mDescription, id, date);
                    reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Notes.this, "Note has been inserted successfully", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(Notes.this, "Failed: " + error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });

                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }



}