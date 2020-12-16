package com.example.timerppo.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class TimerViewModel extends ViewModel {
    private MutableLiveData<Integer> startTime;
    private MutableLiveData<String> nameAction;

    public MutableLiveData<Integer> getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime.setValue(startTime);
    }

    public MutableLiveData<String> getNameAction() {
        return nameAction;
    }

    public void setNameAction(String nameAction) {
        this.nameAction.setValue(nameAction);
    }
}
