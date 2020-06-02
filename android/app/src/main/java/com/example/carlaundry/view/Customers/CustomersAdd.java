package com.example.carlaundry.view.Customers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlaundry.R;

import java.io.Serializable;

public class CustomersAdd extends AppCompatActivity implements CustomersView, Serializable {
    private CustomersPresenter customersPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_add);
        customersPresenter = new CustomersPresenter(this);

        final EditText nameEditable = findViewById(R.id.NameEditable);
        final EditText sirnameEditable = findViewById(R.id.SirnameEditable);
        final EditText emailEditable = findViewById(R.id.EmailEditable);
        final EditText telephoneEditable = findViewById(R.id.TelephoneEditable);
        TextView regDateNotEditable = findViewById(R.id.RegDateNotEditable);
        Button addButton = findViewById(R.id.addButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customersPresenter.addCustomer(nameEditable.getText().toString(), sirnameEditable.getText().toString(), emailEditable.getText().toString(), telephoneEditable.getText().toString());
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                navigateToCustomer();
            }
        });



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
    public void navigateToCustomerDetails(String emailAddress) {

    }

    @Override
    public void wrongTypeOfData(){
        Toast.makeText(this, "Μη Αποδεκτά Στοιχεία", Toast.LENGTH_SHORT).show();
    }
}
