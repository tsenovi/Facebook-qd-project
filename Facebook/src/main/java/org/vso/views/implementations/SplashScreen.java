package org.vso.views.implementations;

import org.vso.constants.ImagePathHolder;

import javax.swing.*;

public class SplashScreen extends JWindow {

    private static SplashScreen instance;

    private SplashScreen() {
        setupComponents();
    }

    public static SplashScreen getInstance() {
        if (instance == null) instance = new SplashScreen();
        return instance;
    }

    private void setupComponents() {
        ImageIcon image = new ImageIcon(ImagePathHolder.SPLASH_SCREEN_IMAGE);
        JLabel label = new JLabel();
        label.setIcon(image);
        this.getContentPane().add(label, SwingConstants.CENTER);
        this.setBounds(530, 250, 489, 256);
        this.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.setVisible(false);
        navigateToLaunchPage();
        this.dispose();
    }

    private void navigateToLaunchPage() {
        LaunchPage.getInstance();
    }
}
