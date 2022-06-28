package org.vso.domain;

import org.vso.dao.UserRepository;
import org.vso.data.User;
import org.vso.dto.UserDTO;

import java.util.List;

public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService() {
        this.userRepository = new UserRepository();
    }

    public boolean onUserInfoEntered(UserDTO userDTO) {
        User userByEmail = userRepository.getUserByEmail(userDTO.getEmail());
        if (userByEmail == null) {
            User user = new User(userDTO.getEmail(), userDTO.getPassword(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge());
            userRepository.createUser(user);
            return true;
        }
        return false;
    }
}
