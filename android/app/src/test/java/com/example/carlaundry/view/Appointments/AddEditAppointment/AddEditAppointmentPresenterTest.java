package com.example.carlaundry.view.Appointments.AddEditAppointment;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.CleaningType;
import com.example.carlaundry.domain.Customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.example.carlaundry.dao.Initializer.getDummyAppointment;
import static com.example.carlaundry.dao.Initializer.getDummyCleaningStuffMember;
import static com.example.carlaundry.dao.Initializer.getDummyCleaningType;
import static com.example.carlaundry.dao.Initializer.getDummyCustomer;

public class AddEditAppointmentPresenterTest {
    private AddEditAppointmentPresenter presenter;
    private AddEditAppointmentViewStub view;

    @Before
    public void setUp() {
        view = new AddEditAppointmentViewStub();
        presenter = new AddEditAppointmentPresenter(view);
        Initializer.resetAll();
    }

    /**
     * Tests the case of adding a new appointment.
     */
    @Test
    public void addNewAppointment() {
        presenter.onSubmit(
                AddEditAppointmentActivity.Mode.ADD,
                getDummyCustomer(),
                getDummyCleaningType(),
                Initializer.getDummyDate(),
                getDummyCleaningStuffMember(),
                Initializer.getDummyTime(),
                null,
                "XYZ",
                "MANUF"
        );

        Assert.assertEquals("Το ραντεβού καταχωρήθηκε επιτυχώς!", view.getLog());
    }


    /**
     * Tests the case of editing an existing appointment.
     */
    @Test
    public void editAppointment() {
        Customer cust1 = getDummyCustomer();
        cust1.add();
        CleaningStuffMember stuff1 = getDummyCleaningStuffMember();
        stuff1.hire();
        CleaningType type1 = getDummyCleaningType();
        type1.add();
        Appointment apt1 = getDummyAppointment(cust1, stuff1, type1);
        apt1.schedule();

        presenter.onSubmit(
                AddEditAppointmentActivity.Mode.EDIT,
                cust1,
                type1,
                Initializer.getDummyDate(),
                stuff1,
                Initializer.getDummyTime(),
                apt1,
                "XYZ",
                "MANUF"
        );

        Assert.assertEquals("Οι αλλαγές αποθηκεύτηκαν επιτυχώς!", view.getLog());
    }
}
