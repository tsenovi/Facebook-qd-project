package org.vso.domain;

import org.vso.dao.UserRepository;
import org.vso.data.PublicUser;
import org.vso.data.User;
import org.vso.dto.UserDTO;

public class AuthenticationService {

    private final UserRepository userRepository;
    private User loggedUser;

    public AuthenticationService() {
        this.userRepository = new UserRepository();
        this.loggedUser = null;
    }

    public boolean onUserInfoEntered(UserDTO userDTO) {
        boolean userExists = ifUserExists(userDTO);
        if (userExists) return false;

        User user = mapUserDTOtoUser(userDTO);
        userRepository.createUser(user);
        return true;
    }

    public boolean ifUserExists(UserDTO userDTO) {
        User userByEmail = userRepository.readUserByEmail(userDTO.getEmail());
        if (userByEmail != null) {
            loggedUser = userByEmail;
            return true;
        }
        return false;
    }

    public PublicUser getLoggedUser() {
        return new PublicUser(loggedUser);
    }

    private User mapUserDTOtoUser(UserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge());
    }
}
