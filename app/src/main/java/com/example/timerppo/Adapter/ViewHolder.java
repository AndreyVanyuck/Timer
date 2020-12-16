package com.example.timerppo.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timerppo.R;


public class ViewHolder {
    Button removeButton, editButton, startButton;
    TextView nameView, idView;
    LinearLayout layout;
    ViewHolder(View view){
        removeButton = view.findViewById(R.id.removeButton);
        editButton = view.findViewById(R.id.editButton);
        startButton = view.findViewById(R.id.startButton);
        nameView = view.findViewById(R.id.nameView);
        layout = view.findViewById(R.id.layout);
        idView = view.findViewById(R.id.idView);
    }
}