package com.example.timerppo.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.timerppo.Models.WorkoutModel;

@Database(entities = {WorkoutModel.class}, version = 1, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {
    public abstract WorkoutDao workoutDao();
}