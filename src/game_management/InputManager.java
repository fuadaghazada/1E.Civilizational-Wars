package game_management;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  This class will handle the user input and updates the game flow according to it.
 */
public class InputManager implements KeyListener
{
    // Properties
    GameObjectHandler gameObjectHandler;

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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            gameObjectHandler.getCharacter().setVelX(5);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            gameObjectHandler.getCharacter().setVelX(-5);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && !gameObjectHandler.getCharacter().isJump())
        {
            gameObjectHandler.getCharacter().setJump(true);
            gameObjectHandler.getCharacter().setVelY(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            gameObjectHandler.getCharacter().setVelX(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            gameObjectHandler.getCharacter().setVelX(0);
        }
    }
}
