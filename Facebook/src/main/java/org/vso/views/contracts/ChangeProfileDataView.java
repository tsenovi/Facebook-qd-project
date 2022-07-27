package org.vso.views.contracts;

public interface ChangeProfileDataView {

    String getNewEmail();

    String getRepeatEmail();

    String getNewPassword();

    String getRepeatPassword();

    void showMessage(String message);
}
