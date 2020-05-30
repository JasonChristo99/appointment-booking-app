package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.AppointmentState;

import java.util.HashSet;
import java.util.Set;

public class AppointmentsDAO {
    private static Set<Appointment> appointments = new HashSet<>();

    public static Set<Appointment> getAppointments() {
        return appointments;
    }

    public static Set<Appointment> getPendingAppointments() {
        Set<Appointment> tmpSet = new HashSet<>();
        for (Appointment appointment : appointments) {
            if (appointment.getAptState().equals(AppointmentState.PENDING)) {
                tmpSet.add(appointment);
            }
        }
        return tmpSet;
    }

    public static Appointment find(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAptId() == id) {
                return appointment;
            }
        }
        return null;
    }

    public static boolean add(Appointment appointment) {
        return appointments.add(appointment);
    }

    public static boolean remove(Appointment appointment) {
        return appointments.remove(appointment);
    }

    public static void reset() {
        appointments = new HashSet<>();
    }
}
