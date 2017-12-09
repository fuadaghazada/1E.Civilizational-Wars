package game_object.player;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.Bullet;
import game_object.weapon.Weapon;
import main.CivilizationalWars;
import user_interface.Game;
import user_interface.GamePanel;
import user_interface.MainMenuPanel;

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
    private int lives = 3;
    private float healthLevel = 100f;
    private Weapon weapon;


    /**
     * Constructing the character with given parameters.
     *
     * @param x - x coordinate of the character.
     * @param y - y coordinate of the character.
     * @param id - id of the character as a game object.
     */
    public Character(float x, float y, ObjectID id)
    {
        super(x, y, id);



        this.setHeight(70);
        this.setWidth(50);
    }

    /**
     *  Checks if the character is dead or not
     */
    public boolean isDead()
    {
        if( healthLevel <= 0 || getY() >= 1000)
        {
            lives--;
            return true;
        }
        return false;
    }

    @Override
    public void update()
    {
        super.update();

        this.checkCollision();

        if(isDead())
        {
            if(lives > 0)
            {
                this.setX(60);
                this.setY(20);

                setHealthLevel(100);
            }
            else
            {
                GameObjectHandler.getInstance().removeGameObject(this);
            }
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    protected boolean checkCollision()
    {
        for(int i = 0; i < GameObjectHandler.getInstance().getGame_objects().size(); i++)
        {
            // To keep the game objects in a temp variable - for simplicity
            GameObject tempObject = GameObjectHandler.getInstance().getGame_objects().get(i);

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
                if(this.getBoundsBottom().intersects(tempObject.getBounds()))
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

        for(int i = 0; i < GameObjectHandler.getInstance().getBullets().size(); i++)
        {
            Bullet temp = GameObjectHandler.getInstance().getBullets().get(i);

            if(temp.getBounds().intersects(this.getBounds()))
            {
                if(temp.getWeapon().getOwner().getId() == ObjectID.Enemy)
                {
                    this.healthLevel -= temp.getDamage();

                    GameObjectHandler.getInstance().removeBullet(temp);

                    i--;

                    break;
                }
            }
        }
        return false;
    }

    // ACCESS to different bounds of the character

    @Override
    public Rectangle getBounds() { return new Rectangle((int) x, (int) y, width, height); }

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsTop() { return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2); }

    public Rectangle getBoundsBottom() { return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2); }

    public float getGravity() { return gravity; }

    public void setGravity(float gravity) { this.gravity = gravity; }

    public Weapon getWeapon() { return weapon; }

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public int getLives() { return lives; }

    public void setLives(int lives) { this.lives = lives; }

    public float getHealthLevel() { return healthLevel; }

    public void setHealthLevel(float healthLevel) { this.healthLevel = healthLevel; }
}
