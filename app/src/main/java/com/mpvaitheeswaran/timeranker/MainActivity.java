package com.mpvaitheeswaran.timeranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mpvaitheeswaran.timeranker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
    }
}