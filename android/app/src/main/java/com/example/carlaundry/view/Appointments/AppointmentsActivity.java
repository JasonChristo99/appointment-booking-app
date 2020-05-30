package com.example.carlaundry.view.Appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.domain.Appointment;

import java.util.List;

public class AppointmentsActivity extends AppCompatActivity implements AppointmentsView, AppointmentCanceledListener {
    private AppointmentsPresenter appointmentsPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appointmentsPresenter = new AppointmentsPresenter(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        List<Appointment> aptsList = appointmentsPresenter.getPendingAppointments();
        appointmentAdapter = new AppointmentAdapter(aptsList, this);
        recyclerView.setAdapter(appointmentAdapter);
    }

    @Override
    public void onAppointmentCanceled(int aptId) {
        appointmentsPresenter.onAppointmentCanceled(aptId);
        initRecyclerAdapter();
    }

    @Override
    public void showCancelSuccess() {
        Toast.makeText(this, "Appointment canceled successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelFailed() {
        Toast.makeText(this, "Appointment cancel failed", Toast.LENGTH_SHORT).show();
    }
}
