package com.mpvaitheeswaran.timeranker.viewmodel;


import android.os.CountDownTimer;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mpvaitheeswaran.timeranker.R;


public class TimerViewModel extends ViewModel {

    CountDownTimer countDownTimer;
    long milliSecond;   //this variable using for pause the timer and continues to paused time.

    //Encapsulated Variavbles
    private MutableLiveData<Integer> _buttonIcon=new MutableLiveData<Integer>();
    public LiveData<Integer> buttonIcon =_buttonIcon;

    private MutableLiveData<String> _timerText=new MutableLiveData<String>();
    public LiveData<String> timerText=_timerText;

    //set default values in this constructor
    TimerViewModel(long milliSecond){
        this.milliSecond=milliSecond;
        startCountDown(this.milliSecond);
        _buttonIcon.setValue(R.drawable.ic_round_pause_circle_filled_24);
        _timerText.setValue("Start Timer");
        Log.i("TimerViewModel","TimerViewModel() Constructor called...");

    }



    public void onClick(){
        switch (_buttonIcon.getValue()){
            case R.drawable.ic_round_play_arrow_24:
                startCountDown(milliSecond);    //When user click pause button and click play button to continues the timer.
                changeIcon();
                break;
            case R.drawable.ic_round_pause_circle_filled_24:
                stopTimer();
                changeIcon();
                break;
        }
    }

    private void startTimer() {
        //TODO Implement the start timer action
        countDownTimer.start();

    }
    private void stopTimer(){
        //TODO Implement the stop timer action
        countDownTimer.cancel();
    }
    private void changeIcon(){
        //TODO Implement the change Icon action
        switch (_buttonIcon.getValue()){
            case R.drawable.ic_round_pause_circle_filled_24:
                _buttonIcon.setValue(R.drawable.ic_round_play_arrow_24);
                break;
            case R.drawable.ic_round_play_arrow_24:
                _buttonIcon.setValue(R.drawable.ic_round_pause_circle_filled_24);
                break;
        }
    }
    private void startCountDown(long duration){
        countDownTimer= new CountDownTimer(duration, 1000) {

            public void onTick(long millisUntilFinished) {
                milliSecond=millisUntilFinished;    //when user click pause button to pause the timer
                _timerText.setValue("seconds remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                _timerText.setValue("done!");
                changeIcon();

            }
        }.start();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        stopTimer();
        Log.i("TimerViewModel","onCleared() is Called");
    }
}
