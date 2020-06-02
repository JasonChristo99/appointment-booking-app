package com.example.carlaundry.view.Appointments.ManageAppointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.view.Appointments.AddEditAppointment.AddEditAppointmentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ManageAppointmentsActivity extends AppCompatActivity implements ManageAppointmentsView, ManageAppointmentListener {
    private ManageAppointmentsPresenter manageAppointmentsPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ManageAppointmentAdapter manageAppointmentAdapter;
    private FloatingActionButton addAptBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        manageAppointmentsPresenter = new ManageAppointmentsPresenter(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initRecyclerAdapter();

        addAptBtn = findViewById(R.id.floatingBtnAddApt);
        addAptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddAppointmentActivity();
            }
        });
    }

    private void navigateToAddAppointmentActivity() {
        Intent intent = new Intent(this, AddEditAppointmentActivity.class);
        startActivity(intent);
    }

    private void initRecyclerAdapter() {
        List<Appointment> aptsList = manageAppointmentsPresenter.getPendingAppointments();
        Log.d("AptAdapter", "Apt set: " + aptsList); //debug
        manageAppointmentAdapter = new ManageAppointmentAdapter(aptsList, this);
        recyclerView.setAdapter(manageAppointmentAdapter);
    }

    @Override
    public void onAppointmentCanceled(int aptId) {
        manageAppointmentsPresenter.onAppointmentCanceled(aptId);
        initRecyclerAdapter();
    }

    @Override
    public void onAppointmentEditPressed(int aptId) {
        Intent intent = new Intent(this, AddEditAppointmentActivity.class);
        intent.putExtra("aptId", aptId);
        startActivity(intent);
    }

    @Override
    public void showCancelSuccess() {
        Toast.makeText(this, "Appointment canceled successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelFailed() {
        Toast.makeText(this, "Appointment cancel failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerAdapter();
    }
}
