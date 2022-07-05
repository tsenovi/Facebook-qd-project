package org.vso.views.contracts;

public interface ProfileView {
    void show(String text);

    String getUserTextInput();

    Integer getUserDecimalInput();

    void showProfileOptions();

    void showOptionError();

    void onUserLogoutSelected();
}
