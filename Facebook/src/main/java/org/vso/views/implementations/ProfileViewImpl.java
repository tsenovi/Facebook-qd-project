package org.vso.views.implementations;

import org.vso.presenters.contracts.ProfilePresenter;
import org.vso.presenters.implementations.ProfilePresenterImpl;
import org.vso.views.contracts.ProfileView;

import java.util.Scanner;

public class ProfileViewImpl implements ProfileView {

    private final ProfilePresenter profilePresenter;

    private final Scanner scanner;

    public ProfileViewImpl() {
        this.profilePresenter = new ProfilePresenterImpl(this);
        this.scanner = new Scanner(System.in);
        profilePresenter.onViewShown();
    }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public String getUserTextInput() {
        return scanner.nextLine();
    }

    @Override
    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

    @Override
    public void showProfileOptions() {
        show("""
                \tProfile Options
                1. Logout""");
    }

    @Override
    public void showOptionError() {
        show("No such option!");
    }

    @Override
    public void onUserLogoutSelected() {
        show("Logout Successful!");
    }
}
