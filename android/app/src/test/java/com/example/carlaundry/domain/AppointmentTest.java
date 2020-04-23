package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointmentTest {
    @Before
    public void setUp() {
        Initializer.resetAll();
    }

    @Test
    public void testScheduleForAvailableStuff() {
    }

    @Test
    public void testScheduleForNonAvailableStuff() {
    }

    @Test
    public void testScheduleForNonWorkingStuff() {
    }

}
