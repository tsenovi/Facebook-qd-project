package org.vso.models.services.implementations;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.dto.UserLoginDTO;
import org.vso.models.dto.UserRegistrationDTO;
public class AuthenticationServiceImpl implements AuthenticationService {

    private static AuthenticationService instance;
    private final UserDao<User> userDao;
    private User loggedUser;

    public AuthenticationServiceImpl() {
        this.userDao = new UserDaoImpl();
        this.loggedUser = null;
    }

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationServiceImpl();
        }

        return instance;
    }

    @Override
    public LoginStatus login(UserLoginDTO userLoginInfo) {
        User user = userDao.getByEmail(userLoginInfo.getEmail());
        if (user != null) {
            if (user.getPassword().equals(userLoginInfo.getPassword())) {
                loggedUser = user;

                return LoginStatus.LOGIN_SUCCESSFUL;
            }
        }

        return LoginStatus.LOGIN_FAILED;
    }

    @Override
    public RegistrationStatus registerUser(UserRegistrationDTO userRegistrationDTO) {
        User optionalUser = userDao.getByEmail(userRegistrationDTO.getEmail());
        if (optionalUser != null) {
            return RegistrationStatus.REGISTRATION_FAILED;
        }

        User user = mapUserDTOtoUser(userRegistrationDTO);
        userDao.save(user);
        loggedUser = user;

        return RegistrationStatus.REGISTRATION_SUCCESSFUL;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    @Override
    public boolean hasLoggedUser() {
        return loggedUser != null;
    }

    @Override
    public void onUserLogoutSelected() {
        this.loggedUser = null;
    }

    private User mapUserDTOtoUser(UserRegistrationDTO userRegistrationDTO) {
        return new User(userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(),
                userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(),
                userRegistrationDTO.getAge());
    }
}
