package main;

import user_interface.*;

import javax.swing.*;

public class CivilizationalWars
{



    public static void main(String[] args)
    {
        MainMenuPanel mmp = new MainMenuPanel();

        GamePanel gp = new GamePanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        //frame.add(mmp);


        System.out.println("Fuad");


        AboutPanel ap = new AboutPanel();

        HelpPanel hp = new HelpPanel();

        LoadLevelPanel ll = new LoadLevelPanel();

        //frame.add(mmp);

        frame.add(gp);


        //Test for Settings panel
        SettingsPanel sp = new SettingsPanel();

        PausePanel pp = new PausePanel();
        //frame.add(pp);
        System.out.println("Seyf");



        frame.setVisible(true);
        
    }
}
