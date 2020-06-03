package com.example.carlaundry.view.CleaningStuff;

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
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.view.Customers.CustomersAdd;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Map;

public class CleaningStuffActivity extends AppCompatActivity implements CleaningStuffView {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff);

        ConstraintLayout constraintLayout1 = (ConstraintLayout) findViewById(R.id.constraint1);
        createCleaners((HashSet<CleaningStuffMember>) CleaningStuffDAO.getCleaningStuffMembers());
        Button newCleaningStuff = findViewById(R.id.newCleaningStuff);
        newCleaningStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagivateToCleaningStuffAdd();
            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createCleaners(HashSet<CleaningStuffMember> cleaningStuff) {


        for (final CleaningStuffMember cleaner : cleaningStuff) {
            Button cleaningStuffButton = new Button(this);
            cleaningStuffButton.setText(String.format("%s  %s", cleaner.getFirstName(), cleaner.getLastName()));
            cleaningStuffButton.setBackgroundColor(Color.TRANSPARENT);
            cleaningStuffButton.setTextColor(Color.BLUE);
            LinearLayout ll = findViewById(R.id.linearlayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 10, 0, 0);
            ll.addView(cleaningStuffButton, lp);
            cleaningStuffButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress = cleaner.getEmailAddress().toString();
                    navigateToCleaningStuffDetails(emailAddress);
                }
            });
        }
    }

    private void navigateToCleaningStuffDetails(String emailAddress) {
        Intent intent = new Intent(this, CleaningStuffViewEdit.class);
        intent.putExtra("email", emailAddress);
        startActivity(intent);
    }

    @Override
    public void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours) {

    }

    @Override
    public void navigateToCleaningStuffActivity() {

    }

    @Override
    public void nagivateToCleaningStuffViewEdit() {

    }

    @Override
    public void nagivateToCleaningStuffAdd() {
        Intent intent = new Intent(this, CleaningStuffAdd.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCleaningStuffEditWorkHours(String stringmail) {

    }

    @Override
    public void wrongData() {

    }

}
