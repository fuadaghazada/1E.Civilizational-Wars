package E1.game_main;

import javax.swing.*;
import java.awt.*;

/**
 *  This class will be the frame of the game.
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

public class GameFrame extends JFrame
{
    //Constants
    public static final int FRAME_HEIGHT = 700;
    public static final int FRAME_WIDTH  = 1000;

    /**
     *   Constructs the game frame
     */
    public GameFrame()
    {
        GamePanel gp = new GamePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setResizable(false);

        this.add(gp);
        this.setVisible(true);
    }
}
