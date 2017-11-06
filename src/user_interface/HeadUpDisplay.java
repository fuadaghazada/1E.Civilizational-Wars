package user_interface;

import game_management.ILevelInterface;

import javax.swing.*;
import java.awt.*;

/**
 *  This class will be the one in which the head-up-game-display is implemented.s
 *
 */

public class HeadUpDisplay extends JPanel
{
    // Game properties
    private ILevelInterface currentLevel;

    private int lives;
    private float healthLevel;
    private int level;

    // UI properties
    private JLabel [] livesLabels;
    private JPanel healthLevelPanel;


    /**
     *  Constructs a HUD according to the given current level.
     *  @param currentLevel - current level of the game.
     */
    public HeadUpDisplay(ILevelInterface currentLevel)
    {
        this.currentLevel = currentLevel;

        healthLevel = currentLevel.gameObjects().getCharacter().getHealthLevel();

        this.setBackground(Color.BLUE);

        this.customizePanel();
    }

    /**
     *  To update the game attributes properly on the panel
     */
    public void update()
    {
        lives = currentLevel.gameObjects().getCharacter().getLives();
        healthLevelPanel = new HealthLevelPanel(healthLevel);
    }

    /**
     *  To render the game properties on the panel properly
     */
    public void render(Graphics g)
    {

    }

    /**
     *  Customizes the panel
     */
    private void customizePanel()
    {
        // Panel in which the lives will be displayed

        this.setLayout(new GridLayout(1, 2));

        JPanel livesPanel = new JPanel();
        livesPanel.setBackground(Color.BLUE);
        livesPanel.setLayout(new FlowLayout());

        livesLabels = new JLabel[3];

        for(int i = 0; i < livesLabels.length; i++)
        {
            livesLabels[i] = new JLabel();
            livesLabels[i].setIcon(new ImageIcon("src/resources/game_textures/life.png"));
            livesPanel.add(livesLabels[i]);
        }

        healthLevelPanel = new HealthLevelPanel(healthLevel);

        this.add(livesPanel);
        this.add(healthLevelPanel);
    }

    // Inner class for filling the health level rectangle
    private class HealthLevelPanel extends JPanel
    {
        // properties
        private float healthLevel;

        /**
         *  Constructs the health level rectangle
         */
        public HealthLevelPanel(float heathLevel)
        {
            this.healthLevel = heathLevel;
        }

        @Override
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.WHITE);
            g.drawRect(0, getHeight()/2 - 5, 100, 10);

            if(healthLevel < 20)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);

            g.fillRect(0,getHeight()/2 - 5, (int) healthLevel,10);
        }
    }
}
