package user_interface;

import game_management.InputManager;
import game_object.Camera;
import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.ObjectID;
import game_object.map.Tile;
import game_object.map.TileMap;
import game_object.player.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

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

    //Game elements
    TileMap tileMap;

    GameObjectHandler handler;
    Camera camera;

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
        camera = new Camera(0,0);

        tileMap = new TileMap("src/resources/map_files/map_level_1.txt");

        handler = new GameObjectHandler();

        handler.addGameObject(new Character(60.f,20.f, ObjectID.Character, handler));

        for (Tile tile : tileMap.getTiles())
        {
            handler.addGameObject(tile);

        }

        this.addKeyListener(new InputManager(handler));
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

        handler.updateAll();

        for(GameObject gameObject : handler.getGame_objects())
        {
            if(gameObject.getId() == ObjectID.Character)
                 camera.update(gameObject);
        }
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

        handler.renderAll(g);

        g2.translate(camera.getX(), camera.getY());

        g.dispose();
    }
}
