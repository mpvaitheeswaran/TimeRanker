package com.mpvaitheeswaran.timeranker;

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
import android.widget.Toast;

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
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_timer_get_data,container,false);
        viewModelFactory=new TimerGetDataViewModelFactory();
        viewModel=new ViewModelProvider(this,viewModelFactory).get(TimerGetDataViewModel.class);
        binding.setViewModel(viewModel);


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
                    int second=Integer.parseInt(binding.secondInput.getText().toString());
                    long milliSecond=second*1000;
                    Toast.makeText(getContext(), "Started is clicked...", Toast.LENGTH_SHORT).show();
                    //Passinng data to TimerFragment
                    TimerGetDataFragDirections.ActionTimerGetDataFragToTimerFragment action=TimerGetDataFragDirections
                            .actionTimerGetDataFragToTimerFragment();
                    action.setMilliSecond(milliSecond);
                    navController.navigate(action);
                    viewModel.onStartFinished();
                }
            }
        });
    }

}