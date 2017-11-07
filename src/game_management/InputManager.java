package game_management;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.Bullet;
import main.CivilizationalWars;
import user_interface.LoadLevelPanel;
import user_interface.MainMenuPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  This class will handle the user input and updates the game flow according to it.
 */
public class InputManager implements KeyListener
{
    // Properties
    GameObjectHandler gameObjectHandler;

    //Default values for input keys
    public static int right = KeyEvent.VK_RIGHT;
    public static int left = KeyEvent.VK_LEFT;
    public static int up = KeyEvent.VK_UP;
    public static int fight = KeyEvent.VK_E;

    /**
     *  Constructs the input manager with given game objects.
     *  @param gameObjectHandler - handler keeps all the game objects.
     */
    public InputManager(GameObjectHandler gameObjectHandler)
    {
        this.gameObjectHandler = gameObjectHandler;
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
            gameObjectHandler.getCharacter().setDir(1);
            gameObjectHandler.getCharacter().getWeapon().setDir(1);
            gameObjectHandler.getCharacter().setVelX(5);
        }
        if(e.getKeyCode() == left)
        {
            gameObjectHandler.getCharacter().setDir(-1);
            gameObjectHandler.getCharacter().getWeapon().setDir(-1);
            gameObjectHandler.getCharacter().setVelX(-5);
        }
        if(e.getKeyCode() == up && !gameObjectHandler.getCharacter().isJump())
        {
            gameObjectHandler.getCharacter().setJump(true);
            gameObjectHandler.getCharacter().setVelY(-10);
        }
        if(e.getKeyCode() == fight)
        {
            gameObjectHandler.getCharacter().getWeapon().setUsed(true);
            gameObjectHandler.getCharacter().getWeapon().fire(gameObjectHandler, gameObjectHandler.getCharacter().getDir());
        }
        //TODO: Think about that
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            CivilizationalWars.frame.getContentPane().removeAll();
            CivilizationalWars.frame.getContentPane().add(new MainMenuPanel());
            CivilizationalWars.frame.revalidate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == right)
        {
            gameObjectHandler.getCharacter().setVelX(0);
        }
        if(e.getKeyCode() == left)
        {
            gameObjectHandler.getCharacter().setVelX(0);
        }
        if(e.getKeyCode() == fight)
        {
            gameObjectHandler.getCharacter().getWeapon().setUsed(false);
        }
    }
}
