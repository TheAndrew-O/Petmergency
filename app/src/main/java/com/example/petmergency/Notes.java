package com.example.petmergency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notes extends AppCompatActivity {

    private RecyclerView recyclerView;
    NotesDatabaseHelper notesdb;
    ArrayList<String> id, notes, desc, date;
    CustomAdapterNotes customAdapterNotes;
    //--------------------------
//    private DatabaseReference reference;
//    private FirebaseAuth mAuth;
//    private FirebaseUser mUser;
//    private String onlineUserID;
    private FloatingActionButton addNoteButton;

//    private ProgressDialog loader;
//
//    private String key = "";
//    private String note;
//    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_notes_main);

        recyclerView = findViewById(R.id.notes_recyclerView);

//        mUser = mAuth.getCurrentUser();
//        onlineUserID = mUser.getUid();
//        reference = FirebaseDatabase.getInstance().getReference().child("notes").child(onlineUserID);
//
//        loader = new ProgressDialog(this);

        addNoteButton = findViewById(R.id.addNoteBtn);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, Add_Note.class);
                startActivity(intent);
            }
        });

        notesdb = new NotesDatabaseHelper(Notes.this);
        id = new ArrayList<>();
        notes = new ArrayList<>();
        desc = new ArrayList<>();

        SetData();
        customAdapterNotes = new CustomAdapterNotes(Notes.this, this, id, notes, desc);
        recyclerView.setAdapter(customAdapterNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notes.this));
    }

    void SetData() {
        Cursor cursor = notesdb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "NO NOTES AVAILABLE", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                notes.add(cursor.getString(1));
                desc.add(cursor.getString(2));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

//    private void addNote() {
//        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//
//        View myView = inflater.inflate(R.layout.activity_notes_input, null);
//        myDialog.setView(myView);
//
//        final AlertDialog dialog = myDialog.create();
//        dialog.setCancelable(false);
//
//        final EditText note = myView.findViewById(R.id.note);
//        final EditText description = myView.findViewById(R.id.description);
//        Button save = myView.findViewById(R.id.saveNoteBtn);
//        Button cancel = myView.findViewById(R.id.cancelNoteBtn);
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String mNote = note.getText().toString().trim();
//                String mDescription = description.getText().toString().trim();
//                String id = reference.push().getKey();
//                String date = DateFormat.getDateInstance().format(new Date());
//
//                if (TextUtils.isEmpty(mNote)) {
//                    note.setError("Note Required");
//                    return;
//                }
//                if (TextUtils.isEmpty(mDescription)) {
//                    description.setError("Description Required");
//                    return;
//                } else {
//                    loader.setMessage("Adding your data");
//                    loader.setCanceledOnTouchOutside(false);
//                    loader.show();
//
//                    Notes_Model model = new Notes_Model(mNote, mDescription, id, date);
//                    reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(Notes.this, "Note has been inserted successfully", Toast.LENGTH_SHORT).show();
//                                loader.dismiss();
//                            } else {
//                                String error = task.getException().toString();
//                                Toast.makeText(Notes.this, "Failed: " + error, Toast.LENGTH_SHORT).show();
//                                loader.dismiss();
//                            }
//                        }
//                    });
//
//                }
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        View mView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mView = itemView;
//        }
//
//        public void setNote(String note) {
//            TextView noteTectView = mView.findViewById(R.id.noteTv);
//            noteTectView.setText(note);
//        }
//
//        public void setDesc(String desc) {
//            TextView descTectView = mView.findViewById(R.id.descriptionTv);
//            descTectView.setText(desc);
//        }
//
//        public void setDate(String date) {
//            TextView dateTextView = mView.findViewById(R.id.dateTv);
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerOptions<Notes_Model> options = new FirebaseRecyclerOptions.Builder<Notes_Model>()
//                .setQuery(reference, Notes_Model.class)
//                .build();
//
//        FirebaseRecyclerAdapter<Notes_Model, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Notes_Model, MyViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Notes_Model model) {
//                holder.setDate(model.getDate());
//                holder.setNote(model.getNote());
//                holder.setDesc(model.getDescription());
//
//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        key = getRef(position).getKey();
//                        note = model.getNote();
//                        description = model.getDescription();
//
//                        updateNote();
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notes_retrieve, parent, false);
//                return new MyViewHolder(view);
//            }
//        };
//
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }
//
//    private void updateNote() {
//        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.activity_notes_update, null);
//        myDialog.setView(view);
//
//        final AlertDialog dialog = myDialog.create();
//
//        final EditText mNote = view.findViewById(R.id.mEditTextNote);
//        final EditText mDescription = view.findViewById(R.id.mEditTextDescription);
//
//        mNote.setText(note);
//        mNote.setSelection(note.length());
//
//        mDescription.setText(description);
//        mDescription.setSelection(description.length());
//
//        Button delButton = view.findViewById(R.id.btnDelete);
//        Button updateButton = view.findViewById(R.id.btnUpdate);
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                note = mNote.getText().toString().trim();
//                description = mDescription.getText().toString().trim();
//
//                String date = DateFormat.getDateInstance().format(new Date());
//
//                Notes_Model model = new Notes_Model(note, description, key, date);
//
//                reference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful()){
//                            Toast.makeText(Notes.this, "Data has been updated successfully", Toast.LENGTH_SHORT).show();
//                        }else {
//                            String err = task.getException().toString();
//                            Toast.makeText(Notes.this, "update failed "+err, Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//                dialog.dismiss();
//
//            }
//        });
//
//        delButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(Notes.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
//                        }else {
//                            String err = task.getException().toString();
//                            Toast.makeText(Notes.this, "Failed to delete note "+ err, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
}