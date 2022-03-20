package com.example.mommayells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    private final TextView NameEvent;
    private final TextView DateEvent;
//    private final TextView DetailEvent;
   // ConstraintLayout mainLayout;

    private ScheduleViewHolder(View itemView){
        super(itemView);
        NameEvent = (TextView) itemView.findViewById(R.id.schedule);
        DateEvent = (TextView) itemView.findViewById(R.id.dateSchedule);
//        DetailEvent = (TextView) itemView.findViewById(R.id.addKeteranganEvent);
       // mainLayout = itemView.findViewById(R.id.mainLayout);
    }
    public void bind(String nameEvent, String dateEvent,  String detailEvent) {
        NameEvent.setText(nameEvent);
        DateEvent.setText(dateEvent);
//        DetailEvent.setText(detailEvent);
    }

    static ScheduleViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_list,parent,false);
        return new ScheduleViewHolder(view);
    }
}
