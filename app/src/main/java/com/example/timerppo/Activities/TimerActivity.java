package com.example.timerppo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timerppo.Models.WorkoutModel;
import com.example.timerppo.R;
import com.example.timerppo.Service.TimerService;
import com.example.timerppo.ViewModels.TimerViewModel;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    boolean isActive;
    long currentTime;
    int currentIndexStep;
    ArrayList<Integer> timeSteps;
    ArrayList<String> trainingSteps;
    TimerViewModel viewModel;
    TextView textViewTime;
    TextView textViewTask;
    ImageView imageViewPause;
    ListView listViewTimer;
    ImageView btnPrev;
    ImageView btnNext;
    ServiceConnection serviceConnection;
    TimerService timerService;
    TextView textViewTitle;
    Intent intent;
    BroadcastReceiver broadcastReceiver;
    private final String ACTION = "TIMER_ACTION";

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        init();

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id_timer");
        WorkoutModel workoutModel = viewModel.getById(id);

        prepareAdapter(workoutModel);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, trainingSteps);
        listViewTimer.setAdapter(adapter);

        textViewTitle.setText(workoutModel.Name);

        intent = new Intent(this, TimerService.class);
        intent.putExtra("current_index", 0);
        intent.putExtra("timeSteps", timeSteps);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentIndexStep = intent.getExtras().getInt("trainingIndexStep");
                String step = trainingSteps.get(currentIndexStep);
                textViewTask.setText(step);
                currentTime = intent.getExtras().getLong("currentTime");
                textViewTime.setText(String.valueOf(currentTime));
            }
        };
        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        if (!isActive) {
            startService(intent);
        }

        imageViewPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerService.timerPause(isActive);
                if (isActive) {
                    imageViewPause.setImageResource(R.drawable.ic_baseline_pause_24);
                } else {
                    imageViewPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }
                isActive = !isActive;
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexStep > 0) {
                    if (currentIndexStep == timeSteps.size()) {
                        currentIndexStep -= 2;
                    } else {
                        currentIndexStep--;
                    }
                    timerService.changeIndex(currentIndexStep);
                    textViewTask.setText(trainingSteps.get(currentIndexStep));
                    currentTime = timeSteps.get(currentIndexStep);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexStep < timeSteps.size() - 1) {
                    currentIndexStep++;
                    timerService.changeIndex(currentIndexStep);
                    textViewTask.setText(trainingSteps.get(currentIndexStep));
                    currentTime = timeSteps.get(currentIndexStep);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (timerService != null) {
            timerService.timerPause(true);
        }
        stopService(intent);
    }

    private void prepareAdapter(WorkoutModel workoutModel) {
        trainingSteps.add(this.getString(R.string.Preparation) + ": " + workoutModel.Preparation);
        timeSteps.add(workoutModel.Preparation * 1000);

        for (int i = 0; i < workoutModel.Sets; i++) {
            for (int j = 0; j < workoutModel.Cycles; j++) {
                trainingSteps.add(this.getString(R.string.Work) + ": " + workoutModel.WorkTime);
                timeSteps.add(workoutModel.WorkTime * 1000);
                trainingSteps.add(this.getString(R.string.Rest) + ": " + workoutModel.RestTime);
                timeSteps.add(workoutModel.RestTime * 1000);
            }
            if (workoutModel.Sets > 1) {
                trainingSteps.add(this.getString(R.string.RestSets) + ": " + workoutModel.RestSets);
                timeSteps.add(workoutModel.RestSets * 1000);
            }
        }

        trainingSteps.add(this.getString(R.string.Final));
        timeSteps.add(5000);
    }

    private void init() {
        isActive = false;
        viewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                timerService = ((TimerService.MyBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        trainingSteps = new ArrayList<String>();
        timeSteps = new ArrayList<Integer>();
        currentIndexStep = 0;
        textViewTitle = findViewById(R.id.textViewTimerTitle);
        textViewTime = findViewById(R.id.textViewTime);
        textViewTask = findViewById(R.id.textViewTimerTask);
        imageViewPause = findViewById(R.id.imageViewPause);
        listViewTimer = findViewById(R.id.listViewTimer);
        btnPrev = findViewById(R.id.imageViewPrevious);
        btnNext = findViewById(R.id.imageViewNext);
    }
}