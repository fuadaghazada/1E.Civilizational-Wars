package E1.game_main;

import E1.game_map.TileMap;

import javax.swing.*;
import java.awt.*;

/**
 *  This class will be the one in which the game play will be implemented
 *
 *  @authors:
 *            - Fuad Aghazada
 *
 *
 *  @version - 1.00
 */

public class GamePanel extends JPanel implements Runnable
{
    //Constants

    //GAME LOOP properties

    private Thread game_thread;
    private boolean isRunning = false;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    //Game elements
    TileMap tileMap;

    /**
     *   Constructs the game panel
     */
    public GamePanel()
    {
        init();
        start();
        setFocusable(true);
    }

    /**
     *  Initialize the variables
     */
    public void init()
    {
        tileMap = new TileMap("src/map_level_1.txt");
    }

    /**
     *   Starts the game loop
     */
    public void start()
    {
        isRunning = true;
        game_thread = new Thread(this);
        game_thread.start();
    }

    /**
     *   Runs the game loop
     */
    @Override
    public void run()
    {
        //some time variables for constructing the loop
        long start;
        long elapsed;
        long wait;

        while (isRunning)
        {
            start = System.nanoTime();

            //TODO update and render the game logic here
            update();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if(wait < 0)
            {
                wait = 5;
            }

            try
            {
                Thread.sleep(wait);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     *  Updates the game logic - (object/elements updating)
     */
    public void update()
    {
        tileMap.update();
    }

    /**
     *  Renders the game graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        tileMap.render((Graphics2D)g);
    }
}
