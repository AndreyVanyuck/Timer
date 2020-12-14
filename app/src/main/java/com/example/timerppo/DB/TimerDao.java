package com.example.timerppo.DB;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.timerppo.Models.TimerModel;

import java.util.List;

@Dao
public interface TimerDao {
    @Query("SELECT * FROM timerModel")
    List<TimerModel> getAll();
}
