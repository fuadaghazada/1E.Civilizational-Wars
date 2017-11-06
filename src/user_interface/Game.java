package user_interface;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Game extends JPanel
{
    // properties
    private GamePanel gamePanel;
    private HeadUpDisplay hud;

    /**
     *  Constructs the whole gameplay window with HUD
     */
    public Game(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        hud = new HeadUpDisplay(gamePanel.getCurrent());

        this.setLayout(new BorderLayout());

        this.add(hud, BorderLayout.NORTH);
        this.add(gamePanel);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        System.out.println("--");
    }

}
