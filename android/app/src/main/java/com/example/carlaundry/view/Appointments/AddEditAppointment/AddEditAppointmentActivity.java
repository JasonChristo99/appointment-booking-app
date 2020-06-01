package com.example.carlaundry.view.Appointments.AddEditAppointment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.CleaningTypesDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.CleaningType;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.EmailAddress;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class AddEditAppointmentActivity extends AppCompatActivity implements AddEditAppointmentView {
    AddEditAppointmentPresenter addEditAppointmentPresenter;

    Mode mode;
    Customer customer;
    CleaningStuffMember stuffMember;
    CleaningType cleanType;
    LocalDate date;
    LocalTime time;

    Appointment editedAppointment;

    public enum Mode {ADD, EDIT}

    private Spinner spinnerCustomer, spinnerType, spinnerCleaner;
    private Button btnNewCustomer, btnSetDate, btnSetTime, btnSubmit;
    TextInputLayout txtInputCarNo, txtInputCarManu;
    private TextView txtDate, txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_appointments);

        addEditAppointmentPresenter = new AddEditAppointmentPresenter(this);

        spinnerCleaner = findViewById(R.id.spinnerCleaner);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        spinnerType = findViewById(R.id.spinnerType);
        btnNewCustomer = findViewById(R.id.btnAddCustomer);
        btnSetDate = findViewById(R.id.btnDate);
        btnSetTime = findViewById(R.id.btnTime);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtInputCarNo = findViewById(R.id.edtCarRegNo);
        txtInputCarManu = findViewById(R.id.edtCarManuf);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);

        initGeneral();

        Intent intent = getIntent();
        int extraAptId = intent.getIntExtra("aptId", -1);
        if (extraAptId != -1) {
            mode = Mode.EDIT;
            Log.d("AddEdit", "Mode = EDIT");
            editedAppointment = AppointmentsDAO.find(extraAptId);
            initEditMode(editedAppointment);
        } else {
            mode = Mode.ADD;
            Log.d("AddEdit", "Mode = ADD");
            initAddMode();
        }
    }

    private void initGeneral() {
        // set customers adapter
        final ArrayAdapter custAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CustomersDAO.getCustomersStringsList());
        custAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomer.setAdapter(custAd);

        // set cleaners adapter
        final ArrayAdapter clAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CleaningStuffDAO.getCleaningStuffStringsList());
        clAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCleaner.setAdapter(clAd);

        // set cleaning types adapter
        final ArrayAdapter typAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CleaningTypesDAO.getCleaningTypesStringsList());
        typAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typAd);

        // set cleaning types listener
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cleanType = CleaningTypesDAO.find(CleaningTypesDAO.getCleaningTypesStringsList().get(position));
                Log.d("ItemSelected", "Type selected " + cleanType.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cleanType = null;
            }
        });

        // set date picker
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDateBtnPressed();
            }
        });

        // set time picker
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeBtnPressed();
            }
        });

        // set submit logic to button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                addEditAppointmentPresenter.onSubmit();
            }
        });
    }

    private void initAddMode() {
        // set customers listener
        spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customer = CustomersDAO.find(new EmailAddress(CustomersDAO.getCustomersStringsList().get(position)));
                Log.d("ItemSelected", "Customer selected " + customer.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customer = null;
            }
        });

        // set cleaners listener
        spinnerCleaner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stuffMember = CleaningStuffDAO.find(new EmailAddress(CleaningStuffDAO.getCleaningStuffStringsList().get(position)));
                Log.d("ItemSelected", "Cleaner selected " + stuffMember.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stuffMember = null;
            }
        });


    }

    private void onDateBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = LocalDate.of(year, month+1, dayOfMonth);
                Log.d("DateSet", "Date set to " + date);
                txtDate.setText("Ημερομηνία: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }, thisYear, thisMonth, thisDay);

        datePickerDialog.show();
    }

    private void onTimeBtnPressed() {
        Calendar calendar = Calendar.getInstance();
        int thisHour = calendar.get(Calendar.HOUR);
        int thisMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time = LocalTime.of(hourOfDay, minute);
                Log.d("TimeSet", "Time set to " + time);
                txtTime.setText("Ώρα: " + time.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        }, thisHour, thisMinute, true);

        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initEditMode(Appointment editedAppointment) {
        // set customer
        int fixedPos = CustomersDAO.getCustomersStringsList().indexOf(editedAppointment.getCustomer().getEmailAddress().toString());
        Log.d("Pos", "List = " + CustomersDAO.getCustomersStringsList() + " Pos = " + fixedPos + " of " + editedAppointment.getCustomer().getEmailAddress().toString());
        spinnerCustomer.setSelection(fixedPos);
        customer = editedAppointment.getCustomer();
        Log.d("EditedApt", customer.toString());
        spinnerCustomer.setEnabled(false);
        // disable new customer button
        btnNewCustomer.setEnabled(false);

        // set cleaner
        fixedPos = CleaningStuffDAO.getCleaningStuffStringsList().indexOf(editedAppointment.getStuffMember().getEmailAddress().toString());
        Log.d("Pos", "List = " + CleaningStuffDAO.getCleaningStuffStringsList() + " Pos = " + fixedPos);
        spinnerCleaner.setSelection(fixedPos);
        stuffMember = editedAppointment.getStuffMember();
        Log.d("EditedApt", stuffMember.toString());
        spinnerCleaner.setEnabled(false);

        // set type
        fixedPos = CleaningTypesDAO.getCleaningTypesStringsList().indexOf(editedAppointment.getCleaningType().toString());
        Log.d("Pos", "List = " + CleaningTypesDAO.getCleaningTypesStringsList() + " Pos = " + fixedPos);
        spinnerType.setSelection(fixedPos);
        cleanType = editedAppointment.getCleaningType();
        Log.d("EditedApt", cleanType.toString());

        // set date text
        date = editedAppointment.getAptDate().toLocalDate();
        txtDate.setText("Ημερομηνία: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // set time text
        time = editedAppointment.getAptDate().toLocalTime();
        txtTime.setText("Ώρα: " + time.format(DateTimeFormatter.ofPattern("HH:mm")));

        // set car details
        txtInputCarNo.getEditText().setText(editedAppointment.getCar().getRegistrationNumber());
        txtInputCarManu.getEditText().setText(editedAppointment.getCar().getManufacturer());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resultReady() {
        finish();
    }

}
