package game_object;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject
{
    // Properties
    protected float x, y;
    protected int width, height;
    protected float velX, velY;
    protected ObjectID id;
    protected boolean isJump;
    protected boolean isFall;


    /**
     *  Constructing the game object
     */
    public GameObject(float x, float y, ObjectID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;

        isJump = false;
        isFall = true;

        velX = 0;
        velY = 0;
    }

    public abstract void update(ArrayList<GameObject> gameObjects);

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();


    // ACCESS & MUTATE

    public ObjectID getId()
    {
        return id;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public boolean isJump()
    {
        return isJump;
    }

    public void setJump(boolean jump)
    {
        isJump = jump;
    }

    public boolean isFall()
    {
        return isFall;
    }

    public void setFall(boolean fall)
    {
        isFall = fall;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public float getVelX()
    {
        return velX;
    }

    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }
}
