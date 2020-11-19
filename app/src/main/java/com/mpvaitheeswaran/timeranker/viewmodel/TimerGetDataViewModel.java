package com.mpvaitheeswaran.timeranker.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimerGetDataViewModel extends ViewModel {

    List<Character> inputTime=new ArrayList<Character>();
    String time="";
    int index=10;
    //Encapsulated Variables
    private MutableLiveData<Boolean> _onStarted =new MutableLiveData<Boolean>();
    public LiveData<Boolean> onStarted=_onStarted;

    private MutableLiveData<String> _setTime =new MutableLiveData<String>();
    public LiveData<String> setTime=_setTime;

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
        Log.i("Time",time);
        _setTime.setValue(time);
        time="";
    }
    void setValue(char value){
        if (!(index<0)){
            if (index==10){
                index--;
                inputTime.set(index,value);

            }else if ( index ==7){
                index=index-2;
                inputTime.set(index,value);

            }
            else if ( index==3){
                index=index-2;
                inputTime.set(index,value);
            }else if(index==0){
                index=10;
            }
            else {
                index--;
                if(!(index==7||index==3)){
                    inputTime.set(index,value);
                }else {
                    index=index-2;
                    inputTime.set(index,value);
                }
            }
        }else {
            //TODO write something when the index is 0;
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
        index=10;

    }

    public void onStart(){
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
