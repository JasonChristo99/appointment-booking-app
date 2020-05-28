package com.example.carlaundry.view.AdminHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.carlaundry.R;
import com.example.carlaundry.view.Appointments.AppointmentsActivity;
import com.example.carlaundry.view.CleaningStuff.CleaningStuffActivity;
import com.example.carlaundry.view.Customers.CustomersActivity;
import com.example.carlaundry.view.Statistics.StatisticsActivity;

public class AdminHomeActivity extends AppCompatActivity implements AdminHomeView {
    AdminHomePresenter adminHomePresenter;

    ImageButton aptsBtn, customersBtn, cleanersBtn, statsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminHomePresenter = new AdminHomePresenter(this);

        aptsBtn = findViewById(R.id.aptsBtn);
        customersBtn = findViewById(R.id.custmersBtn);
        cleanersBtn = findViewById(R.id.cleanersBtn);
        statsBtn = findViewById(R.id.statsBtn);

        aptsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomePresenter.aptsBtnClicked();
            }
        });
        customersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomePresenter.customersBtnClicked();
            }
        });
        cleanersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomePresenter.cleanersBtnClicked();
            }
        });
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomePresenter.statsBtnClicked();
            }
        });
    }

    @Override
    public void navigateToAppointments() {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCustomers() {
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCleaners() {
        Intent intent = new Intent(this, CleaningStuffActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToStatistics() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
