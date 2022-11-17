package com.example.petmergency;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CustomAdapterNotes extends RecyclerView.Adapter<CustomAdapterNotes.MyNotesHolder>{

    Context context;
    ArrayList id, notes, desc;
    Activity activity;

    CustomAdapterNotes(Activity activity, Context context, ArrayList _id, ArrayList _note, ArrayList _desc){
        this.activity = activity;
        this.context = context;
        this.id = _id;
        this.notes = _note;
        this.desc = _desc;
        //this.date = date;
    }

    @NonNull
    @Override
    public MyNotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_notes_retrieve, parent,false);
        return new MyNotesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNotesHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.note.setText(String.valueOf(notes.get(position)));
        holder.desc.setText(String.valueOf(desc.get(position)));
        //holder.date.setText(String.valueOf(date.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Notes.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("notes",String.valueOf(notes.get(position)));
                intent.putExtra("desc",String.valueOf(desc.get(position)));
                //intent.putExtra("date",String.valueOf(date.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }


    public class MyNotesHolder extends RecyclerView.ViewHolder{
        TextView note, desc, date;
        //LinearLayout layout;
        CardView layout;
        public MyNotesHolder(@NonNull View itemView){
            super(itemView);
            note = itemView.findViewById(R.id.noteTv);
            desc = itemView.findViewById(R.id.descriptionTv);
            //date = itemView.findViewById(R.id.dateTv);
            layout = itemView.findViewById(R.id.note_list);
        }
    }
}