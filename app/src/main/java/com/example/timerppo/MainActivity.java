package com.example.timerppo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.timerppo.DB.DatabaseHandler;
import com.example.timerppo.DB.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseHandler.getInstance().getDatabase();

        listView = findViewById(R.id.listTimer);
        //TODO
        //Set adapter

    }
}