package com.example.carlaundry.view.Customers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.domain.Customer;

import java.io.Serializable;
import java.util.HashSet;

public  class CustomersActivity extends AppCompatActivity implements CustomersView,Serializable {
    private CustomersPresenter customersPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        customersPresenter = new CustomersPresenter(this);


        ConstraintLayout constraintLayout1 = (ConstraintLayout) findViewById(R.id.constraint1);
        createCustomers((HashSet<Customer>) CustomersDAO.getCustomers());
        Button newCustomer = findViewById(R.id.newCustomer);
        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCustomerAdd();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createCustomers(HashSet<Customer> customers) {


        for (final Customer customer : customers) {
            Button customerButton = new Button(this);
            customerButton.setText(String.format("%s  %s", customer.getFirstName(), customer.getLastName()));
            customerButton.setBackgroundColor(Color.TRANSPARENT);
            customerButton.setTextColor(Color.BLUE);
            LinearLayout ll = findViewById(R.id.linearlayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 10, 0, 0);
            ll.addView(customerButton, lp);
            customerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress=customer.getEmailAddress().toString();
                    navigateToCustomerDetails(emailAddress);
                }
            });
        }
    }

    @Override
    public void navigateToCustomer() {
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCustomerAdd() {
        Intent intent = new Intent(this, CustomersAdd.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCustomerDetails(String emailAddress){
        Intent intent = new Intent(getBaseContext(), CustomersViewEdit.class);
        intent.putExtra("email", emailAddress);
        startActivity(intent);
    }


    public void wrongTypeOfData() {}
}
