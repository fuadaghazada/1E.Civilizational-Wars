package user_interface;

import game_management.InputManager;
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

        handler = new GameObjectHandler();


        handler.addGameObject(new Robot(60.f,20.f, ObjectID.Character, handler));

        handler.addGameObject(new Enemy(100,80, ObjectID.Enemy, handler));
        handler.addGameObject(new Enemy(200,80, ObjectID.Enemy, handler));
        handler.addGameObject(new Enemy(150,80, ObjectID.Enemy, handler));
        handler.addGameObject(new Enemy(500,80, ObjectID.Enemy, handler));
        handler.addGameObject(new Enemy(300,80, ObjectID.Enemy, handler));
        handler.addGameObject(new Enemy(250,80, ObjectID.Enemy, handler));

        tileMap = new TileMap("src/resources/map_files/map_level_1.txt");

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
