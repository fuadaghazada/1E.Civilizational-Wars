package game_object.general;

import java.awt.*;

/**
 *  This class represents the basic properties of the game objects.
 *
 */

public abstract class GameObject implements IUpdatable, IRenderable
{
    // Constants
    public static final float MAX_SPEED = 5;

    // Properties
    protected ObjectID id;
    protected double x, y;
    protected int width, height;
    protected float velX, velY;
    protected boolean isJump;
    protected boolean isFall;
    protected int dir = 1;

    private boolean toBeRemoved = false;

    private static float GRAVITY = 0.3f;


    /**
     *  Constructing the game object with given parameters.
     * @param x - x coordinate of the game object.
     *  @param y - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public GameObject(double x, double y, ObjectID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;

        isJump = false;
        isFall = true;

        velX = 0;
        velY = 0;
    }


    @Override
    public void update()
    {

        x += velX * dir;
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
    @Override
    public abstract void render(Graphics g);

    protected abstract boolean checkCollision();

    /**
     *  Access the bounds of the object in a rectangle.
     */
    public abstract Rectangle getBounds();

    // ACCESS & MUTATE

    public ObjectID getId()
    {
        return id;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
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

    public  int getDir() { return dir; }

    public  void setDir(int dir) { this.dir = dir; }

    public String toString() { return id + "-" + x + "-" + y; }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}

