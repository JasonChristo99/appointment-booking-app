package com.example.carlaundry.services;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.AppointmentState;

import java.time.LocalDateTime;

public class TotalSalesCalculator implements StatisticCalculator {
    @Override
    public double calculate(LocalDateTime startDate, LocalDateTime endDate) {
        double value = 0;
        for (Appointment appointment : AppointmentsDAO.getAppointments()) {
            if (appointment.getAptState().equals(AppointmentState.COMPLETE)) {
                value += appointment.getCleaningType().getCost().getValue();
            }
        }
        return value;
    }
}
