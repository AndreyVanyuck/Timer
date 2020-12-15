package com.example.timerppo.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkoutModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int preparation;
    private int workTime;
    private int restTime;
    private int cycles;
    private int sets;
    private int restSets;
    private int color;

    public WorkoutModel(String name, int preparation, int workTime, int restTime,
                        int cycles, int sets, int restSets, int color){
        this.name = name;
        this.preparation = preparation;
        this.workTime = workTime;
        this.restTime = restTime;
        this.cycles = cycles;
        this.sets = sets;
        this.restSets = restSets;
        this.color = color;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getColor(){
        return color;
    }

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRestSets() {
        return restSets;
    }

    public void setRestSets(int restSets) {
        this.restSets = restSets;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}