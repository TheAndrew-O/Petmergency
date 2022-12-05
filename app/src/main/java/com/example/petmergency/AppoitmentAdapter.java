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

public class AppoitmentAdapter extends RecyclerView.Adapter<AppoitmentAdapter.MyViewHolder>{

    Context context;
    ArrayList id, location, desc, date;
    Activity activity;
    //int pos;
    List<View> itemViewList = new ArrayList<>();

    AppoitmentAdapter(Activity activity, Context context, ArrayList ids, ArrayList locations, ArrayList descs, ArrayList dates){
        this.activity = activity;
        this.context = context;
        this.id = ids;
        this.location = locations;
        this.desc = descs;
        this.date = dates;
    }

    @NonNull
    @Override
    public AppoitmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.appoitment_list,parent,false);
        return new AppoitmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppoitmentAdapter.MyViewHolder holder, int position) {
        holder.app_loc.setText(String.valueOf(location.get(position)));
        holder.app_desc.setText(String.valueOf(desc.get(position)));
        holder.app_date.setText(String.valueOf("Date: " + date.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateAppoitment.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                intent.putExtra("desc", String.valueOf(desc.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView app_id, app_loc, app_desc, app_date;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //med_id = itemView.findViewById(R.id.display_med_id);
            app_loc = itemView.findViewById(R.id.display_loc);
            app_desc = itemView.findViewById(R.id.display_descr);
            app_date = itemView.findViewById(R.id.display_apdate);
            layout = itemView.findViewById(R.id.appoitment);

        }
    }
}
