package com.example.carlaundry.view.Customers;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

public class CustomersPresenter implements Serializable {
    private CustomersView customersView;
    private CustomersViewEdit customersViewEdit;

    public CustomersPresenter(CustomersView customersView) {
        this.customersView = customersView;
    }


    public void deleteCustomer(String stringmail) {
        EmailAddress email = new EmailAddress(stringmail);
        Customer customer = CustomersDAO.find(email);
        customer.delete();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addCustomer(String name, String sirname, String stringmail, String stringtelephone) {
        try {
            EmailAddress email = new EmailAddress(stringmail);
            TelephoneNumber telephoneNumber = new TelephoneNumber(stringtelephone);
            Customer customer = new Customer(name, sirname, telephoneNumber, email, LocalDate.now());
            customer.setFirstName(name);
            customer.setLastName(sirname);
            customer.setEmailAddress(email);
            customer.setTelNo(telephoneNumber);
            CustomersDAO.add(customer);
            customersView.navigateToCustomer();
        } catch (IllegalArgumentException e) {
            customersView.wrongTypeOfData();

        }

    }

    public void updateCustomer(Customer customer, String name, String sirname, String stringmail, String oldTelephone) {
        customersViewEdit = new CustomersViewEdit();
        try {
            EmailAddress email = new EmailAddress(stringmail);
            TelephoneNumber telephoneNumber = new TelephoneNumber(oldTelephone);
            customer.setFirstName(name);
            customer.setLastName(sirname);
            customer.setEmailAddress(email);
            customer.setTelNo(telephoneNumber);
            customersView.navigateToCustomer();

        } catch (IllegalArgumentException e) {
            customersView.wrongTypeOfData();

        }

    }


}
