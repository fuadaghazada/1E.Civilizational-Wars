package user_interface;


import game_management.DataManager;
import main.CivilizationalWars;
import user_interface.custom_button.GameButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *  This class will represent the panel in which the main
 *      menu  components and listeners
 *      are take place
 *
 *      @authors: Fuad Aghazada
 *                Bayram Muradov
 *
 */

public class MainMenuPanel extends JPanel
{
    // Properties
    private JPanel up_panel, center_panel, bottom_panel;
    private JButton playBtn, loadLevelBtn, viewSettingsBtn, viewHelpBtn, viewAboutBtn, quitBtn;
    private JLabel game_label;

    /*
    *   Constructing the Main menu panel.
    */
    public MainMenuPanel()
    {
        this.init();

        this.setLayout(new GridLayout(2, 0));

        this.listen();

        this.customizeButtons();
        this.customizeUpPanel();
        this.customizeBottomPanel();

        this.add(up_panel);
        this.add(center_panel);
        this.add(center_panel);
    }

    public void listen()
    {
        playBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GamePanel gPanel = new GamePanel();

                ScreenManager.getInstance().setCurrentPanel(gPanel);
            }
        });

        loadLevelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                //ScreenManager.getInstance().setCurrentPanel(new LoadLevelPanel());

                if(DataManager.getInstance().isSuccessfulRead())
                {
                    DataManager.getInstance().setLoadCalled(true);

                    GamePanel gPanel = new GamePanel();

                    ScreenManager.getInstance().setCurrentPanel(gPanel);
                }
                else
                {
                    JOptionPane.showMessageDialog(ScreenManager.getInstance().getFrame(), "NO saved game", "WARNING", 1);
                }
            }
        });

        viewSettingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ScreenManager.getInstance().setCurrentPanel(new SettingsPanel());
            }
        });

        viewAboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ScreenManager.getInstance().setCurrentPanel(new AboutPanel());
            }
        });

        viewHelpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ScreenManager.getInstance().setCurrentPanel(new HelpPanel());
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);
            }
        });

    }

    /*
    *   Initializing the properties - components of the panel
    */
    private void init()
    {
        // Panels
        up_panel = new JPanel();
        center_panel = new JPanel();
        bottom_panel = new JPanel();

        // Buttons
        playBtn = new JButton("Play Game");
        loadLevelBtn = new JButton("Load Level");
        viewSettingsBtn = new JButton("Settings");
        viewAboutBtn = new JButton("About");
        viewHelpBtn = new JButton();
        quitBtn = new JButton();

        playBtn.setUI(new GameButtonUI());
        loadLevelBtn.setUI(new GameButtonUI());
        viewSettingsBtn.setUI(new GameButtonUI());
        viewAboutBtn.setUI(new GameButtonUI());

        // Labels (for icon)
        game_label = new JLabel();
        game_label.setIcon(new ImageIcon("src/resources/ui_icons/title_image.png"));
    }

    /*
    *   Customizing some properties of the JButtons
    */
    private void customizeButtons()
    {
        playBtn.setAlignmentX(CENTER_ALIGNMENT);
        loadLevelBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewSettingsBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewAboutBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewHelpBtn.setAlignmentX(CENTER_ALIGNMENT);

        viewHelpBtn.setIcon(new ImageIcon("src/resources/ui_icons/help.png"));
        quitBtn.setIcon(new ImageIcon("src/resources/ui_icons/quit.png"));
    }

    /*
    *   Customizing the up panel: Game icon will be located.
    */
    private void customizeUpPanel()
    {
        up_panel.setLayout(new GridBagLayout());

        up_panel.add(game_label);
    }

    /*
    *   Customizing the center panel: ButtonsPanel is located.
    */
    private void customizeBottomPanel()
    {
        //-----//Panel for keeping the main buttons.
        JPanel main_buttons_panel = new JPanel();

        main_buttons_panel.setLayout(new BoxLayout(main_buttons_panel, BoxLayout.Y_AXIS));

        main_buttons_panel.add(playBtn);
        main_buttons_panel.add(loadLevelBtn);
        main_buttons_panel.add(viewSettingsBtn);
        main_buttons_panel.add(viewAboutBtn);

        //-----//Panel for keeping rest of the buttons: help and quit
        JPanel other_buttons_panel = new JPanel();

        other_buttons_panel.setLayout(new FlowLayout());

        other_buttons_panel.add(viewHelpBtn);
        other_buttons_panel.add(quitBtn);

        //-----//Helper panel for locating the other_buttons_panel in the most left
        JPanel helperPanel = new JPanel();

        helperPanel.setLayout(new BorderLayout());
        helperPanel.add(other_buttons_panel, BorderLayout.EAST);

        // setting the proper layout and add the components to it.
        center_panel.setLayout(new BorderLayout());

        center_panel.add(main_buttons_panel, BorderLayout.CENTER);
        center_panel.add(helperPanel, BorderLayout.SOUTH);
    }
}
