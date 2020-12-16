package com.example.timerppo.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timerppo.Models.WorkoutModel;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Query("SELECT * FROM workoutmodel")
    List<WorkoutModel> getAll();

    @Query("SELECT * FROM workoutmodel WHERE id = :id")
    WorkoutModel getById(long id);

    @Insert
    void insert(WorkoutModel workoutModel);

    @Update
    void update(WorkoutModel workoutModel);

    @Delete
    void delete(WorkoutModel workoutModel);

    @Query("DELETE FROM workoutmodel")
    void DeleteAll();
}
