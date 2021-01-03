package com.example.timerppo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.example.timerppo.Models.WorkoutModel;
import com.example.timerppo.R;
import com.example.timerppo.ViewModels.CreateUpdateWorkoutViewModel;

public class CreateUpdateWorkoutActivity extends AppCompatActivity {
    Button btnPrepPlus;
    Button btnPrepMinus;
    Button btnWorkPlus;
    Button btnWorkMinus;
    Button btnRestPlus;
    Button btnRestMinus;
    Button btnCyclePlus;
    Button btnCycleMinus;
    Button btnSetPlus;
    Button btnSetMinus;
    Button btnCalmPlus;
    Button btnCalmMinus;

    EditText inputName;
    EditText inputPrep;
    EditText inputWork;
    EditText inputRest;
    EditText inputCycle;
    EditText inputSet;
    EditText inputCalm;

    CreateUpdateWorkoutViewModel viewModel;
    WorkoutModel workoutModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        btnPrepPlus = findViewById(R.id.btnPrepPlus);
        btnPrepMinus = findViewById(R.id.btnPrepMinus);
        btnWorkPlus = findViewById(R.id.btnWorkPlus);
        btnWorkMinus = findViewById(R.id.btnWorkMinus);
        btnRestPlus = findViewById(R.id.btnRestPlus);
        btnRestMinus = findViewById(R.id.btnRestMinus);
        btnCyclePlus = findViewById(R.id.btnCyclePlus);
        btnCycleMinus = findViewById(R.id.btnCycleMinus);
        btnSetPlus = findViewById(R.id.btnSetPlus);
        btnSetMinus = findViewById(R.id.btnSetMinus);
        btnCalmPlus = findViewById(R.id.btnCalmPlus);
        btnCalmMinus = findViewById(R.id.btnCalmMinus);

        inputName = findViewById(R.id.inputName);
        inputPrep = findViewById(R.id.inputPrep);
        inputWork = findViewById(R.id.inputWork);
        inputRest = findViewById(R.id.inputRest);
        inputCycle = findViewById(R.id.inputCycle);
        inputSet = findViewById(R.id.inputSet);
        inputCalm = findViewById(R.id.inputCalm);

        viewModel = new ViewModelProvider(this).get(CreateUpdateWorkoutViewModel.class);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id;
        try {
            id = (int)bundle.get("id");
        }
        catch (Exception e) {
            id = -1;
        }

        if (id != -1){
            workoutModel = viewModel.getById(id);
            viewModel.setName(workoutModel.Name);
            viewModel.setPrep(workoutModel.Preparation);
            viewModel.setWork(workoutModel.WorkTime);
            viewModel.setRest(workoutModel.RestTime);
            viewModel.setCycle(workoutModel.Cycles);
            viewModel.setSets(workoutModel.Sets);
            viewModel.setRestSets(workoutModel.RestSets);
            //viewModel.setColor(timerModel.Color);
        }

        viewModel.getName().observe(this, val -> inputName.setText(val));
        viewModel.getPreparation().observe(this, val -> inputPrep.setText(val.toString()));
        viewModel.getWorkTime().observe(this, val -> inputWork.setText(val.toString()));
        viewModel.getRestTime().observe(this, val -> inputRest.setText(val.toString()));
        viewModel.getCycles().observe(this, val -> inputCycle.setText(val.toString()));
        viewModel.getSets().observe(this, val -> inputSet.setText(val.toString()));
        viewModel.getRestSets().observe(this, val -> inputCalm.setText(val.toString()));
        btnPrepPlus.setOnClickListener(i -> viewModel.setIncrementPreparation());
        btnPrepMinus.setOnClickListener(i -> viewModel.setDecrementPreparation());

        btnWorkPlus.setOnClickListener(i -> viewModel.setIncrementWorkTime());
        btnWorkMinus.setOnClickListener(i -> viewModel.setDecrementWorkTime());

        btnRestPlus.setOnClickListener(i -> viewModel.setIncrementRestTime());
        btnRestMinus.setOnClickListener(i -> viewModel.setDecrementRestTime());

        btnCyclePlus.setOnClickListener(i -> viewModel.setIncrementCycle());
        btnCycleMinus.setOnClickListener(i -> viewModel.setDecrementCycle());

        btnSetPlus.setOnClickListener(i -> viewModel.setIncrementSets());
        btnSetMinus.setOnClickListener(i -> viewModel.setDecrementSets());

        btnCalmPlus.setOnClickListener(i -> viewModel.setIncrementRestSets());
        btnCalmMinus.setOnClickListener(i -> viewModel.setDecrementRestSets());

        inputName.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.setName(inputName.getText().toString());
                return true;
            }
            return false;
        });

        findViewById(R.id.submit).setOnClickListener(i -> {
            int idWorkout;

            try {
                idWorkout = (int)bundle.get("id");
            }
            catch (Exception e) {
                idWorkout = -1;
            }

            if (idWorkout != -1){
                viewModel.updateItem(idWorkout);
            }
            else {
                viewModel.insertItem();
            }

            Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(backIntent);
        });

    }
}