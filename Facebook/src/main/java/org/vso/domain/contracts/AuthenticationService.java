package org.vso.domain.contracts;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.data.PublicUser;
import org.vso.dto.UserLoginDTO;
import org.vso.dto.UserRegistrationDTO;

public interface AuthenticationService {

    LoginStatus login(UserLoginDTO userLoginInfo);

    RegistrationStatus registerUser(UserRegistrationDTO userRegistrationDTO);

    PublicUser getLoggedUser();

    boolean hasLoggedUser();

    void onUserLogoutSelected();
}
