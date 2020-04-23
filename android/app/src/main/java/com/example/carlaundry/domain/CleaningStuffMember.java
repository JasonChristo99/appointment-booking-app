package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CleaningStuffMember extends Person {
    private LocalDate dateHired;
    private WorkHours workHours;

    public CleaningStuffMember(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, LocalDate dateHired, WorkHours workHours) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.dateHired = dateHired;
        this.workHours = workHours;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    public Set<Appointment> getPendingAppointments() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getStuffMember()) && apt.getAppointmentState().equals(AppointmentState.PENDING)) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }


    public boolean isAvailableOn(LocalDateTime aptDate) {
        // yparxei sto wrario
        boolean worksOnDate = false;
        for (Map.Entry<DayOfWeek, DailyTimePeriod> entry : workHours.getWorkHoursMap().entrySet()) {
            if (aptDate.toLocalTime().isAfter(entry.getValue().getStartHour()) && aptDate.toLocalTime().isBefore(entry.getValue().getEndHour())) {
                worksOnDate = true;
                break;
            }
        }
        if (!worksOnDate) {
            return false;
        }
        // einai diathesimos en mesw wrariou
        for (Appointment appointment : getPendingAppointments()) {
            if (aptDate.isAfter(appointment.getAptDate()) && aptDate.isBefore(appointment.getAptDate().plus(appointment.getCleaningType().getEstimatedDuration()))) {
                return false;
            }
        }
        return true;
    }
}
