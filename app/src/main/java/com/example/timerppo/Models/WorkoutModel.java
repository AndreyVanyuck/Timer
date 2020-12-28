package com.example.timerppo.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkoutModel {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public String Name;
    public int Preparation;
    public int WorkTime;
    public int RestTime;
    public int Cycles;
    public int Sets;
    public int RestSets;
    public int Color;

    public WorkoutModel(String name, int preparation, int workTime, int restTime,
                        int cycles, int sets, int restSets){  //, int color){
        this.Name = name;
        this.Preparation = preparation;
        this.WorkTime = workTime;
        this.RestTime = restTime;
        this.Cycles = cycles;
        this.Sets = sets;
        this.RestSets = restSets;
//        this.olor = color;
    }
    public WorkoutModel(){
        Name = "name";
    }
}
