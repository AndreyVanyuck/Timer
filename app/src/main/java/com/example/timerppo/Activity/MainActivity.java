package com.example.timerppo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.timerppo.Adapter.CustomAdapter;
import com.example.timerppo.DB.DatabaseHandler;
import com.example.timerppo.DB.DatabaseHelper;
import com.example.timerppo.Models.WorkoutModel;
import com.example.timerppo.R;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseHandler.getInstance().getDatabase();

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                assert extras != null;
                int position = extras.getInt("position");
                db.timerDao().delete(db.timerDao().getById(position));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("item-deleted"));

        listView = findViewById(R.id.listTimer);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, db.timerDao().getAll());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkoutModel workoutModel = (WorkoutModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra("id", workoutModel.getId());
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonAddTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateWorkoutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.settingsButton).setOnClickListener(i -> {
            Intent Settings = new Intent(this, SettingsActivity.class);
            startActivityForResult(Settings, 1);
        });

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            recreate();
        }
    }
}