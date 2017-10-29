package main;

import user_interface.MainMenuPanel;
import user_interface.SettingsPanel;

import javax.swing.*;

public class CivilizationalWars
{



    public static void main(String[] args)
    {
        MainMenuPanel mmp = new MainMenuPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(mmp);
        frame.setVisible(true);

        System.out.println("Fuad");

        /*
        //Test for Settings panel
        SettingsPanel sp = new SettingsPanel();
        frame.add(sp);
        System.out.println("Seyf");
        */

        
    }
}
