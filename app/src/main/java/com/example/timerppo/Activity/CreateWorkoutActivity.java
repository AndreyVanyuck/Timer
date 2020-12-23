package com.example.timerppo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import codes.side.andcolorpicker.hsl.HSLColorPickerSeekBar;
import codes.side.andcolorpicker.model.IntegerHSLColor;

import com.example.timerppo.Activity.MainActivity;
import com.example.timerppo.DB.DatabaseHandler;
import com.example.timerppo.DB.DatabaseHelper;
import com.example.timerppo.Models.WorkoutModel;
import com.example.timerppo.R;
import com.example.timerppo.ViewModel.CreateViewModel;

public class CreateWorkoutActivity extends AppCompatActivity {
    Button buttonPrepairPlus;
    Button buttonPrepairMinus;
    Button buttonWorkPlus;
    Button buttonWorkMinus;
    Button buttonRestPlus;
    Button buttonRestMinus;
    Button buttonCyclePlus;
    Button buttonCycleMinus;
    Button buttonSetPlus;
    Button buttonSetMinus;
    Button buttonCalmPlus;
    Button buttonCalmMinus;

    EditText inputName;
    EditText inputPrepair;
    EditText inputWork;
    EditText inputRest;
    EditText inputCycle;
    EditText inputSet;
    EditText inputCalm;

    HSLColorPickerSeekBar colorBar;

    CreateViewModel createViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timer);

        createViewModel = new ViewModelProvider(this).get(CreateViewModel.class);

        buttonCalmMinus = (Button) findViewById(R.id.minus_calm);
        buttonCalmPlus =  (Button) findViewById(R.id.plus_calm);
        buttonCycleMinus =  (Button) findViewById(R.id.minus_cycle);
        buttonCyclePlus =  (Button) findViewById(R.id.plus_cycle);
        buttonPrepairMinus =  (Button) findViewById(R.id.minus_prepair);
        buttonPrepairPlus =  (Button) findViewById(R.id.plus_prepair);
        buttonRestMinus =  (Button) findViewById(R.id.minus_rest);
        buttonRestPlus =  (Button) findViewById(R.id.plus_rest);
        buttonWorkPlus =  (Button) findViewById(R.id.plus_work);
        buttonWorkMinus =  (Button) findViewById(R.id.minus_work);
        buttonSetPlus =  (Button) findViewById(R.id.plus_set);
        buttonSetMinus =  (Button) findViewById(R.id.minus_set);

        inputName = (EditText) findViewById(R.id.inputName)
        inputCalm = (EditText) findViewById(R.id.calm_value);
        inputCycle = (EditText) findViewById(R.id.calm_value);
        inputSet = (EditText) findViewById(R.id.calm_value);
        inputWork = (EditText) findViewById(R.id.calm_value);
        inputRest = (EditText) findViewById(R.id.calm_value);
        inputPrepair = (EditText) findViewById(R.id.calm_value);

        colorBar = (HSLColorPickerSeekBar)findViewById(R.id.color_seek_bar);


        final Observer<String> inputNameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) { inputName.setText(s); }
        };
        final Observer<Integer> inputPrepairObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputPrepair.setText(Integer.toString(s));
            }
        };
        final Observer<Integer> inputWorkObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputWork.setText(Integer.toString(s));
            }
        };
        final Observer<Integer> inputRestObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputRest.setText(Integer.toString(s));
            }
        };
        final Observer<Integer> inputCycleObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputCycle.setText(Integer.toString(s));
            }
        };
        final Observer<Integer> inputSetObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputSet.setText(Integer.toString(s));
            }
        };
        final Observer<Integer> inputCalmObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                inputCalm.setText(Integer.toString(s));
            }
        };

        createViewModel.getPreparation().observe(this, inputPrepairObserver);
        createViewModel.getCycles().observe(this, inputCycleObserver);
        createViewModel.getRestSets().observe(this, inputCalmObserver);
        createViewModel.getRestTime().observe(this, inputRestObserver);
        createViewModel.getSets().observe(this, inputSetObserver);
        createViewModel.getWorkTime().observe(this, inputWorkObserver);
        createViewModel.getName().observe(this, inputNameObserver);

        buttonCalmMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setRestSets(createViewModel.getRestSets().getValue() - 1);
            }
        });
        buttonCalmPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setRestSets(createViewModel.getRestSets().getValue() + 1);
            }
        });
        buttonCycleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setCycles(createViewModel.getCycles().getValue() - 1);
            }
        });
        buttonCyclePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setCycles(createViewModel.getCycles().getValue() + 1);
            }
        });
        buttonPrepairMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setPreparation(createViewModel.getPreparation().getValue() - 1);
            }
        });
        buttonPrepairPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setPreparation(createViewModel.getPreparation().getValue() + 1);
            }
        });
        buttonRestMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setRestTime(createViewModel.getRestTime().getValue() - 1);
            }
        });
        buttonRestPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setRestTime(createViewModel.getRestTime().getValue() + 1);
            }
        });
        buttonWorkPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setWorkTime(createViewModel.getWorkTime().getValue() + 1);
            }
        });
        buttonWorkMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setWorkTime(createViewModel.getWorkTime().getValue() - 1);
            }
        });
        buttonSetPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setSets(createViewModel.getSets().getValue() + 1);
            }
        });
        buttonSetMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createViewModel.setSets(createViewModel.getSets().getValue() - 1);
            }
        });

        inputName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    createViewModel.setName(inputName.getText().toString());
                    return true;
                }
                return false;
            }
        });


        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                int[] id = (int[])bundle.get("timerId");

                if (id[1] != 1) {
                    createViewModel.insertItem();
                }
                else{
                    createViewModel.updateItem(id[0]);
                }
                    Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(backIntent);
            }
        });

    }
}