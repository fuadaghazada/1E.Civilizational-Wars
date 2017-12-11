package user_interface;

import main.CivilizationalWars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutPanel extends JPanel
{
    // Properties
    private JButton backToMenu;
    private JTextArea about_txt;

    /*
    *   Constructing the About panel.
    */
    public AboutPanel()
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
                ScreenManager.getInstance().setCurrentPanel(new MainMenuPanel());
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

        //---//Helper panel for locating the text are on the about panel.
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

        about_txt.append("This project has been implemented as a part of CS-319 Project in Bilkent University, Ankara, Turkey. \n\n\n" +
                        "Authors: Fuad Aghazada, Seyfullah Yamanoglu, Bayram Muradov, Berk Erzin");
    }

}
