package game_management;

import game_object.general.GameObjectHandler;
import main.CivilizationalWars;
import user_interface.MainMenuPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  This class will handle the user input and updates the game flow according to it.
 */
public class InputManager implements KeyListener
{

    //Default values for input keys
    //Keys for 1st player
    public static int right = KeyEvent.VK_RIGHT;
    public static int left = KeyEvent.VK_LEFT;
    public static int up = KeyEvent.VK_UP;
    public static int fight = KeyEvent.VK_K;

    //Keys for second player
    public static int right2 = KeyEvent.VK_H;
    public static int left2 = KeyEvent.VK_F;
    public static int up2 = KeyEvent.VK_H;
    public static int fight2 = KeyEvent.VK_Q;

    private GameManager gameManager;

    public InputManager(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == right)
        {
            GameObjectHandler.getInstance().getCharacter(0).move(1);

        }
        if(e.getKeyCode() == left)
        {
            GameObjectHandler.getInstance().getCharacter(0).move(-1);
        }
        if(e.getKeyCode() == up && !GameObjectHandler.getInstance().getCharacter(0).isJump())
        {
            GameObjectHandler.getInstance().getCharacter(0).jump();
        }
        if(e.getKeyCode() == fight)
        {
            GameObjectHandler.getInstance().getCharacter(0).fight(true);
        }

        //TODO: Think about that
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            gameManager.setPaused(!gameManager.isGamePaused());
        }

        //Second player

        if(!GameObjectHandler.getInstance().isMultiPlayer())
            return;
        if(e.getKeyCode() == right2)
        {
            GameObjectHandler.getInstance().getCharacter(1).move(1);

        }
        if(e.getKeyCode() == left2)
        {
            GameObjectHandler.getInstance().getCharacter(1).move(-1);

        }
        if(e.getKeyCode() == up2 && !GameObjectHandler.getInstance().getCharacter(1).isJump())
        {
            GameObjectHandler.getInstance().getCharacter(1).jump();
        }
        if(e.getKeyCode() == fight2)
        {
            GameObjectHandler.getInstance().getCharacter(1).fight(true);
        }


    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == right || e.getKeyCode() == left)
        {
            GameObjectHandler.getInstance().getCharacter(0).setVelX(0);
        }
        if(e.getKeyCode() == fight)
        {
            GameObjectHandler.getInstance().getCharacter(0).fight(false);
        }

        //Second player
        if(!GameObjectHandler.getInstance().isMultiPlayer())
            return;
        if(e.getKeyCode() == right || e.getKeyCode() == left)
        {
            GameObjectHandler.getInstance().getCharacter(1).setVelX(0);
        }
        if(e.getKeyCode() == fight)
        {
            GameObjectHandler.getInstance().getCharacter(1).fight(false);
        }




    }
}
