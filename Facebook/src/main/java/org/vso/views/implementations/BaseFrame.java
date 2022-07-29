package org.vso.views.implementations;

import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BaseFrame extends JFrame implements ActionListener {

    private final ImageIcon icon;

    public BaseFrame(){
        icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
    }
}
