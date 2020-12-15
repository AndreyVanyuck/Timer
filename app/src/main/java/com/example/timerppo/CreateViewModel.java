package com.example.timerppo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CreateViewModel extends ViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<Integer> preparation;
    private MutableLiveData<Integer> workTime;
    private MutableLiveData<Integer> restTime;
    private MutableLiveData<Integer> restSets;
    private MutableLiveData<Integer> cycles;
    private MutableLiveData<Integer> sets;
    private MutableLiveData<Integer> color;

    public CreateViewModel(){
        name = new MutableLiveData<>("");
        preparation = new MutableLiveData<>(10);
        workTime = new MutableLiveData<>(20);
        restTime = new MutableLiveData<>(10);
        restSets = new MutableLiveData<>(10);
        cycles = new MutableLiveData<>(8);
        sets = new MutableLiveData<>(4);
        color = new MutableLiveData<>(-16777216);
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
}

