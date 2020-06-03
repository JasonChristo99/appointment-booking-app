package com.example.carlaundry.view.CleaningStuff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.util.DailyTimePeriod;

import java.time.DayOfWeek;
import java.util.Map;

public class CleaningStuffAdd extends AppCompatActivity implements CleaningStuffView{
    private CleaningStuffPresenter cleaningStuffPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff_add);
        cleaningStuffPresenter = new CleaningStuffPresenter(this);
        final EditText nameEditable = findViewById(R.id.nameEditable);
        final EditText sirnameEditable = findViewById(R.id.sirnameEditable);
        final EditText telephoneEditable = findViewById(R.id.telephoneEditable);
        final EditText emailEditable = findViewById(R.id.emailEditable);
        final EditText afmEditable = findViewById(R.id.afmEditable);
        final Button addButton = findViewById(R.id.addButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                cleaningStuffPresenter.addCleaningStuff(nameEditable.getText().toString(), sirnameEditable.getText().toString(), telephoneEditable.getText().toString(), emailEditable.getText().toString(), afmEditable.getText().toString());
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                navigateToCleaningStuffActivity();
            }
        });
    }

    @Override
    public void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours) {

    }

    @Override
    public void navigateToCleaningStuffActivity() {
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
    public void navigateToCleaningStuffEditWorkHours(String stringmail) {

    }

    @Override
    public void wrongData() {
        Toast.makeText(this, "Μη Αποδεκτά Στοιχεία", Toast.LENGTH_SHORT).show();
    }
}
