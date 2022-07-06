package org.vso.models.domain.contracts;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.models.data.PublicUser;
import org.vso.models.dto.UserLoginDTO;
import org.vso.models.dto.UserRegistrationDTO;

public interface AuthenticationService {

    LoginStatus login(UserLoginDTO userLoginInfo);

    RegistrationStatus registerUser(UserRegistrationDTO userRegistrationDTO);

    PublicUser getLoggedUser();

    boolean hasLoggedUser();

    void onUserLogoutSelected();
}
