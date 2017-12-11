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
        gameManager.setCamera(camera);



        game_thread = new Thread(this);

        isRunning = false;
        //start();
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

//        if (current == null || current != LevelManager.currentLevel)
//        {
//            this.addKeyListener(LevelManager.currentLevel.getInputManager());
//            current = LevelManager.currentLevel;
//        }
//        else {
//            camera.update(LevelManager.currentLevel.gameObjects().getCharacter());
//            LevelManager.currentLevel.gameObjects().updateAll();
//        }
        if (gameManager.getGameState() == GameManager.PLAYING) {
            gameManager.update();
            pause(false);
        }
        else if (gameManager.getGameState() == GameManager.PAUSED)
        {
            pause(true);
        }

    }

    public void pause(boolean paused)
    {
        //System.out.println("paused: " + paused + "- pauseCalled: "  + pauseCalled);
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
        //this.renderHUD(g);

        g.dispose();
    }

//    private void renderHUD(Graphics g)
//    {
//        // HUD
//
//        //Level name
//        g.setColor(Color.BLACK);
//        g.drawString(current.getName(), getWidth()/2, 20);
//
//        // Lives
//        for(int i = 0; i < this.current.gameObjects().getCharacter().getLives(); i++)
//        {
//            g.drawImage(new ImageIcon("src/resources/game_textures/life.png").getImage(),  i * 30 + 20, 5, null );
//        }
//
//        // HealthBar
//        g.setColor(Color.GRAY);
//        g.drawRect(getWidth() - 120,5, 100, 20);
//
//        if(this.current.gameObjects().getCharacter().getHealthLevel() <= 20)
//        {
//            g.setColor(Color.RED);
//        }
//        else
//        {
//            g.setColor(Color.GREEN);
//        }
//        g.fillRect(getWidth() - 120,5, (int) this.current.gameObjects().getCharacter().getHealthLevel(),20);
//
//
//        // GameOver
//        if(this.current.gameObjects().getCharacter().getLives() == 0)
//        {
//            g.setColor(Color.RED);
//            g.drawString("GAME OVER!", getWidth()/2, getHeight()/2);
//
//
//            final Timer timer = new Timer(5000, new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    System.out.println("+++");
//                }
//            });
//
//            timer.setRepeats(false);
//            timer.start();
//
//            System.out.println("===");
//
//            gameManager = new GameManager();
//            camera = new Camera(0,0);
//        }
//
//        if(this.current.getName().equals("Post Modern Period"))
//        {
//            boolean flag = false;
//            for(int i = 0; i < current.gameObjects().getGame_objects().size() && !flag; i++)
//            {
//                if(current.gameObjects().getGame_objects().get(i).getId() == ObjectID.Enemy)
//                {
//                    flag = false;
//                }
//                else
//                {
//                    if(i == current.gameObjects().getGame_objects().size() - 1)
//                         flag = true;
//                }
//            }
//            if(flag) {
//                g.setColor(Color.GREEN);
//                g.drawString("YOU WON!", getWidth() / 2, getHeight() / 2);
//            }
//        }
//    }

    // ACCESS

    public GameManager getGameManager()
    {
        return gameManager;
    }
}
