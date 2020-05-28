package com.example.carlaundry.view.CleaningStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carlaundry.R;

public class CleaningStuffActivity extends AppCompatActivity implements CleaningStuffView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff);
    }
}
