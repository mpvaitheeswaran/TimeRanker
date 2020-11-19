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
                    //Getting Data from EditText

//                    if(isEditTextNotEmpty(binding.secondInput)) {
//                        int second=Integer.parseInt(binding.secondInput.getText().toString());
//                        long milliSecond=second*1000;
//
//                        //Passinng data to TimerFragment
//                        TimerGetDataFragDirections.ActionTimerGetDataFragToTimerFragment action = TimerGetDataFragDirections
//                                .actionTimerGetDataFragToTimerFragment();
//                        action.setMilliSecond(milliSecond);
//                        navController.navigate(action);
//                        Toast.makeText(getContext(), "Started is clicked...", Toast.LENGTH_SHORT).show();
//                        viewModel.onStartFinished();
//                    }else {
//                        Toast.makeText(getContext(), "Second value required!", Toast.LENGTH_SHORT).show();
//                    }
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
    private boolean isEditTextNotEmpty(EditText editText) {
        if (editText.getText().toString().trim().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

}