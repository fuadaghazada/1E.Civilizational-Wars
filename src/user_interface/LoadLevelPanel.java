package user_interface;

import main.CivilizationalWars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadLevelPanel extends JPanel
{
    // Constants
    public static final int NUM_OF_LEVELS = 3;

    // Properties
    private JButton backToMenu;
    private JButton [] level_btns;
    private JLabel [] level_icns;

    /*
    *   Constructing the Load level panel.
    */
    public LoadLevelPanel()
    {
        this.init();

        this.listen();

        this.setLayout(new BorderLayout());

        customizePanel();
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
        backToMenu = new JButton("Back to menu main");

        // initializing of the arrays
        level_btns = new JButton[NUM_OF_LEVELS];
        level_icns = new JLabel[NUM_OF_LEVELS];


        for(int i = 0; i < NUM_OF_LEVELS; i++)
        {
            level_btns[i] = new JButton("Level " + (i + 1));
            level_icns[i] = new JLabel();
            level_icns[i].setIcon(new ImageIcon("src/resources/ui_icons/" + "level" + ( i + 1 ) +".png"));
        }
    }

    /*
    *   To customize the panel: changing the properties and add components
    */
    private void customizePanel()
    {
        //---//Helper panel for locating the button (back to main menu)
        JPanel btn_helper_panel = new JPanel();

        btn_helper_panel.setLayout(new BorderLayout());
        btn_helper_panel.add(backToMenu, BorderLayout.WEST);

        //---//Helper panel for locating the button (back to main menu)
        JPanel main_helper_panel = new JPanel();

        main_helper_panel.setLayout(new FlowLayout());

        for(int i = 0; i < NUM_OF_LEVELS; i++)
        {
            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            level_icns[i].setAlignmentX(CENTER_ALIGNMENT);
            level_btns[i].setAlignmentX(CENTER_ALIGNMENT);

            panel1.add(level_icns[i]);
            panel1.add(level_btns[i]);

            main_helper_panel.add(panel1);
        }

        main_helper_panel.setLayout(new GridBagLayout());
        
        //adding components to the panel
        this.add(main_helper_panel, BorderLayout.CENTER);
        this.add(btn_helper_panel, BorderLayout.SOUTH);
    }


}
