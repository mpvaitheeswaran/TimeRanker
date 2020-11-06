package com.mpvaitheeswaran.timeranker.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mpvaitheeswaran.timeranker.R;

public class StopwatchTabViewModel extends ViewModel {
    //Encapsulated variables
    private MutableLiveData<Integer> _buttonIcon=new MutableLiveData<Integer>();
    public LiveData<Integer> buttonIcon =_buttonIcon;

    //Setter
    public void setButtonIcon(int imgId){
        _buttonIcon.setValue(imgId);
    }

    StopwatchTabViewModel(){
        _buttonIcon.setValue(R.drawable.ic_round_play_arrow_24);
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
}
