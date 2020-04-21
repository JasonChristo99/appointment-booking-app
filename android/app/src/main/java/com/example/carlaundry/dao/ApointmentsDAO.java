package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Appointment;

import java.util.HashSet;
import java.util.Set;

public class ApointmentsDAO {
    private Set<Appointment> appointments = new HashSet<>();

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    //TODO
    public static boolean addAppointment(Appointment appointment) {
        return false;
    }

    //TODO
    public static boolean removeAppointment(int aptId) {
        return false;
    }
}
