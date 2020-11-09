package com.mpvaitheeswaran.timeranker.viewmodel;

import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mpvaitheeswaran.timeranker.R;

public class StopwatchTabViewModel extends ViewModel {

    //Encapsulated variables
    private MutableLiveData<Integer> _buttonIcon=new MutableLiveData<Integer>();
    public LiveData<Integer> buttonIcon =_buttonIcon;

    private MutableLiveData<Boolean> _isRunning=new MutableLiveData<Boolean>();
    public LiveData<Boolean> isRunning=_isRunning;

    private MutableLiveData<Long> _pauseOfset=new MutableLiveData<Long>();
    public LiveData<Long> pauseOfset=_pauseOfset;

    //Setter
    public void setButtonIcon(int imgId){
        _buttonIcon.setValue(imgId);
    }
    public void setIsRunning(Boolean isRunning){
        _isRunning.setValue(isRunning);
    }
    public void setPauseOfset(Long pauseOfset){
        _pauseOfset.setValue(pauseOfset);
    }


    StopwatchTabViewModel(){
        _buttonIcon.setValue(R.drawable.ic_round_play_arrow_24);
        _isRunning.setValue(false);
        _pauseOfset.setValue(0L);
    }
    public void onClickStartAndPause(){
        switch (_buttonIcon.getValue()){
            case R.drawable.ic_round_play_arrow_24:
                changeIcon();
                break;
            case R.drawable.ic_round_pause_circle_filled_24:
                changeIcon();
                break;
        }
    }

    public void onClickStop(){
        _buttonIcon.setValue(R.drawable.ic_round_stop_24);
    }

    private void changeIcon(){
        switch (_buttonIcon.getValue()){
            case R.drawable.ic_round_pause_circle_filled_24:
                _buttonIcon.setValue(R.drawable.ic_round_play_arrow_24);
                break;
            case R.drawable.ic_round_play_arrow_24:
                _buttonIcon.setValue(R.drawable.ic_round_pause_circle_filled_24);
                break;
        }
    }
    public void startStopwatch(Chronometer chronometer){
        if (!isRunning.getValue()){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOfset.getValue());
            chronometer.start();
            _isRunning.setValue(true);
        }
    }

    public void pauseStopwatch(Chronometer chronometer){
        if (isRunning.getValue()){
            chronometer.stop();
            _pauseOfset.setValue(SystemClock.elapsedRealtime()-chronometer.getBase());
            _isRunning.setValue(false);
        }
    }

    public void stopStopwatch(Chronometer chronometer){
        chronometer.setBase(SystemClock.elapsedRealtime());
        _pauseOfset.setValue(0L);
        chronometer.stop();

        //Reset the icon to Start
        _buttonIcon.setValue(R.drawable.ic_round_play_arrow_24);
    }
}
