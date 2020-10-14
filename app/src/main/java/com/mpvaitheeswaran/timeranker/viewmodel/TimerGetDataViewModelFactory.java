package com.mpvaitheeswaran.timeranker.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TimerGetDataViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TimerGetDataViewModel.class)){
            return (T) new TimerGetDataViewModel();
        }
        else {
            throw new IllegalArgumentException("Unknown Class");
        }
    }
}
