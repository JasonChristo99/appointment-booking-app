package com.example.carlaundry;

import com.example.carlaundry.domain.Appointment;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class AppointmentTest {
    @Test
    public void exampleTest() {
        Appointment apt = new Appointment();
        Calendar dt = apt.getDate();
        Assert.assertEquals(dt.get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}
