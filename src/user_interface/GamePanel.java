package user_interface;

import game_management.*;
import game_object.general.Camera;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.enemy.Enemy;
import game_object.map.Tile;
import game_object.map.TileMap;
import game_object.player.Character;
import game_object.player.ClassicFighter;
import game_object.player.Robot;

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
	private static final long serialVersionUID = -3314656870429864436L;

    //GAME LOOP properties
	private Thread game_thread;
    private boolean isRunning = false;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    //
    private GameManager gameManager;
    private Camera camera;


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
        gameManager = new GameManager();
        camera = new Camera(0,0);

        this.addKeyListener(new InputManager(gameManager.getLevelManager().getCurrentLevel().gameObjects()));
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
        camera.update(gameManager.getLevelManager().getCurrentLevel().gameObjects().getCharacter());
        gameManager.getLevelManager().getCurrentLevel().gameObjects().updateAll();
    }

    /**
     *  Renders the game graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Camera follows the character
        g2.translate(-camera.getX(), -camera.getY());

        //game manager
        gameManager.getLevelManager().getCurrentLevel().gameObjects().renderAll(g);

        g2.translate(camera.getX(), camera.getY());

        g.dispose();
    }
}
