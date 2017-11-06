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

        about_txt.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et sapien nulla. Nam eu felis tellus. Integer vel lectus vitae sem ultrices ultricies. Sed maximus ornare metus sit amet blandit. Sed vel felis ligula. Quisque feugiat efficitur turpis, ac lacinia est convallis quis. In vel lacus urna. Curabitur a ornare metus, condimentum faucibus tellus. Proin semper mi eu tortor fermentum, a ornare leo interdum.");
    }

}
