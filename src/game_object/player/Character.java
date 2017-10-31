package game_object.player;

import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.ObjectID;

import java.awt.*;
import java.util.ArrayList;

public class Character extends GameObject
{

    // Constants
    private final float MAX_SPEED = 5;

    // Properties
    private float gravity = 0.3f;

    private GameObjectHandler gameObjectHandler;


    /**
     * Constructing the character
     *
     * @param x
     * @param y
     * @param id
     */
    public Character(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler)
    {
        super(x, y, id);

        this.gameObjectHandler = gameObjectHandler;

        this.setHeight(70);
        this.setWidth(30);
    }

    @Override
    public void update(ArrayList<GameObject> gameObjects)
    {
        x += velX;
        y += velY;

        if(isFall || isJump)
        {
            velY += gravity;

            if(velY > MAX_SPEED)
            {
                velY = MAX_SPEED;
            }
        }

        this.checkCollision(gameObjects);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLUE);

        g.fillRect((int)x, (int)y, width, height);
    }

    private void checkCollision(ArrayList<GameObject> gameObjects)
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            GameObject tempObject = gameObjectHandler.game_objects.get(i);

            if(tempObject.getId() == ObjectID.Tile)
            {
                if(this.getBoundsTop().intersects(tempObject.getBounds()))
                {
                    //setY(tempObject.getY() + height/2);
                    setVelY(0);
                }

                if(this.getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() - getWidth());
                }

                if(this.getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() + getWidth());
                }

                if(this.getBounds().intersects(tempObject.getBounds()))
                {
                    setVelY(0);
                    setFall(false);
                    setJump(false);
                }
                else
                {
                    setFall(true);
                }
            }
        }
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y+5, 5, height - 10);
    }

    public Rectangle getBoundsTop()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2);
    }
}
