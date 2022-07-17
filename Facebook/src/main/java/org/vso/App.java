package org.vso;

import org.vso.views.implementations.LoginViewImpl;
import org.vso.views.implementations.SplashScreen;

import java.util.logging.Level;

public class App {

    public static void main(String[] args) {
        //for Turning off hibernate logging console output
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        //old starter of the program
        //new LoginViewImpl();

        //for testing GUI implementation
        SplashScreen.getInstance();
    }
}
