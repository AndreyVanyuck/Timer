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

    @Query("SELECT * FROM WorkoutModel WHERE id = :id")
    WorkoutModel getById(long id);

    @Insert
    void insert(WorkoutModel workoutModel);

    @Delete
    void delete(WorkoutModel workoutModel);

    @Update
    void update(WorkoutModel workoutModel);
}




/*
@Dao
public interface TimerDao {
    @Query("SELECT * FROM timerModel")
    List<TimerModel> getAll();

    @Query("SELECT * FROM timerModel WHERE id = :id")
    TimerModel getById(long id);

    @Query("SELECT * FROM timerModel WHERE name = :name")
    TimerModel getByName(String name);

    @Insert
    void insert(TimerModel timerModel);

    @Update
    void update(TimerModel timerModel);

    @Delete
    void delete(TimerModel timerModel);

    @Query("DELETE FROM timerModel")
    void DeleteAll();
}

*/
