package user_interface;

import game_management.ClassicLevel;
import main.CivilizationalWars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel
{
    // Properties
    private JButton backToMenu;
    private JTextArea about_txt;

    /*
    *   Constructing the Help panel.
    */
    public HelpPanel()
    {
        this.init();

        this.listen();

        this.setLayout(new BorderLayout());

        this.customizeTextArea();
        this.customizePanel();
    }

    public void listen()
    {
        backToMenu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CivilizationalWars.frame.getContentPane().removeAll();
                CivilizationalWars.frame.add(new MainMenuPanel());
                CivilizationalWars.frame.revalidate();
            }
        });
    }

    /*
    *   Initializing the properties - components of the panel
    */
    private void init()
    {
        //Buttons
        backToMenu = new JButton("Back to main menu");

        // Text area
        about_txt = new JTextArea();
    }

    /*
    *   To customize the panel: change properties and add components.
    */
    private void customizePanel()
    {
        //---//Helper panel for locating the button (back to main menu)
        JPanel btn_helper_panel = new JPanel();

        btn_helper_panel.setLayout(new BorderLayout());
        btn_helper_panel.add(backToMenu, BorderLayout.WEST);

        //---//Helper panel for locating the text are on the help panel.
        JPanel txt_help_panel = new JPanel();

        txt_help_panel.setLayout(new GridBagLayout());
        txt_help_panel.setBackground(Color.WHITE);
        txt_help_panel.add(about_txt);

        //adding components to the panel
        this.add(txt_help_panel, BorderLayout.CENTER);
        this.add(btn_helper_panel, BorderLayout.SOUTH);
    }

    /*
    *   To customize the text area: change properties.
    */
    private void customizeTextArea()
    {
        about_txt.setLineWrap(true);

        about_txt.setPreferredSize(new Dimension(500,500));
        about_txt.setEditable(false);

        about_txt.append("Default control keys: arrow keys for movement -  E for fighting\n\n" +
                         "To get information about the game go to main/about\n\n" +
                         "To start the game go to main menu and press 'Start game' button\n\n" +
                         "To continue the level you left go to menu and press 'Load Level' button");
    }

}
