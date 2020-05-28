package com.example.carlaundry.view.AdminHome;

public class AdminHomePresenter {
    private AdminHomeView adminHomeView;

    public AdminHomePresenter(AdminHomeView adminHomeView) {
        this.adminHomeView = adminHomeView;
    }


    public void aptsBtnClicked() {
        adminHomeView.navigateToAppointments();
    }

    public void customersBtnClicked() {
        adminHomeView.navigateToCustomers();
    }

    public void cleanersBtnClicked() {
        adminHomeView.navigateToCleaners();
    }

    public void statsBtnClicked() {
        adminHomeView.navigateToStatistics();
    }
}
