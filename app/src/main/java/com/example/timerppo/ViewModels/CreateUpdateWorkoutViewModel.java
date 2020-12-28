package com.example.timerppo.ViewModels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.timerppo.DB.DBHelper;
import com.example.timerppo.Models.WorkoutModel;

public class CreateUpdateWorkoutViewModel extends AndroidViewModel {
    private MutableLiveData<String> Name;
    private MutableLiveData<Integer> Preparation;
    private MutableLiveData<Integer> WorkTime;
    private MutableLiveData<Integer> RestTime;
    private MutableLiveData<Integer> RestSets;
    private MutableLiveData<Integer> Cycles;
    private MutableLiveData<Integer> Sets;
    DBHelper db;

    public CreateUpdateWorkoutViewModel(@NonNull Application application){
        super(application);
        Name = new MutableLiveData<>("");
        Preparation = new MutableLiveData<>(10);
        WorkTime = new MutableLiveData<>(20);
        RestTime = new MutableLiveData<>(10);
        RestSets = new MutableLiveData<>(10);
        Cycles = new MutableLiveData<>(2);
        Sets = new MutableLiveData<>(2);

        db = Room.databaseBuilder(application.getApplicationContext(),
                DBHelper.class, "database-name").allowMainThreadQueries().build();
    }

    public void insertItem(){
        WorkoutModel workoutModel = new WorkoutModel(
                Name.getValue(),
                Preparation.getValue(),
                WorkTime.getValue(),
                RestTime.getValue(),
                Cycles.getValue(),
                Sets.getValue(),
                RestSets.getValue()
        );
        db.workoutDao().insert(workoutModel);
    }

    public void updateItem(int id){
        WorkoutModel workoutModel = getById(id);
        workoutModel.Name = Name.getValue();
        workoutModel.Preparation = Preparation.getValue();
        workoutModel.WorkTime = WorkTime.getValue();
        workoutModel.RestTime = RestTime.getValue();
        workoutModel.Cycles = Cycles.getValue();
        workoutModel.Sets = Sets.getValue();
        workoutModel.RestSets = RestSets.getValue();
       /* workoutModel.setColor(color.getValue());*/
        db.workoutDao().update(workoutModel);
    }

    public WorkoutModel getById(int id){
        return db.workoutDao().getById(id);
    }

    public MutableLiveData<String> getName() {
        return Name;
    }

    public void setPreparation(MutableLiveData<Integer> preparation) {
        Preparation = preparation;
    }

    public void setWorkTime(MutableLiveData<Integer> workTime) {
        WorkTime = workTime;
    }

    public void setRestTime(MutableLiveData<Integer> restTime) {
        RestTime = restTime;
    }

    public void setRestSets(MutableLiveData<Integer> restSets) {
        RestSets = restSets;
    }

    public void setCycles(MutableLiveData<Integer> cycles) {
        Cycles = cycles;
    }

    public void setName(String name) {
        Name.setValue(name);
    }

    public MutableLiveData<Integer> getPreparation() {
        return Preparation;
    }

    public void setIncrementPreparation() {
        Preparation.setValue(Preparation.getValue() + 1);
    }

    public void setDecrementPreparation() {
        if(Preparation.getValue() != 0) {
            Preparation.setValue(Preparation.getValue() - 1);
        }
    }

    public void setPrep(int prep) {
        Preparation.setValue(prep);
    }

    public MutableLiveData<Integer> getWorkTime() {
        return WorkTime;
    }

    public void setIncrementWorkTime( ) {
        WorkTime.setValue(WorkTime.getValue() + 1);
    }

    public void setDecrementWorkTime() {
        if(WorkTime.getValue() != 0) {
            WorkTime.setValue(WorkTime.getValue() - 1);
        }
    }

    public void setWork(int work) {
        WorkTime.setValue(work);
    }

    public MutableLiveData<Integer> getRestTime() {
        return RestTime;
    }

    public void setIncrementRestTime() {
        RestTime.setValue(RestTime.getValue() + 1);
    }

    public void setDecrementRestTime() {
        if(RestTime.getValue() != 0) {
            RestTime.setValue(RestTime.getValue() - 1);
        }
    }

    public void setRest(int rest) {
        RestTime.setValue(rest);
    }

    public MutableLiveData<Integer> getRestSets() {
        return RestSets;
    }

    public void setIncrementRestSets() {
        RestSets.setValue(RestSets.getValue() + 1);
    }

    public void setDecrementRestSets() {
        if(RestSets.getValue() != 0) {
            RestSets.setValue(RestSets.getValue() - 1);
        }
    }

    public void setRestSets(int calm) {
        RestSets.setValue(calm);
    }

    public MutableLiveData<Integer> getCycles() {
        return Cycles;
    }

    public void setIncrementCycle() {
        Cycles.setValue(Cycles.getValue() + 1);
    }

    public void setDecrementCycle() {
        if(Cycles.getValue() != 0) {
            Cycles.setValue(Cycles.getValue() - 1);
        }
    }

    public void setCycle(int cycle) {
        Cycles.setValue(cycle);
    }

    public MutableLiveData<Integer> getSets() {
        return Sets;
    }

    public void setSets(int sets) {
        Sets.setValue(sets);
    }

    public void setIncrementSets() {
        Sets.setValue(Sets.getValue() + 1);
    }

    public void setDecrementSets() {
        if(Sets.getValue() != 1) {
            Sets.setValue(Sets.getValue() - 1);
        }
    }
}

