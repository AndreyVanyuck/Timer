package com.example.timerppo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.room.Room;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.timerppo.Adapters.WorkoutAdapter;
import com.example.timerppo.DB.DBHelper;
import com.example.timerppo.R;
import com.example.timerppo.ViewModels.CreateUpdateWorkoutViewModel;
import com.example.timerppo.ViewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageButton addButton;
    ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("theme", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        listView = findViewById(R.id.list_view_timers);
        WorkoutAdapter adapter = new WorkoutAdapter(this, R.layout.workout_item, viewModel.getAll());
        listView.setAdapter(adapter);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                assert extras != null;
                int position = extras.getInt("position");
                viewModel.delete(position);
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("item-deleted"));

        addButton = findViewById(R.id.button_add_new_workout);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateUpdateWorkoutActivity.class);
                startActivity(intent);
            }
        });
        settingButton = findViewById(R.id.setting_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}