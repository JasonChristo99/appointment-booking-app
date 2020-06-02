package com.example.carlaundry.view.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.time.LocalDate;

public  class CustomersViewEdit extends AppCompatActivity implements CustomersView, Serializable {
    private CustomersPresenter customersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_view_edit);
        customersPresenter = new CustomersPresenter(this);

        // Get email from Intent Extras
        final String stringmail = getIntent().getStringExtra("email");
        if (stringmail.equals("new")){

        }else{

        }
        // Initialize customer details
        EmailAddress mail = new EmailAddress(stringmail);
        final Customer customer = CustomersDAO.find(mail);
        String name = customer.getFirstName();
        String sirname = customer.getLastName();
        EmailAddress email = customer.getEmailAddress();
        String telephone = customer.getTelNoAsString();
        LocalDate regDate = customer.getRegistrationDate();
        final EditText nameEditable = findViewById(R.id.NameEditable);
        final EditText sirnameEditable = findViewById(R.id.SirnameEditable);
        final EditText emailEditable = findViewById(R.id.EmailEditable);
        final EditText telephoneEditable = findViewById(R.id.TelephoneEditable);
        EditText regDateNotEditable = findViewById(R.id.RegDateNotEditable);

        // Set customer details to EditTexts
        nameEditable.setText(name);
        sirnameEditable.setText(sirname);
        emailEditable.setText(email.toString());
        telephoneEditable.setText(telephone);
        regDateNotEditable.setText(regDate.toString());
        regDateNotEditable.setEnabled(false);

        // Set up Cancel Button
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CustomersViewEdit.this.finish();
            }
        });

        // Set up Delete Button
        ImageButton deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customersPresenter.deleteCustomer(stringmail);
                navigateToCustomer();

            }
        });


        // Set up "Save Changes" Button
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newName = nameEditable.getText().toString();
                String newSirname = sirnameEditable.getText().toString();
                String newEmail = emailEditable.getText().toString();
                String newTelephone = telephoneEditable.getText().toString();
                customersPresenter.updateCustomer(customer, newName, newSirname, newEmail, newTelephone);
            }
        });

    }



    @Override
    public void navigateToCustomer() {
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCustomerDetails(String emailAddress) {

    }

    @Override
    public void wrongTypeOfData(){
        Toast.makeText(this, "Μη Αποδεκτά Στοιχεία", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCustomerAdd() {
        Intent intent = new Intent(this, CustomersAdd.class);
        startActivity(intent);
    }
}
