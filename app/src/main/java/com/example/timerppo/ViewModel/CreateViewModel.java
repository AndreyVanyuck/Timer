package com.example.timerppo.ViewModel;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timerppo.DB.DatabaseHandler;
import com.example.timerppo.DB.DatabaseHelper;
import com.example.timerppo.Models.WorkoutModel;


public class CreateViewModel extends ViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<Integer> preparation;
    private MutableLiveData<Integer> workTime;
    private MutableLiveData<Integer> restTime;
    private MutableLiveData<Integer> restSets;
    private MutableLiveData<Integer> cycles;
    private MutableLiveData<Integer> sets;
    private MutableLiveData<Integer> color;
    private DatabaseHelper db;


    public CreateViewModel(){
        name = new MutableLiveData<>("");
        preparation = new MutableLiveData<>(10);
        workTime = new MutableLiveData<>(20);
        restTime = new MutableLiveData<>(10);
        restSets = new MutableLiveData<>(10);
        cycles = new MutableLiveData<>(8);
        sets = new MutableLiveData<>(4);
        color = new MutableLiveData<>();
        db = DatabaseHandler.getInstance().getDatabase();
    }

    public MutableLiveData<Integer> getPreparation() {
        return preparation;
    }

    public void setPreparation(Integer preparation) {
        this.preparation.setValue(preparation);
    }

    public MutableLiveData<Integer> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime.setValue(workTime);
    }

    public MutableLiveData<Integer> getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime.setValue(restTime);
    }

    public MutableLiveData<Integer> getRestSets() {
        return restSets;
    }

    public void setRestSets(Integer restSets) {
        this.restSets.setValue(restSets);
    }

    public MutableLiveData<Integer> getCycles() {
        return cycles;
    }

    public void setCycles(Integer cycles) {
        this.cycles.setValue(cycles);
    }

    public MutableLiveData<Integer> getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets.setValue(sets);
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void insertItem(){
        WorkoutModel workoutModel = new WorkoutModel(
                name.getValue(),
                preparation.getValue(),
                workTime.getValue(),
                restTime.getValue(),
                cycles.getValue(),
                sets.getValue(),
                restSets.getValue(),
                color.getValue()
        );
        db.timerDao().insert(workoutModel);
    }

    public void updateItem(int id){
        WorkoutModel workoutModel = db.timerDao().getById(id);

        workoutModel.setName(name.getValue());
        workoutModel.setPreparation(preparation.getValue());
        workoutModel.setWorkTime(workTime.getValue());
        workoutModel.setRestTime(restTime.getValue());
        workoutModel.setCycles(cycles.getValue());
        workoutModel.setSets(sets.getValue());
        workoutModel.setRestSets(restSets.getValue());
        workoutModel.setColor(color.getValue());

        db.timerDao().update(workoutModel);
    }
}

