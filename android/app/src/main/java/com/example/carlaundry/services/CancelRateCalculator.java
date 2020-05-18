package com.example.carlaundry.services;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.AppointmentState;

import java.time.LocalDateTime;

public class CancelRateCalculator implements StatisticCalculator {
    @Override
    public double calculate(LocalDateTime startDate, LocalDateTime endDate) {
        double value = 0;
        int complete = 0;
        int canceled = 0;
        for (Appointment appointment : AppointmentsDAO.getAppointments()) {
            if (appointment.getAptState().equals(AppointmentState.COMPLETE)) {
                complete++;
            } else if (appointment.getAptState().equals(AppointmentState.CANCELED)) {
                canceled++;
            }
        }
        value = 1.0 * canceled / (complete + canceled);
        return value;
    }
}
