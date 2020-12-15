package com.example.timerppo.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.timerppo.Models.TimerModel;

@Database(entities = {TimerModel.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract TimerDao timerDao();
}