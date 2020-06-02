package com.example.carlaundry.view.CleaningStuffHome;

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

public class StuffManageAppointmentAdapter extends RecyclerView.Adapter<StuffManageAppointmentAdapter.AppointmentViewHolder> {
    private List<Appointment> stuffAppointmentSet;

    private StuffManageAppointmentListener stuffManageAppointmentListener;

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        public ViewGroup listItem;
        public TextView txtAptDate, txtAptHour, txtAptCustomer, txtAptCleaner, txtAptType;
        public ImageButton btnCompleteApt;

        public AppointmentViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtAptDate = listItem.findViewById(R.id.aptDateStuff);
            txtAptHour = listItem.findViewById(R.id.aptHourStuff);
            txtAptCustomer = listItem.findViewById(R.id.aptCustomerStuff);
            txtAptCleaner = listItem.findViewById(R.id.aptCleanerStuff);
            txtAptType = listItem.findViewById(R.id.aptTypeStuff);
            btnCompleteApt = listItem.findViewById(R.id.completeAptStuff);
        }
    }

    public StuffManageAppointmentAdapter(List<Appointment> stuffAppointmentSet, StuffManageAppointmentListener stuffManageAppointmentListener) {
        this.stuffAppointmentSet = stuffAppointmentSet;
        this.stuffManageAppointmentListener = stuffManageAppointmentListener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stuff_appointment_list_item, parent, false);

        AppointmentViewHolder vh = new AppointmentViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        final Appointment aptAtPosition = stuffAppointmentSet.get(position);

        // - replace the contents of the view with data from the dataset item at this position
        holder.txtAptDate.setText(aptAtPosition.getAptDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        holder.txtAptHour.setText(aptAtPosition.getAptDate().format(DateTimeFormatter.ofPattern("HH:mm")));
        holder.txtAptCustomer.setText(aptAtPosition.getCustomer().getEmailAddress().toString());
        holder.txtAptCleaner.setText(aptAtPosition.getStuffMember().getEmailAddress().toString());
        holder.txtAptType.setText(aptAtPosition.getCleaningType().getDescription());
        Log.d("StuffAptAdapter", "Apt set: " + stuffAppointmentSet); //debug
        holder.btnCompleteApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the cancel
                stuffManageAppointmentListener.onAppointmentCompleted(aptAtPosition.getAptId());
                stuffAppointmentSet.remove(aptAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stuffAppointmentSet.size();
    }
}
