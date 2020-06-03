package com.example.carlaundry.view.Statistics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.services.StatisticsCalculatorService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView {
    StatisticsPresenter statisticsPresenter;

    LocalDate startDate, endDate;
    LocalTime startTime, endTime;

    Spinner spinnerStatType;
    ImageButton btnStartDate, btnStartTime, btnEndDate, btnEndTime;
    Button btnCalculate;
    TextView txtStartDate, txtStartTime, txtEndDate, txtEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statisticsPresenter = new StatisticsPresenter(this);

        spinnerStatType = findViewById(R.id.spinnerStatType);
        btnStartDate = findViewById(R.id.imgStatStartDate);
        btnStartTime = findViewById(R.id.imgStatStartHour);
        btnEndDate = findViewById(R.id.imgStatEndDate);
        btnEndTime = findViewById(R.id.imgStatEndHour);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtStartDate = findViewById(R.id.txtStatStartDate);
        txtStartTime = findViewById(R.id.txtStatStartHour);
        txtEndDate = findViewById(R.id.txtStatEndDate);
        txtEndTime = findViewById(R.id.txtStatEndHour);

        final ArrayAdapter typeAdapt = new ArrayAdapter(this, android.R.layout.simple_spinner_item, StatisticsCalculatorService.statisticTypes);
        typeAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatType.setAdapter(typeAdapt);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartDateBtnPressed();
            }
        });

        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartTimeBtnPressed();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEndDateBtnPressed();
            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEndTimeBtnPressed();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDate == null || startTime == null || endDate == null || endTime == null) {
                    showErrorMessage("Πρέπει να συμπληρώσετε όλες τις ημερομηνίες και ώρες!");
                    return;
                }
                statisticsPresenter.calculate(LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime), spinnerStatType.getSelectedItemPosition());
            }
        });
    }

    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStatisticResult(double result) {
        Toast.makeText(this, "Αποτέλεσμα: " + result, Toast.LENGTH_LONG).show();
    }

    private void onStartDateBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startDate = LocalDate.of(year, month + 1, dayOfMonth);
                Log.d("DateSet", "Date set to " + startDate);
                txtStartDate.setText(startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }, thisYear, thisMonth, thisDay);

        datePickerDialog.show();
    }

    private void onStartTimeBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisHour = calendar.get(Calendar.HOUR);
        int thisMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                startTime = LocalTime.of(hourOfDay, minute);
                Log.d("TimeSet", "Time set to " + startTime);
                txtStartTime.setText(startTime.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        }, thisHour, thisMinute, true);

        timePickerDialog.show();
    }

    private void onEndDateBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endDate = LocalDate.of(year, month + 1, dayOfMonth);
                Log.d("DateSet", "Date set to " + endDate);
                txtEndDate.setText(endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }, thisYear, thisMonth, thisDay);

        datePickerDialog.show();
    }

    private void onEndTimeBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisHour = calendar.get(Calendar.HOUR);
        int thisMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                endTime = LocalTime.of(hourOfDay, minute);
                Log.d("TimeSet", "Time set to " + endTime);
                txtEndTime.setText(endTime.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        }, thisHour, thisMinute, true);

        timePickerDialog.show();
    }
}
