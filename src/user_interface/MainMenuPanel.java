package user_interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    private JPanel up_panel, center_panel, bottom_panel, buttons_Panel;
    private JButton playBtn, loadLevelBtn, viewSettingsBtn, viewHelpBtn, viewAboutBtn, quitBtn;
    private ImageIcon game_icon;

    /*
    *   Constructing the Main menu panel.
    */
    public MainMenuPanel()
    {
        this.init();

        this.customizeButtons();

        this.customizeUpPanel();
      //  this.customizeCenterPanel();
      //  this.customizeBottomPanel();
       // this.customizeButtonsPanel();

        this.setFocusable(true);

        this.setBackground(Color.BLUE);

        this.setLayout(new BorderLayout());

        this.add(up_panel, BorderLayout.NORTH);
        //this.add(center_panel, BorderLayout.CENTER);
        this.add(center_panel, BorderLayout.SOUTH);
    }

    /*
    *   Initializing the properties - components of the panel
    */
    public void init()
    {
        // Panels
        up_panel = new JPanel();
        center_panel = new JPanel();
        bottom_panel = new JPanel();
        buttons_Panel = new JPanel();

        // Buttons
        playBtn = new JButton("Play Game");
        loadLevelBtn = new JButton("Load Level");
        viewSettingsBtn = new JButton("Settings");
        viewAboutBtn = new JButton("About");
        viewHelpBtn = new JButton("Help");
        quitBtn = new JButton("QUIT");

        // Other
        game_icon = new ImageIcon();
        game_icon.setImage(new ImageIcon("src/title_image.png").getImage());

    }

    /*
    *   Customizing some properties of the JButtons
    */
    public void customizeButtons()
    {

        up_panel.setBackground(Color.BLUE);
        center_panel.setBackground(Color.RED);
        bottom_panel.setBackground(Color.GREEN);
        buttons_Panel.setBackground(Color.GRAY);

        playBtn.setAlignmentX(CENTER_ALIGNMENT);
        loadLevelBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewSettingsBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewAboutBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewHelpBtn.setAlignmentX(CENTER_ALIGNMENT);
    }

    /*
    *   Customizing the up panel: Game icon will be located.
    */
    public void customizeUpPanel()
    {


    }

    /*
    *   Customizing the center panel: ButtonsPanel is located.
    */
    public void customizeCenterPanel()
    {
        center_panel.add(buttons_Panel);
    }

    /*
    *   Customizing the up panel: Quit and help button.
    */
    public void customizeBottomPanel()
    {

    }

    /*
    *   Customizing the buttons panel: Buttons are located in a panel with BoxLayout
    */
    public void customizeButtonsPanel()
    {
        buttons_Panel.setLayout(new BoxLayout(buttons_Panel, BoxLayout.Y_AXIS));

        buttons_Panel.add(playBtn);
        buttons_Panel.add(loadLevelBtn);
        buttons_Panel.add(viewSettingsBtn);
        buttons_Panel.add(viewAboutBtn);
        buttons_Panel.add(viewHelpBtn);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(game_icon.getImage(), up_panel.getX() + 30,up_panel.getY() + 30, null);
    }

}
