package com.example.carlaundry.view.CleaningStuff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.WorkHours;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.Customers.CustomersView;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import static java.lang.String.valueOf;

public class CleaningStuffViewEdit extends AppCompatActivity implements Serializable, CleaningStuffView {

    TextView mondayWorkHours;
    TextView tuesdayWorkHours;
    TextView wednesdayWorkHours;
    TextView thursdayWorkHours;
    TextView fridayWorkHours;
    TextView saturdayWorkHours;
    TextView sundayWorkHours;

    private CleaningStuffPresenter cleaningStuffPresenter;
    private CleaningStuffView cleaningStuffView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff_view_edit);
        cleaningStuffPresenter = new CleaningStuffPresenter(this);

        // Set cancel button
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCleaningStuffActivity();
            }
        });




        final String stringmail = getIntent().getStringExtra("email");
        System.out.println(stringmail);

        // Set delete button
        final ImageButton deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleaningStuffPresenter.deleteCleaner(stringmail);
                navigateToCleaningStuffActivity();
            }
        });
        EmailAddress email = new EmailAddress(stringmail);
        final CleaningStuffMember cleaner = new CleaningStuffDAO().find(email);

        // Get cleaner data
        String name = cleaner.getFirstName();
        String lastName = cleaner.getLastName();
        String telephone = cleaner.getTelNoAsString();
        String afm = valueOf(cleaner.getAfm().getIntValue());
        LocalDate hireDate = cleaner.getDateHired();
        WorkHours workHours = cleaner.getWorkHours();

        // Set Edit Texts
        final EditText nameEditable = findViewById(R.id.nameEditable);
        final EditText sirnameEditable = findViewById(R.id.sirnameEditable);
        final EditText telephoneEditable = findViewById(R.id.telephoneEditable);
        final EditText emailEditable = findViewById(R.id.emailEditable);
        final EditText afmEditable = findViewById(R.id.afmEditable);
        EditText hireDateEditable = findViewById(R.id.hireDateEditable);

        //Get and display work hours
        mondayWorkHours = findViewById(R.id.mondayWorkHours);
        tuesdayWorkHours = findViewById(R.id.tuesdayWorkHours);
        wednesdayWorkHours = findViewById(R.id.wednesdayWorkHours);
        thursdayWorkHours = findViewById(R.id.thursdayWorkHours);
        fridayWorkHours = findViewById(R.id.fridayWorkHours);
        saturdayWorkHours = findViewById(R.id.saturdayWorkHours);
        sundayWorkHours = findViewById(R.id.sundayWorkHours);

        // Set save Button
        Button saveChanges = findViewById(R.id.saveChanges);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = nameEditable.getText().toString();
                String sirname = sirnameEditable.getText().toString();
                String telephone = telephoneEditable.getText().toString();
                String email = emailEditable.getText().toString();
                String afm = afmEditable.getText().toString();
                cleaningStuffPresenter.updateCleaningStuff(cleaner ,firstName, sirname, telephone, email, stringmail, afm);
            }
        });


        // Put values into Edit Texts
        nameEditable.setText(name);
        sirnameEditable.setText(lastName);
        telephoneEditable.setText(telephone);
        emailEditable.setText(stringmail);
        afmEditable.setText(afm);
        hireDateEditable.setText(hireDate.toString());
        hireDateEditable.setEnabled(false);

        setWorkHours(workHours.getWorkHoursMap());

        ImageButton editWorkHoursButton = findViewById(R.id.editWorkHoursButton);
        editWorkHoursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToCleaningStuffEditWorkHours(stringmail);
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours){

        if (workHours.containsKey(DayOfWeek.MONDAY)){
            mondayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.MONDAY).toString());
        }else{
            mondayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.TUESDAY)){
            tuesdayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.TUESDAY).toString());
        }else{
            tuesdayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.WEDNESDAY)){
            wednesdayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.WEDNESDAY).toString());
        }else{
            wednesdayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.THURSDAY)){
            thursdayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.THURSDAY).toString());
        }else{
            thursdayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.FRIDAY)){
            fridayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.FRIDAY).toString());
        }else{
            fridayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.SATURDAY)){
            saturdayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.SATURDAY).toString());
        }else{
            saturdayWorkHours.setText("Δεν εργάζεται.");
        }
        if (workHours.containsKey(DayOfWeek.SUNDAY)){
            sundayWorkHours.setText((CharSequence) workHours.get(DayOfWeek.SUNDAY).toString());
        }else{
            sundayWorkHours.setText("Δεν εργάζεται.");
        }

    }

    public void navigateToCleaningStuffActivity(){
        Intent intent = new Intent (this, CleaningStuffActivity.class);
        startActivity(intent);
    }

    @Override
    public void nagivateToCleaningStuffViewEdit() {

    }

    @Override
    public void nagivateToCleaningStuffAdd() {

    }

    @Override
    public void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours) {

    }

    public void navigateToCleaningStuffEditWorkHours(String stringmail)
    {
        Intent intent = new Intent (this, CleaningStuffEditWorkHours.class);
        intent.putExtra("email", stringmail);
        startActivity(intent);
    }

    @Override
    public void wrongData() {
        Toast.makeText(this, "Μη Αποδεκτά Στοιχεία", Toast.LENGTH_SHORT).show();
    }
}
