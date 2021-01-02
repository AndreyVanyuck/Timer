package com.example.timerppo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.timerppo.Activities.CreateUpdateWorkoutActivity;
import com.example.timerppo.Activities.TimerActivity;
import com.example.timerppo.Models.WorkoutModel;
import com.example.timerppo.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends BaseAdapter {
    private int layout;
    private List<WorkoutModel> workoutModelList = new ArrayList<WorkoutModel>();
    private Context context;

    public WorkoutAdapter(Context context, int resource, List<WorkoutModel> workoutModels){
        this.context = context;
        this.workoutModelList = workoutModels;
        this.layout = resource;
    }
    @Override
    public int getCount() {
        return workoutModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return workoutModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //TODO
        return 0;
    }

    @Override
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
        viewHolder.nameView.setText(workoutModel.Name);
        viewHolder.idView.setText((Integer.toString(workoutModel.Id)));

        viewHolder.startButton.setOnClickListener(i -> {
            Intent intent = new Intent(context, TimerActivity.class);
            intent.putExtra("id_timer", Integer.parseInt(viewHolder.idView.getText().toString()));
            context.startActivity(intent);
        });

        viewHolder.removeButton.setOnClickListener(i -> {
            sendBroadcast(viewHolder.idView.getText().toString());
            workoutModelList.remove(workoutModel);
            notifyDataSetChanged();
        });

        viewHolder.editButton.setOnClickListener(i -> {
            Intent intent = new Intent(context, CreateUpdateWorkoutActivity.class);
            intent.putExtra("id", Integer.parseInt(viewHolder.idView.getText().toString()));
            context.startActivity(intent);
        });

        return convertView;
    }

    private void sendBroadcast(String position) {
        Intent intent = new Intent("item-deleted");
        intent.putExtra("position", Integer.parseInt(position));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private class ViewHolder {
        Button removeButton, editButton, startButton;
        TextView nameView, idView;
        LinearLayout layout;
        ViewHolder(View view){
            removeButton = view.findViewById(R.id.remove_button);
            editButton = view.findViewById(R.id.edit_button);
            startButton = view.findViewById(R.id.start_button);
            nameView = view.findViewById(R.id.name_view);
            layout = view.findViewById(R.id.layout);
            idView = view.findViewById(R.id.id_view);
        }
    }
}

