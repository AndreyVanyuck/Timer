package com.example.timerppo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.timerppo.DB.DatabaseHelper;
import com.example.timerppo.Models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private int layout;
    private List<WorkoutModel> workoutModelList = new ArrayList<WorkoutModel>();
    private DatabaseHelper db;
    private Context context;

    CustomAdapter(Context context, int resource, List<WorkoutModel> workoutModels,
                 DatabaseHelper db) {
        this.context = context;
        this.workoutModelList = workoutModels;
        this.layout = resource;
        this.db = db;
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
/*

        //TODO
        viewHolder.startButton.setOnClickListener(i -> {
        });

        //TODO
        viewHolder.removeButton.setOnClickListener(i -> {
        });

        //TODO
        viewHolder.editButton.setOnClickListener(i -> {
        });
*/

        return convertView;
    }
}