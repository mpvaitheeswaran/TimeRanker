package com.mpvaitheeswaran.timeranker.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimerGetDataViewModel extends ViewModel {
    static final int INITIAL_CURSOR = -1;
    static final int MINUTE =60;
    static final int SECOND =60;
    static final int MILLISECOND =1000;

    List<Character> inputTime=new ArrayList<Character>();
    String time="";
    int cursorIndex =INITIAL_CURSOR;

    //Encapsulated Variables
    private MutableLiveData<Boolean> _onStarted =new MutableLiveData<Boolean>();
    public LiveData<Boolean> onStarted=_onStarted;

    private MutableLiveData<String> _setTime =new MutableLiveData<String>();
    public LiveData<String> setTime=_setTime;

    private MutableLiveData<Long> _totalMilliOfInput=new MutableLiveData<Long>();
    public LiveData<Long> totalMilliOfInput = _totalMilliOfInput;


    //Default Settings Constructor
    TimerGetDataViewModel(){
        _onStarted.setValue(false);
        _setTime.setValue("00h 00m 00s");
        inputTime.add(0,'0');
        inputTime.add(1,'0');
        inputTime.add(2,'h');
        inputTime.add(3,' ');
        inputTime.add(4,'0');
        inputTime.add(5,'0');
        inputTime.add(6,'m');
        inputTime.add(7,' ');
        inputTime.add(8,'0');
        inputTime.add(9,'0');
        inputTime.add(10,'s');
        resetTime();

    }

    private void updateTime() {
        for (int index=0;index<inputTime.size();index++){
            time+=inputTime.get(index);
        }
        _setTime.setValue(time);
        time="";
    }
    void setValue(char value){
        if(cursorIndex ==9){
            cursorIndex = INITIAL_CURSOR;
        }
        else {
            //This block is bug zone solved ArrayIndexOutOfBound Exception using nested if();
            cursorIndex++;
            if(!(cursorIndex ==6|| cursorIndex ==2)){
                inputTime.set(cursorIndex,value);
            }else {
                cursorIndex = cursorIndex +2;
                inputTime.set(cursorIndex,value);
            }
        }
        updateTime();
    }
    private void resetTime(){
        inputTime.set(0,'0');
        inputTime.set(1,'0');
        inputTime.set(2,'h');
        inputTime.set(3,' ');
        inputTime.set(4,'0');
        inputTime.set(5,'0');
        inputTime.set(6,'m');
        inputTime.set(7,' ');
        inputTime.set(8,'0');
        inputTime.set(9,'0');
        inputTime.set(10,'s');
        updateTime();
        cursorIndex = INITIAL_CURSOR;

    }

    public void onStart(){
        int hours= Integer.parseInt(""+inputTime.get(0)+inputTime.get(1));
        int minutes =Integer.parseInt(""+inputTime.get(4)+inputTime.get(5));
        int seconds=Integer.parseInt(""+inputTime.get(8)+inputTime.get(9));

        //Time conversion to MilliSecond.
        long hourToMili=(long) ((hours*MINUTE)*SECOND)*MILLISECOND;
        long minuteToMilli=(long) (minutes*SECOND)*MILLISECOND;
        long secondToMilli=(long) seconds*MILLISECOND;
        long totalMilli = hourToMili + minuteToMilli + secondToMilli;
        _totalMilliOfInput.setValue(totalMilli);
        _onStarted.setValue(true);

    }
    public void onStartFinished(){
        _onStarted.setValue(false);
    }


    //Click Event methods for numpad.
    public void onClickZero(){
        setValue('0');
    }

    public void onClickOne(){
        setValue('1');

    }
    public void onClickTwo(){
        setValue('2');
    }
    public void onClickThree(){
        setValue('3');
    }
    public void onClickFour(){
        setValue('4');
    }
    public void onClickFive(){
        setValue('5');
    }
    public void onClickSix(){
        setValue('6');
    }
    public void onClickSeven(){
        setValue('7');
    }
    public void onClickEight(){
        setValue('8');
    }
    public void onClickNine(){
        setValue('9');
    }
    public void onClickClear(){
        resetTime();
    }




    @Override
    protected void onCleared() {
        super.onCleared();

    }

}
