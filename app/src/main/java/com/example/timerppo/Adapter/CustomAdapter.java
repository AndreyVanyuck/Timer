package com.example.timerppo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.timerppo.Activity.CreateWorkoutActivity;
import com.example.timerppo.Models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private int layout;
    private List<WorkoutModel> workoutModelList = new ArrayList<WorkoutModel>();
    private Context context;

    public CustomAdapter(Context context, int resource, List<WorkoutModel> workoutModels) {
        this.context = context;
        this.workoutModelList = workoutModels;
        this.layout = resource;
    }

    @Override
    public int getCount() {
        return workoutModelList.size();
    }

    @Override
    public WorkoutModel getItem(int position) {
        return workoutModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return workoutModelList.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WorkoutModel workoutModel = workoutModelList.get(position);
        viewHolder.nameView.setText(workoutModel.getName());
        viewHolder.idView.setText((Integer.toString(workoutModel.getId())));
        viewHolder.layout.setBackgroundColor(workoutModel.getColor());

        viewHolder.startButton.setOnClickListener(i -> {
            Context context = getContext();
            Intent intent = new Intent(context, CreateWorkoutActivity.class);
            intent.putExtra("timerId", workoutModel.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        viewHolder.removeButton.setOnClickListener(i -> {
            sendBroadcast(position);
            workoutModelList.remove(workoutModel);
            notifyDataSetChanged();
        });

        viewHolder.editButton.setOnClickListener(i -> {
            Context context = getContext();
            Intent intent = new Intent(context, CreateWorkoutActivity.class);
            intent.putExtra("timerId", new int[]{workoutModel.getId(), 1});
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        return convertView;
    }

    private void sendBroadcast(int position) {
        Intent intent = new Intent("item-deleted");
        intent.putExtra("position", position);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}