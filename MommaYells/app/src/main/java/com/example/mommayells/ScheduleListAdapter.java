package com.example.mommayells;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScheduleListAdapter extends ListAdapter<Schedule, ScheduleViewHolder> {
    Context context;
    public ScheduleListAdapter(@NonNull DiffUtil.ItemCallback<Schedule> diffCallback){
        super(diffCallback);
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ScheduleViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Schedule current = getItem(position);
        holder.bind(current.getScheduleName(), current.getScheduleDate(), current.getScheduleDetail());
    }

    static public class ScheduleDiff extends DiffUtil.ItemCallback<Schedule> {

        @Override
        public boolean areItemsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            boolean same;
            same = oldItem.getScheduleName().equals(newItem.getScheduleName());
            return same;
        }
    }

}

////    private final LinkedList<String> mScheduleList;
////    private final LayoutInflater mInflater;
//
//    private ArrayList<Schedule> dataList;
//
//    public ScheduleListAdapter(ArrayList<Schedule> dataList) {
//        this.dataList = dataList;
//    }
//
//    @Override
//    public ScheduleListAdapter.ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//        View view = layoutInflater.inflate(R.layout.schedule_list, viewGroup, false);
//        return new ScheduleViewHolder(view);
//    }
//public class ScheduleViewHolder extends RecyclerView.ViewHolder {
//    private TextView mSchedule;
//
//    public ScheduleViewHolder(@NonNull View itemView) {
//        super(itemView);
//        mSchedule = (TextView) itemView.findViewById(R.id.schedule);
//
//    }
//}
//    @Override
//    //method connects your data to the view holder
//    public void onBindViewHolder(ScheduleListAdapter.ScheduleViewHolder holder, int i) {
//        holder.mSchedule.setText(dataList.get(i).getScheduleName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return (dataList != null) ? dataList.size() : 0;
//    }
////
