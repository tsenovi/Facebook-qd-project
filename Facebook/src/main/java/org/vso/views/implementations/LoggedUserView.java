package org.vso.views.implementations;

import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;


public class LoggedUserView {
    private final AuthenticationService authenticationService;

    public LoggedUserView() {
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    public String loggedUserFirstName(){
        return "First name : " + authenticationService.getLoggedUser().getFirstName();
    }

    public String loggedUserLastName(){
        return "Last name: " + authenticationService.getLoggedUser().getLastName();
    }

    public String loggedUserAge(){
        return "Age: " + authenticationService.getLoggedUser().getAge();
    }

    public String loggedUserEmail(){
        return "Email: " + authenticationService.getLoggedUser().getEmail();
    }
}
