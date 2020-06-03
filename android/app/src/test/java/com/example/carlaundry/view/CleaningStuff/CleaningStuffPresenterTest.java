package com.example.carlaundry.view.CleaningStuff;

import android.view.animation.LinearInterpolator;

import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.services.StatisticsCalculatorService;
import com.example.carlaundry.view.Statistics.StatisticsPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CleaningStuffPresenterTest {
    private CleaningStuffPresenter presenter;
    private CleaningStuffViewStub view;

    @Before
    public void setUp() {
        view = new CleaningStuffViewStub();
        presenter = new CleaningStuffPresenter(view);
        Initializer.resetAll();
    }

    /**
     * Tests the case of adding a cleaning stuff member.
     */
    @Test
    public void addCleaningStuffMember() {
        presenter.addCleaningStuff(
                "FirstN", "LastN", "6999999999", "x@y.z", "123456789"
        );
        Assert.assertEquals("ok", view.getLog());
    }

    /**
     * Tests the case of editing a cleaning stuff member.
     */
    @Test
    public void editCleaningStuffMember() {
        CleaningStuffMember cleaner = Initializer.getDummyCleaningStuffMember();
        cleaner.hire();

        presenter.updateCleaningStuff(
                cleaner, "FirstN", "LastN", "6999999999", "x@y.z", cleaner.getEmailAddress().toString(), "123456789"
        );
        Assert.assertEquals("ok", view.getLog());
    }

    /**
     * Tests the case of deleting a cleaning stuff member.
     */
    @Test
    public void deleteCleaningStuffMember() {
        CleaningStuffMember cleaner = Initializer.getDummyCleaningStuffMember();
        cleaner.hire();

        presenter.deleteCleaner(cleaner.getEmailAddress().toString());
        Assert.assertNull(CleaningStuffDAO.find(cleaner.getEmailAddress()));
    }

}
