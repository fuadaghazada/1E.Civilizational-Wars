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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


/**
 *  This class will be the one in which the game play will be implemented
 *
 *  @authors:
 *            - Fuad Aghazada
 *            - Seyfullah Yamanoglu
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

    // Game Properties
    private GameManager gameManager;
    private Camera camera;
    private boolean pauseCalled = false;

    private boolean isMultiPlayer;

    /**
     *   Constructs the game panel
     */
    public GamePanel(int state, boolean isMultiPlayer)
    {
        this.isMultiPlayer = isMultiPlayer;
        init(state);
        start();
        setFocusable(true);
    }

    /**
     *  Initialize the variables
     */
    public void init(int state)
    {
        gameManager = new GameManager(state, isMultiPlayer);
        camera = new Camera(0,0);
        gameManager.setCamera(camera);

        game_thread = new Thread(this);

        isRunning = false;
    }

    /**
     *   Starts the game loop
     */
    public void start()
    {
        isRunning = true;
        game_thread.start();
        setFocusable(true);
        requestFocusInWindow();
        this.addKeyListener(gameManager.getInputManager());
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
     *  Updates the game logic - (object/elements updating according to level)
     */
    public void update()
    {
        if (gameManager.getGameState() == GameManager.PLAYING)
        {
            gameManager.update();
            pause(false);
        }
        else if (gameManager.getGameState() == GameManager.PAUSED)
        {
            pause(true);
        }
        else
        {
            processLevelFinish();
        }
    }

    private void processLevelFinish() {
        if(gameManager.getGameState() == GameManager.WON)
        {
            ScreenManager.getInstance().setCurrentPanel(new WonPanel(gameManager));
            isRunning = false;
        }
        else if(gameManager.getGameState() == GameManager.LOST)
        {
            ScreenManager.getInstance().setCurrentPanel(new LostPanel());
            isRunning = false;
        }
    }

    public void pause(boolean paused)
    {
        if(paused)
        {
            if(!pauseCalled) {
                ScreenManager.getInstance().setCurrentPanel(new PausePanel(gameManager));
                pauseCalled = !pauseCalled;
            }
        }
        else{
            if(pauseCalled) {
                ScreenManager.getInstance().back();
                pauseCalled = false;
            }
        }
    }
    /**
     *  Renders the game graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // --
        Graphics2D g2 = (Graphics2D) g;

        //Camera follows the character
        g2.translate(-camera.getX(), -camera.getY());

        //game manager
        gameManager.render(g);

        g2.translate(camera.getX(), camera.getY());

        //HUD
        this.renderHUD(g);

        g.dispose();
    }

    /**
     *  Renders the HUD of the game panel
     */
    private void renderHUD(Graphics g)
    {
        // Head Up Display

        // Lives
        for(int i = 0; i < GameObjectHandler.getInstance().getCharacter(0).getLives(); i++)
        {
            g.drawImage(new ImageIcon("src/resources/game_textures/life.png").getImage(),  i * 30 + 40, 5, null );
        }

        // HealthBar
        g.setColor(Color.GRAY);
        g.drawRect(getWidth() - 120,5, 100, 20);

        if(GameObjectHandler.getInstance().getCharacter(0).getHealthLevel() <= 20)
        {
            g.setColor(Color.RED);
        }
        else
        {
            g.setColor(Color.GREEN);
        }
        g.fillRect(getWidth() - 120,5, (int) GameObjectHandler.getInstance().getCharacter(0).getHealthLevel(),20);


        // Multiplayer mode
        if(gameManager.isMultiPlayer())
        {
            //identifier
            g.setColor(Color.RED);
            g.fillOval(20, 7, 10,10);
            g.setColor(Color.BLUE);
            g.fillOval(20, 32, 10,10);

            // Lives
            for(int i = 0; i < GameObjectHandler.getInstance().getCharacter(1).getLives(); i++)
            {
                g.drawImage(new ImageIcon("src/resources/game_textures/life.png").getImage(),  i * 30 + 40, 30, null );
            }

            // HealthBar
            g.setColor(Color.GRAY);
            g.drawRect(getWidth() - 120,30, 100, 20);

            if(GameObjectHandler.getInstance().getCharacter(1).getHealthLevel() <= 20)
            {
                g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.GREEN);
            }
            g.fillRect(getWidth() - 120,30, (int) GameObjectHandler.getInstance().getCharacter(1).getHealthLevel(),20);
        }

    }

    // ACCESS

    public GameManager getGameManager()
    {
        return gameManager;
    }
}
