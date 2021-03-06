package com.mpvaitheeswaran.timeranker.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpvaitheeswaran.timeranker.R;
import com.mpvaitheeswaran.timeranker.databinding.FragmentTimerBinding;
import com.mpvaitheeswaran.timeranker.viewmodel.TimerViewModel;
import com.mpvaitheeswaran.timeranker.viewmodel.TimerViewModelFactory;


public class TimerFragment extends Fragment {
    FragmentTimerBinding binding;
    TimerViewModel viewModel;
    TimerViewModelFactory timerViewModelFactory;
    //Safe arg variable
    long milliSecond;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        milliSecond= TimerFragmentArgs.fromBundle(getArguments()).getMilliSecond();
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_timer,container,false);
        timerViewModelFactory=new TimerViewModelFactory(milliSecond);
        viewModel=new ViewModelProvider(this,timerViewModelFactory).get(TimerViewModel.class);
        binding.setTimerViewModel(viewModel);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController= Navigation.findNavController(view);


        viewModel.buttonIcon.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer imgId) {
                switch (imgId){
                    //when the icon is changed to pause (or) when user clicks play icon then Stopwatch is start.
                    case R.drawable.ic_round_pause_circle_filled_24:
                        binding.stop.setVisibility(View.VISIBLE);
                        binding.startPause.setImageResource(imgId);
                        break;
                    //when the icon is changed to start (or) when user clicks pause icon then Stopwatch is pause.
                    case R.drawable.ic_round_play_arrow_24:
                        binding.startPause.setImageResource(imgId);
                        break;
                    case R.drawable.ic_round_stop_24:
                        binding.stop.setVisibility(View.GONE);
                        navController.navigate(TimerFragmentDirections.actionTimerFragmentToTimerGetDataFrag());
                }
            }
        });

        viewModel.timerText.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!(s.equals("done"))){
                    binding.timeIndicator.setText(s);
                }else {
                    navController.navigate(TimerFragmentDirections.actionTimerFragmentToTimerGetDataFrag());
                }

            }
        });
    }
}