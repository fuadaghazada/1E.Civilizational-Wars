package game_object.player;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.Weapon;

import java.awt.*;

/**
 *  This class will represent the Character of the game with its given parameters and methods.
 */
public class Character extends GameObject
{
    // Constants
    private final float MAX_SPEED = 5;

    // Properties
    private float gravity = 0.3f;

    private Weapon weapon;

    private GameObjectHandler gameObjectHandler;

    /**
     * Constructing the character with given parameters.
     *
     * @param x - x coordinate of the character.
     * @param y - y coordinate of the character.
     * @param id - id of the character as a game object.
     */
    public Character(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler)
    {
        super(x, y, id);

        this.gameObjectHandler = gameObjectHandler;

        this.setHeight(70);
        this.setWidth(50);
    }

    @Override
    public void update(GameObjectHandler gameObjectHandler)
    {
        super.update(gameObjectHandler);

        this.checkCollision(gameObjectHandler);
    }

    @Override
    public void render(Graphics g)
    {
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }

    @Override
    protected boolean checkCollision(GameObjectHandler gameObjectHandler)
    {
        for(int i = 0; i < gameObjectHandler.getGame_objects().size(); i++)
        {
            // To keep the game objects in a temp variable - for simplicity
            GameObject tempObject = gameObjectHandler.getGame_objects().get(i);

            //checking collision with the tiles.
            if(tempObject.getId() == ObjectID.Tile)
            {
                // Top collision
                if(this.getBoundsTop().intersects(tempObject.getBounds()))
                {
                    setY(y+2);
                    setVelY(0);
                }

                // Right collision
                if(this.getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() - getWidth());
                    
                }

                // Left collision
                if(this.getBoundsLeft().intersects(tempObject.getBounds()))
                {

                    setX(tempObject.getX() + tempObject.getWidth());

                }

                // Bottom Collision
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
        return false;
    }

    // ACCESS to different bounds of the character

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsTop() { return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2); }

    public float getGravity() { return gravity; }

    public void setGravity(float gravity) { this.gravity = gravity; }

    public Weapon getWeapon() { return weapon; }

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
}
