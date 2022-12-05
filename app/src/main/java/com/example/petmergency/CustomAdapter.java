package com.example.petmergency;

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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    ArrayList id, name, dosage, date;
    Activity activity;
    //int pos;
    List<View> itemViewList = new ArrayList<>();

    CustomAdapter(Activity activity, Context context, ArrayList ids, ArrayList names, ArrayList dosages, ArrayList dates){
        this.activity = activity;
        this.context = context;
        this.id = ids;
        this.name = names;
        this.dosage = dosages;
        this.date = dates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_list, parent, false);
//        final MyViewHolder myViewHolder = new MyViewHolder(itemView);
//        itemViewList.add(itemView);
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(View tempItemView : itemViewList){
//                    if(itemViewList.get(myViewHolder.getAdapterPosition()) == tempItemView){
//                        tempItemView.setBackgroundResource(R.color.EmergencyRed);
//                    }
//                }
//            }
//        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medication_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //this.pos = position;

        //holder.med_id.setText(String.valueOf(id.get(position)));
        int num = Integer.parseInt(String.valueOf(id.get(position)));
//        if(num % 2 == 0){
//            holder.layout = holder.itemView.findViewById(R.id.missed);
//        }
        holder.med_name.setText(String.valueOf(name.get(position)));
        holder.med_dosage.setText("Dosage: " + String.valueOf(dosage.get(position)) + "Mg");
        holder.med_date.setText(String.valueOf("Date: " + date.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMedication.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("dosage", String.valueOf(dosage.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView med_id, med_name, med_dosage, med_date;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //med_id = itemView.findViewById(R.id.display_med_id);
            med_name = itemView.findViewById(R.id.display_med_name);
            med_dosage = itemView.findViewById(R.id.display_dosage);
            med_date = itemView.findViewById(R.id.display_date);
            layout = itemView.findViewById(R.id.med_list);

        }
    }
}
