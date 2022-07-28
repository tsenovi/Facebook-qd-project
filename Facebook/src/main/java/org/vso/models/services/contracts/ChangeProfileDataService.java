package org.vso.models.services.contracts;

public interface ChangeProfileDataService {

    void editEmail(String newEmail, String repeatEmail);

    void editPassword(String newPassword, String repeatPassword);
}
