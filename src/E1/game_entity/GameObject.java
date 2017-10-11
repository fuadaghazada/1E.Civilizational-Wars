package game_entity;

import E1.game_map.Tile;
import E1.game_map.TileMap;

import java.awt.*;

/**
 *  This class creates an abstarct game object represents the basic properties for all the game elements
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

public abstract class GameObject
{
    //properties
    private TileMap tileMap;

    protected float x, y;
    protected float dx, dy;

    protected int width, height;

    protected float move_speed;
    protected float fall_speed;
    protected float jump_force;

    private float gravity;

    public GameObject(TileMap tileMap)
    {
        this.tileMap = tileMap;

    }

    /**
     *  Methods needs to be implemented in the subclasses
     */
    public abstract void update();
    public abstract void render(Graphics2D graphics2D);


    // ACCESS & MUTATE

    public TileMap getTileMap()
    {
        return tileMap;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getDx()
    {
        return dx;
    }

    public float getDy()
    {
        return dy;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public float getJump_force()
    {
        return jump_force;
    }

    public float getMove_speed()
    {
        return move_speed;
    }

    public float getFall_speed()
    {
        return fall_speed;
    }

    public float getGravity()
    {
        return gravity;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setDx(float dx)
    {
        this.dx = dx;
    }

    public void setDy(float dy)
    {
        this.dy = dy;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setMove_speed(float move_speed)
    {
        this.move_speed = move_speed;
    }

    public void setJump_force(float jump_force)
    {
        this.jump_force = jump_force;
    }

    public void setFall_speed(float fall_speed)
    {
        this.fall_speed = fall_speed;
    }

    public void setGravity(float gravity)
    {
        this.gravity = gravity;
    }
}
