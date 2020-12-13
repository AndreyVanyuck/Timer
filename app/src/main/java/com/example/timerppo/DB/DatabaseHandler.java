package com.example.timerppo.DB;


import android.app.Application;

import androidx.room.Room;

public class DatabaseHandler extends Application {

    public static DatabaseHandler instance;

    private DatabaseHelper database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, DatabaseHelper.class, "TimerDatabase")
                .allowMainThreadQueries()
                .build();
    }

    public static DatabaseHandler getInstance() {
        return instance;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }
}