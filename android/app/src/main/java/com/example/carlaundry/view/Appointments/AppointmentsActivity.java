package com.example.carlaundry.view.Appointments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carlaundry.R;

public class AppointmentsActivity extends AppCompatActivity implements AppointmentsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
    }
}
