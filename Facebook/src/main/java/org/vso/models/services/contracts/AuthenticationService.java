package org.vso.models.services.contracts;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.models.data.User;
import org.vso.models.dto.UserLoginDTO;
import org.vso.models.dto.UserRegistrationDTO;

public interface AuthenticationService {

    LoginStatus login(UserLoginDTO userLoginInfo);

    RegistrationStatus registerUser(UserRegistrationDTO userRegistrationDTO);

    User getLoggedUser();

    boolean hasLoggedUser();

    void onUserLogoutSelected();

    void updateUser(User owner);
}
