package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningTypesDAO;
import com.example.carlaundry.util.StatisticType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {
    private StatisticType type;
    private LocalDateTime start;
    private LocalDateTime end;
    private double value;

    public Statistic(StatisticType type, LocalDateTime start, LocalDateTime end) {
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public Statistic(StatisticType type, double value) {
        this.type = type;
        this.value = value;
    }

    public StatisticType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public double calculate() {
        value = 0;
        switch (type) {
            case TOTAL_APPOINTMENTS_COMPLETE:
                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
                    if (appointment.getAppointmentState().equals(AppointmentState.COMPLETE)) {
                        value++;
                    }
                }
                break;
            case TOTAL_SALES:
                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
                    if (appointment.getAppointmentState().equals(AppointmentState.COMPLETE)) {
                        value += appointment.getCleaningType().getCost().getValue();
                    }
                }
                break;
            case CANCEL_RATE:
                int complete = 0;
                int canceled = 0;
                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
                    if (appointment.getAppointmentState().equals(AppointmentState.COMPLETE)) {
                        complete++;
                    } else if (appointment.getAppointmentState().equals(AppointmentState.CANCELED)) {
                        canceled++;
                    }
                }
                value = 1.0 * canceled / (complete + canceled);
                break;
//            case MOST_POPULAR_CLEANING_TYPE:
//                List<Integer> cleaningTypes = new ArrayList<>();
//                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
//                    cleaningTypes.add(appointment.getCleaningType().getId());
//                }
//                Collections.sort(cleaningTypes);
//
//                int count = 1, tempCount;
//                int popular = cleaningTypes.get(0);
//                int temp = 0;
//                for (int i = 0; i < (cleaningTypes.size() - 1); i++) {
//                    temp = cleaningTypes.get(i);
//                    tempCount = 0;
//                    for (int j = 1; j < cleaningTypes.size(); j++) {
//                        if (temp == cleaningTypes.get(j))
//                            tempCount++;
//                    }
//                    if (tempCount > count) {
//                        popular = temp;
//                        count = tempCount;
//                    }
//                }
//                value = popular;
//                break;
        }
        return value;
    }
}
