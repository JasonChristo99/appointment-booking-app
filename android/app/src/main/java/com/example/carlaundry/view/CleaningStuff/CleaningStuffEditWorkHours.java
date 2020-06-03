package com.example.carlaundry.view.CleaningStuff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.WorkHours;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;

import java.time.DayOfWeek;
import java.util.Map;

public class CleaningStuffEditWorkHours extends AppCompatActivity   {

    TextView mondayWorkHours;
    TextView tuesdayWorkHours;
    TextView wednesdayWorkHours;
    TextView thursdayWorkHours;
    TextView fridayWorkHours;
    TextView saturdayWorkHours;
    TextView sundayWorkHours;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff_edit_work_hours);

        // Find Cleaner
        final String stringmail = getIntent().getStringExtra("email");
        EmailAddress emailAddress = new EmailAddress(stringmail);
        CleaningStuffMember cleaner = CleaningStuffDAO.find(emailAddress);
        WorkHours workHours = cleaner.getWorkHours();

        //Get and display work hours
        mondayWorkHours = findViewById(R.id.mondayWorkHours);
        tuesdayWorkHours = findViewById(R.id.tuesdayWorkHours);
        wednesdayWorkHours = findViewById(R.id.wednesdayWorkHours);
        thursdayWorkHours = findViewById(R.id.thursdayWorkHours);
        fridayWorkHours = findViewById(R.id.fridayWorkHours);
        saturdayWorkHours = findViewById(R.id.saturdayWorkHours);
        sundayWorkHours = findViewById(R.id.sundayWorkHours);

        setWorkHours(workHours.getWorkHoursMap());

        // Set Edit Buttons


        ImageButton editMonday = findViewById(R.id.editMonday);
        ImageButton editTuesday = findViewById(R.id.editTuesday);
        ImageButton editWednesday = findViewById(R.id.editWednesday);
        ImageButton editThursday = findViewById(R.id.editThursday);
        ImageButton editFriday = findViewById(R.id.editFriday);
        ImageButton editSaturday = findViewById(R.id.editSaturday);
        ImageButton editSunday = findViewById(R.id.editSunday);
        Button backButton = findViewById(R.id.backButton);
        editMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",1);
                startActivity(intent);
            }
        });
        editTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",2);
                startActivity(intent);
            }
        });
        editWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",3);
                startActivity(intent);
            }
        });
        editThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",4);
                startActivity(intent);
            }
        });
        editFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",5);
                startActivity(intent);
            }
        });
        editSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",6);
                startActivity(intent);
            }
        });
        editSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffAddHours.class);
                intent.putExtra("email", stringmail);
                intent.putExtra("day",7);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningStuffViewEdit.class);
                intent.putExtra("email", stringmail);
                startActivity(intent);
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



}
