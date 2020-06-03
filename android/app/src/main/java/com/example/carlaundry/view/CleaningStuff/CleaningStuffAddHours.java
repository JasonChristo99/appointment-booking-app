package com.example.carlaundry.view.CleaningStuff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.Customers.CustomersPresenter;

import org.w3c.dom.Text;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Map;

public class CleaningStuffAddHours extends AppCompatActivity implements CleaningStuffView {
    private CleaningStuffPresenter cleaningStuffPresenter;
    TimePickerDialog picker;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff_add_hours);
        cleaningStuffPresenter = new CleaningStuffPresenter(this);

        final String stringmail = getIntent().getStringExtra("email");
        final int day = getIntent().getIntExtra("day",0);
        EmailAddress email = new EmailAddress(stringmail);
        CleaningStuffMember cleaner = CleaningStuffDAO.find(email);
        Button fromButton = findViewById(R.id.fromButton);
        final TextView from = findViewById(R.id.from);
        Button untilButton = findViewById(R.id.untilButton);
        final TextView until = findViewById(R.id.until);
        final LocalTime[] timeFrom = new LocalTime[1];
        final LocalTime[] timeUntil = new LocalTime[1];
        final boolean[] timeFromSet = {false};
        final boolean[] timeUntilSet = {false};

        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                picker = new TimePickerDialog(CleaningStuffAddHours.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                from.setText(sHour+":"+sMinute);
                                timeFrom[0] =LocalTime.of(sHour,sMinute);
                                timeFromSet[0] =true;
                            }
                        }, hour, minutes, false);
                picker.show();
            }
        });

        untilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                picker = new TimePickerDialog(CleaningStuffAddHours.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                until.setText(sHour+":"+sMinute);
                                timeUntil[0] =LocalTime.of(sHour,sMinute);
                                timeUntilSet[0] =true;
                            }
                        }, hour, minutes, false);
                picker.show();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeFromSet[0] && timeUntilSet[0]){
                    CleaningStuffPresenter.updateHours(stringmail,timeFrom[0],timeUntil[0], day);
                    navigateToCleaningStuffEditWorkHours(stringmail);
                }else{
                    Toast.makeText(getApplicationContext(), "Πρέπει να ορίσετε ώρα αρχής και τέλους του ωραρίου.", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    public void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours) {

    }

    @Override
    public void navigateToCleaningStuffActivity() {

    }

    @Override
    public void nagivateToCleaningStuffViewEdit(){

    }

    @Override
    public void nagivateToCleaningStuffAdd() {

    }

    @Override
    public void navigateToCleaningStuffEditWorkHours(String stringmail) {
        Intent intent = new Intent(this, CleaningStuffEditWorkHours.class);
        intent.putExtra("email",stringmail);
        startActivity(intent);
    }

    @Override
    public void wrongData() {

    }
}
