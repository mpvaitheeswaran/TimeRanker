package com.mpvaitheeswaran.timeranker.screens;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mpvaitheeswaran.timeranker.R;
import com.mpvaitheeswaran.timeranker.adapters.ViewPagerAdapter;
import com.mpvaitheeswaran.timeranker.databinding.FragmentHomeBinding;
import com.mpvaitheeswaran.timeranker.tabs.StopwatchTabFrag;
import com.mpvaitheeswaran.timeranker.tabs.TimerTabFrag;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    FragmentHomeBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        tabLayout=binding.tabLayout;
        viewPager=binding.viewPager;

        ArrayList<Fragment> fragments= new ArrayList<Fragment>();
        fragments.add(new TimerTabFrag());
        fragments.add(new StopwatchTabFrag());

        viewPagerAdapter=new ViewPagerAdapter(fragments,
                requireActivity().getSupportFragmentManager(),
                getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
            //TODO Implement tab Something
            switch (position){
                case 0:
                    tab.setText("Timer");
                    break;
                case 1:
                    tab.setText("Stopwatch");
                    break;
            }
        }).attach();
        return binding.getRoot();
    }
}