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
import android.widget.EditText;
import android.widget.Toast;

import com.mpvaitheeswaran.timeranker.R;
import com.mpvaitheeswaran.timeranker.databinding.FragmentTimerGetDataBinding;
import com.mpvaitheeswaran.timeranker.viewmodel.TimerGetDataViewModel;
import com.mpvaitheeswaran.timeranker.viewmodel.TimerGetDataViewModelFactory;

public class TimerGetDataFrag extends Fragment {
    FragmentTimerGetDataBinding binding;
    TimerGetDataViewModelFactory viewModelFactory;
    TimerGetDataViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_timer_get_data,container,false);
        viewModelFactory=new TimerGetDataViewModelFactory();
        viewModel=new ViewModelProvider(this,viewModelFactory).get(TimerGetDataViewModel.class);
        binding.setViewModel(viewModel);
        binding.start.setEnabled(false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController=Navigation.findNavController(view);

        viewModel.onStarted.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isStarted) {
                if(isStarted){
                    
                    //Passinng data to TimerFragment
                    TimerGetDataFragDirections.ActionTimerGetDataFragToTimerFragment action = TimerGetDataFragDirections
                            .actionTimerGetDataFragToTimerFragment();
                    action.setMilliSecond(viewModel.totalMilliOfInput.getValue());
                    navController.navigate(action);
                    viewModel.onStartFinished();

                }
            }


        });
        viewModel.setTime.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String time) {
                binding.inputTime.setText(time);
                if(time.equals("00h 00m 00s")){
                    //when the clear button is pressed the start button is disabled.
                    binding.start.setEnabled(false);
                }else {
                    binding.start.setEnabled(true);
                }
            }
        });
    }

}