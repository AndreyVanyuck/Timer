package com.example.timerppo.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.timerppo.DB.DBHelper;
import com.example.timerppo.Models.WorkoutModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    DBHelper db;

    public MainViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application.getApplicationContext(),
                DBHelper.class, "database-name").allowMainThreadQueries().build();
    }

    public List<WorkoutModel> getAll(){
        return db.workoutDao().getAll();
    }

    public void delete(int id){
        db.workoutDao().delete(db.workoutDao().getById(id));
    }
}
