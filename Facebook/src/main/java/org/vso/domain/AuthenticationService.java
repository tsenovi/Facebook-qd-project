package org.vso.domain;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.dao.UserRepository;
import org.vso.data.PublicUser;
import org.vso.data.User;
import org.vso.dto.UserLoginDTO;
import org.vso.dto.UserRegistrationDTO;

public class AuthenticationService {

    private final UserRepository userRepository;
    private User loggedUser;

    public AuthenticationService() {
        this.userRepository = new UserRepository();
        this.loggedUser = null;
    }

    public LoginStatus login(UserLoginDTO userLoginInfo) {
        User userByEmail = userRepository.readUserByEmail(userLoginInfo.getEmail());
        if (userByEmail != null && userByEmail.getPassword().equals(userLoginInfo.getPassword())) {
            loggedUser = userByEmail;
            return LoginStatus.LOGIN_SUCCESSFUL;
        }

        return LoginStatus.LOGIN_FAILED;
    }

    public RegistrationStatus registerUser(UserRegistrationDTO userRegistrationDTO) {
        boolean userExists = ifUserExists(userRegistrationDTO.getEmail());
        if (userExists) return RegistrationStatus.REGISTRATION_FAILED;

        User user = mapUserDTOtoUser(userRegistrationDTO);
        userRepository.createUser(user);
        return RegistrationStatus.REGISTRATION_SUCCESSFUL;
    }

    private boolean ifUserExists(String email) {
        User userByEmail = userRepository.readUserByEmail(email);
        if (userByEmail != null) {
            loggedUser = userByEmail;
            return true;
        }

        return false;
    }

    public PublicUser getLoggedUser() {
        return new PublicUser(loggedUser);
    }

    public boolean hasLoggedUser() {
        return loggedUser != null;
    }

    private User mapUserDTOtoUser(UserRegistrationDTO userRegistrationDTO) {
        return new User(userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(),
                userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getAge());
    }
}
