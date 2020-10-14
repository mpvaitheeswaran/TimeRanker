package com.mpvaitheeswaran.timeranker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
public class TimerViewModelFactory implements ViewModelProvider.Factory {
    private long param;
    public TimerViewModelFactory(long param){
        this.param=param;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TimerViewModel.class)){
            return (T) new TimerViewModel(param);
        }
        else {
            throw new IllegalArgumentException("Unknown Class");
        }

    }
}
