package com.example.carlaundry.view.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carlaundry.R;

public class CustomersActivity extends AppCompatActivity implements CustomersView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
    }
}
