package org.vso.views;

import org.vso.presenters.ProfilePresenter;

import java.util.Scanner;

public class ProfileView {

    private final ProfilePresenter profilePresenter;

    private final Scanner scanner;

    public ProfileView() {
        this.profilePresenter = new ProfilePresenter(this);
        this.scanner = new Scanner(System.in);
        profilePresenter.onViewShown();
    }

    public void show(String text) {
        System.out.println(text);
    }

    public String getUserTextInput() {
        return scanner.nextLine();
    }

    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

    public void showProfileOptions() {
        show("""
                \tProfile Options
                1. Logout""");
    }

    public void showOptionError() {
        show("No such option!");
    }

    public void onUserLogoutSelected() {
        show("Logout Successful!");
    }
}
