package game_management;

import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.ObjectID;

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
        for(GameObject gameObject : gameObjectHandler.getGame_objects())
        {
            GameObject temp = gameObject;

            if(temp.getId() == ObjectID.Character)
            {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    temp.setVelX(5);
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    temp.setVelX(-5);
                }
                if(e.getKeyCode() == KeyEvent.VK_UP && !temp.isJump())
                {
                    temp.setJump(true);
                    temp.setVelY(-10);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        for(GameObject gameObject : gameObjectHandler.getGame_objects())
        {
            GameObject temp = gameObject;

            if(temp.getId() == ObjectID.Character)
            {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    temp.setVelX(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    temp.setVelX(0);
                }
            }
        }
    }
}
