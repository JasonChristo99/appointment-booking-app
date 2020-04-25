package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.util.StatisticType;

import java.time.LocalDateTime;

public class Statistic {
    private StatisticType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double value;

    public Statistic(StatisticType type, LocalDateTime startDate, LocalDateTime endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double calculate() {
        value = 0;
        switch (type) {
            case TOTAL_APPOINTMENTS_COMPLETE:
                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
                    if (appointment.getAptState().equals(AppointmentState.COMPLETE)) {
                        value++;
                    }
                }
                break;
            case TOTAL_SALES:
                for (Appointment appointment : AppointmentsDAO.getAppointments()) {
                    if (appointment.getAptState().equals(AppointmentState.COMPLETE)) {
                        value += appointment.getCleaningType().getCost().getValue();
                    }
                }
                break;
            case CANCEL_RATE:
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
