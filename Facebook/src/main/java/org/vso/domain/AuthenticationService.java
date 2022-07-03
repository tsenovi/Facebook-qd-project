package org.vso.domain;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.dao.UserRepository;
import org.vso.data.PublicUser;
import org.vso.data.User;
import org.vso.dto.UserLoginDTO;
import org.vso.dto.UserRegistrationDTO;

public class AuthenticationService {
    private static AuthenticationService instance;
    private final UserRepository userRepository;
    private User loggedUser;

    private AuthenticationService() {
        this.userRepository = new UserRepository();
        this.loggedUser = null;
    }

    public static AuthenticationService getInstance() {
        if (instance == null) instance = new AuthenticationService();
        return instance;
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
        User userByEmail = userRepository.readUserByEmail(userRegistrationDTO.getEmail());
        if (userByEmail != null) return RegistrationStatus.REGISTRATION_FAILED;

        User user = mapUserDTOtoUser(userRegistrationDTO);
        userRepository.createUser(user);
        loggedUser = user;

        return RegistrationStatus.REGISTRATION_SUCCESSFUL;
    }

    public PublicUser getLoggedUser() {
        return new PublicUser(loggedUser);
    }

    public boolean hasLoggedUser() {
        return loggedUser != null;
    }

    public void onUserLogoutSelected() {
        this.loggedUser = null;
    }

    private User mapUserDTOtoUser(UserRegistrationDTO userRegistrationDTO) {
        return new User(userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(),
                userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getAge());
    }
}
