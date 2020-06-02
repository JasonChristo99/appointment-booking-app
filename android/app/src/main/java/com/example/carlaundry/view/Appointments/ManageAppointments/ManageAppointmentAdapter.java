package com.example.carlaundry.view.Appointments.ManageAppointments;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carlaundry.R;
import com.example.carlaundry.domain.Appointment;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManageAppointmentAdapter extends RecyclerView.Adapter<ManageAppointmentAdapter.AppointmentViewHolder> {
    private List<Appointment> appointmentSet;

    private ManageAppointmentListener manageAppointmentListener;

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        public ViewGroup listItem;
        public TextView txtAptDate, txtAptHour, txtAptCustomer, txtAptCleaner, txtAptType;
        public ImageButton btnCancelApt, btnEditApt;

        public AppointmentViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtAptDate = listItem.findViewById(R.id.aptDate);
            txtAptHour = listItem.findViewById(R.id.aptHour);
            txtAptCustomer = listItem.findViewById(R.id.aptCustomer);
            txtAptCleaner = listItem.findViewById(R.id.aptCleaner);
            txtAptType = listItem.findViewById(R.id.aptType);
            btnCancelApt = listItem.findViewById(R.id.delApt);
            btnEditApt = listItem.findViewById(R.id.editApt);
        }
    }

    public ManageAppointmentAdapter(List<Appointment> appointmentSet, ManageAppointmentListener manageAppointmentListener) {
        this.appointmentSet = appointmentSet;
        this.manageAppointmentListener = manageAppointmentListener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_list_item, parent, false);

        AppointmentViewHolder vh = new AppointmentViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        final Appointment aptAtPosition = appointmentSet.get(position);

        // - replace the contents of the view with data from the dataset item at this position
        holder.txtAptDate.setText(aptAtPosition.getAptDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        holder.txtAptHour.setText(aptAtPosition.getAptDate().format(DateTimeFormatter.ofPattern("HH:mm")));
        holder.txtAptCustomer.setText(aptAtPosition.getCustomer().getEmailAddress().toString());
        holder.txtAptCleaner.setText(aptAtPosition.getStuffMember().getEmailAddress().toString());
        holder.txtAptType.setText(aptAtPosition.getCleaningType().getDescription());
        Log.d("AptAdapter", "Apt set: " + appointmentSet); //debug
        holder.btnCancelApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the cancel
                manageAppointmentListener.onAppointmentCanceled(aptAtPosition.getAptId());
                appointmentSet.remove(aptAtPosition);
            }
        });
        holder.btnEditApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify Activity of the edit
                manageAppointmentListener.onAppointmentEditPressed(aptAtPosition.getAptId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentSet.size();
    }
}
