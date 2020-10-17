package com.mpvaitheeswaran.timeranker.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerGetDataViewModel extends ViewModel {
    //Encapsulated Variables
    private MutableLiveData<Boolean> _onStarted =new MutableLiveData<Boolean>();
    public LiveData<Boolean> onStarted=_onStarted;

    //Default Settings Constructor
    TimerGetDataViewModel(){
        _onStarted.setValue(false);
    }
    public void onStart(){
        _onStarted.setValue(true);
    }
    public void onStartFinished(){
        _onStarted.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("TimerGetDataViewModel","onCleared() is Called");
    }

}
