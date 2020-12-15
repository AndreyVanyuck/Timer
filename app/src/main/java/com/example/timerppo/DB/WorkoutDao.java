package com.example.timerppo.DB;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.timerppo.Models.WorkoutModel;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Query("SELECT * FROM WorkoutModel")
    List<WorkoutModel> getAll();
}
