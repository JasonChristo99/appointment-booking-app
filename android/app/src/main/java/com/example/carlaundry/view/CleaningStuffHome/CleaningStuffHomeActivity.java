package com.example.carlaundry.view.CleaningStuffHome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.Appointments.ManageAppointments.ManageAppointmentAdapter;

import java.util.List;

public class CleaningStuffHomeActivity extends AppCompatActivity implements CleaningStuffHomeView, StuffManageAppointmentListener {
    private CleaningStuffHomePresenter cleaningStuffHomePresenter;

    CleaningStuffMember loggedInStuffMember;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private StuffManageAppointmentAdapter manageAppointmentAdapter;

    String comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_stuff_home);

        cleaningStuffHomePresenter = new CleaningStuffHomePresenter(this);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("stuff_email");
        loggedInStuffMember = CleaningStuffDAO.find(new EmailAddress(extra));

        recyclerView = findViewById(R.id.recyclerViewStuff);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        List<Appointment> aptsList = cleaningStuffHomePresenter.getStuffPendingAppointments();
        Log.d("AptAdapter", "Apt set: " + aptsList); //debug
        manageAppointmentAdapter = new StuffManageAppointmentAdapter(aptsList, this);
        recyclerView.setAdapter(manageAppointmentAdapter);
    }

    @Override
    public void onAppointmentCompleted(final int aptId) {

        // show comments dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Θα θέλατε να προσθέσετε κάποιο σχόλιο;");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                comments = input.getText().toString();
//                Toast.makeText(CleaningStuffHomeActivity.this, "Το σχόλιο καταχωρήθηκε", Toast.LENGTH_SHORT).show();
                cleaningStuffHomePresenter.completeAppointment(aptId, comments);
                initRecyclerAdapter();
            }
        });
        builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cleaningStuffHomePresenter.completeAppointment(aptId, comments);
                initRecyclerAdapter();
            }
        });

        builder.show();

        // call presenter
//        cleaningStuffHomePresenter.completeAppointment(aptId, comments);
//        initRecyclerAdapter();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
