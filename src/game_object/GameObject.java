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
    // Constants
    public static final float MAX_SPEED = 5;

    // Properties
    private static float GRAVITY = 0.3f;

    /**
     *  Constructing the game object with given parameters.
     *  @param x - x coordinate of the game object.
     *  @param y - y coordinate of the game object.
     *  @param id - object id defines the type of the objects.
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

    /**
     * Updates the properties of the game object.
     *
     * @param gameObjects - to check the collision between the game objects.
     */
    public void update(ArrayList<GameObject> gameObjects) {
        x += velX;
        y += velY;

        if(isFall || isJump)
        {
            velY += GRAVITY;

            if(velY > MAX_SPEED)
            {
                velY = MAX_SPEED;
            }
        }
    }

    /**
     *  Render the graphics of the game object.
     *  @param g - Graphics of the program.
     */
    public abstract void render(Graphics g);

    /**
     *  Access the bounds of the object in a rectangle.
     */
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
