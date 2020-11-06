package com.mpvaitheeswaran.timeranker.tabs;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.mpvaitheeswaran.timeranker.R;
import com.mpvaitheeswaran.timeranker.databinding.FragmentStopwatchTabBinding;
import com.mpvaitheeswaran.timeranker.viewmodel.StopwatchTabViewModel;
import com.mpvaitheeswaran.timeranker.viewmodel.StopwatchTabViewModelFactory;


public class StopwatchTabFrag extends Fragment {

    FragmentStopwatchTabBinding binding;
    StopwatchTabViewModelFactory stopwatchTabViewModelFactory;
    StopwatchTabViewModel stopwatchTabViewModel;
    Chronometer stopwatchChronometer;

    long pauseOfset;
    boolean running=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_stopwatch_tab,container,false);

        //ViewModel and Factory Creation{
        stopwatchTabViewModelFactory=new StopwatchTabViewModelFactory();
        stopwatchTabViewModel= new ViewModelProvider(this,stopwatchTabViewModelFactory).get(StopwatchTabViewModel.class);
        binding.setStopwatchTabViewModel(stopwatchTabViewModel);
        //ViewModel and Factory Created }

        stopwatchChronometer=binding.stopwatchChronometer;
        stopwatchTabViewModel.buttonIcon.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer imgId) {

                switch (imgId){
                    //when the icon is changed to pause (or) when user clicks play icon then Stopwatch is start.
                    case R.drawable.ic_round_pause_circle_filled_24:
                        binding.stop.setVisibility(View.VISIBLE);
                        binding.startPause.setImageResource(imgId);
                        if(!running) {
                            stopwatchChronometer.setBase(SystemClock.elapsedRealtime()-pauseOfset);
                            Log.i("StopwatchTabFrag"," SystemClock.elapsedRealtime() is "+stopwatchChronometer.getBase());
                            stopwatchChronometer.start();
                            running=true;
                        }
                        break;
                    //when the icon is changed to start (or) when user clicks pause icon then Stopwatch is pause.
                    case R.drawable.ic_round_play_arrow_24:
                        binding.startPause.setImageResource(imgId);
                        if (running){
                            stopwatchChronometer.stop();
                            Log.i("StopwatchTabFrag"," stopwatchChronometer.stop();  is executed...");
                            pauseOfset=SystemClock.elapsedRealtime()-stopwatchChronometer.getBase();
                            running=false;
                        }
                        break;
                    case R.drawable.ic_round_stop_24:
                        binding.stop.setVisibility(View.GONE);
                        stopwatchChronometer.setBase(SystemClock.elapsedRealtime());
                        pauseOfset=0;
                        stopwatchChronometer.stop();
                        //Reset the icon to Start
                        stopwatchTabViewModel.setButtonIcon(R.drawable.ic_round_play_arrow_24);
                }
            }
        });



        return binding.getRoot();
    }
}