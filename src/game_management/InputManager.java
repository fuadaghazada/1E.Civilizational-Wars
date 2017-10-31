package game_management;

import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.ObjectID;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    GameObjectHandler gameObjectHandler;


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
        for(GameObject gameObject : gameObjectHandler.game_objects)
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
        for(GameObject gameObject : gameObjectHandler.game_objects)
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
