package com.example.carlaundry.view.Login;

import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPresenterTest {
    private LoginPresenter presenter;
    private LoginViewStub view;

    @Before
    public void setUp() {
        view = new LoginViewStub();
        presenter = new LoginPresenter(view);

    }

    /**
     * Tests the case of verifying an admin account.
     */
    @Test
    public void verifyAdmin() {
        presenter.verifyUser(
                "admin@mail.com"
        );

        Assert.assertEquals("Επιτυχημένη είσοδος ως διαχειριστής", view.getLog());
    }

    /**
     * Tests the case of verifying a cleaning stuff member account.
     */
    @Test
    public void verifyCleaningStuffMember() {
        String aCleanersEmail = CleaningStuffDAO.getCleaningStuffStringsList().get(0);
        presenter.verifyUser(
                aCleanersEmail
        );

        Assert.assertEquals("Επιτυχημένη είσοδος ως καθαριστής", view.getLog());
    }
}
