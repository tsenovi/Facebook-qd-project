package org.vso.views.implementations;

import org.vso.constants.ImagePathHolder;

import javax.swing.*;

public class SplashPage extends JWindow {

    private static SplashPage instance;

    private SplashPage() {
        setupComponents();
    }

    public static SplashPage getInstance() {
        if (instance == null) instance = new SplashPage();
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
            Thread.sleep(5000);
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
