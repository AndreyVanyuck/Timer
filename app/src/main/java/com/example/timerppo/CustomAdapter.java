package com.example.timerppo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timerppo.DB.DatabaseHelper;
import com.example.timerppo.Models.TimerModel;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<TimerModel> {
    private LayoutInflater inflater;
    private int layout;
    private List<TimerModel> timerModelList;
    private DatabaseHelper db;

    CustomAdapter(Context context, int resource, List<TimerModel> timerModels,
                 DatabaseHelper db) {
        super(context, resource, timerModels);
        this.timerModelList = timerModels;
        this.layout = resource;
        this.db = db;
        this.inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        Button removeButton, editButton, startButton;
        TextView nameView;
        LinearLayout layout;
        ViewHolder(View view){
            removeButton = view.findViewById(R.id.removeButton);
            editButton = view.findViewById(R.id.editButton);
            startButton = view.findViewById(R.id.startButton);
            nameView = view.findViewById(R.id.nameView);
            layout = view.findViewById(R.id.layout);
        }
    }
}