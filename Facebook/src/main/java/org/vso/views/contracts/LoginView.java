package org.vso.views.contracts;

import org.vso.presenters.implementations.LoginPresenterImpl;

public interface LoginView {
    void getUserLoginInfo(LoginPresenterImpl.UserLoginListener listener);

    void showLoginSuccessful();

    void showLoginFailed();
}
