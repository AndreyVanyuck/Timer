package com.example.timerppo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

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
}