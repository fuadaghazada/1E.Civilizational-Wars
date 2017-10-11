package game_entity;

import E1.game_map.TileMap;

import java.awt.*;

/**
 *  This class creates the main character model.
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

public class Character extends GameObject
{
    //Constants

    //Properties
    private boolean moving_right;
    private boolean moving_left;
    private boolean falling;
    private boolean jumping;


    /**
     *  Constructing the Character by setting its properties
     */
    public Character(TileMap tileMap)
    {
        super(tileMap);

        //setting the initial properties
        this.setWidth(30);
        this.setHeight(30);

        this.setMove_speed(1);
        this.setJump_force(-10.0f);

        this.setGravity(0.6f);
    }

    // ACCESS & MUTATE

    public void setMoving_right(boolean moving_right)
    {
        this.moving_right = moving_right;
    }

    public void setMoving_left(boolean moving_left)
    {
        this.moving_left = moving_left;
    }

    public void setFalling(boolean falling)
    {
        this.falling = falling;
    }

    public void setJumping(boolean jumping)
    {
        if(!falling)
            this.jumping = jumping;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics2D graphics2D)
    {

    }
}
