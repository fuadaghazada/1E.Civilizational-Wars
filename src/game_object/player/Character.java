package game_object.player;

import game_object.bonus.SurpriseBox;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.Bullet;
import game_object.weapon.Weapon;

import java.awt.*;

/**
 *  This class will represent the Character of the game with its given parameters and methods.
 */
public class Character extends GameObject
{
    // Constants
    public static final float MAX_SPEED = 5;
    public static final int MAX_LIVES = 4;

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
    public Character(double x, double y, ObjectID id)
    {
        super(x, y, id);

        this.setHeight(70);
        this.setWidth(50);
    }

    public void move(int dir)
    {
        if(dir == 0)
            setVelX(0);
        else {
            setDir(dir);
            setVelX(5);
            weapon.setDir(dir);
        }
    }

    public void jump()
    {
        setJump(true);
        setVelY(-10);
    }

    public void fight(boolean onFire)
    {
        if(onFire) {
            weapon.setUsed(true);
            weapon.fire(getDir());
        }
        else
        {
            weapon.setUsed(false);
        }
    }

    public void takeDamage()
    {
        this.setY(y - 50);
        this.setHealthLevel(getHealthLevel() - 10);
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
        //TODO: put this code to gamemanager and handle there
        if(isDead())
        {
            if(lives > 0)
            {
                this.setX(60);
                this.setY(20);

                setHealthLevel(100);
            }
            //else
            //{
            //    GameObjectHandler.getInstance().removeGameObject(this);
            //}
        }
    }

    @Override
    public void render(Graphics g)
    {
        if(GameObjectHandler.getInstance().getCharacter(1) != null)
        {
            if (this.equals(GameObjectHandler.getInstance().getCharacter(0))) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillOval((int) (x + width / 2 - 5), (int) (y - 10), 10, 10);
        }
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
                if(temp.getWeapon().getOwner().getId() == ObjectID.Alien || temp.getWeapon().getOwner().getId() == ObjectID.ModernSoldier)
                {
                    this.healthLevel -= temp.getDamage();

                    GameObjectHandler.getInstance().removeBullet(temp);

                    i--;

                    break;
                }
            }
        }

        for(int i = 0; i < GameObjectHandler.getInstance().getGame_objects().size(); i++)
        {
            if(GameObjectHandler.getInstance().getGame_objects().get(i).getId() == ObjectID.SurpriseBox)
            {
                if(GameObjectHandler.getInstance().getGame_objects().get(i).getBounds().intersects(this.getBounds()))
                {
                    SurpriseBox surpriseBox = (SurpriseBox) GameObjectHandler.getInstance().getGame_objects().get(i);

                    surpriseBox.setCollected(true);

                    surpriseBox.obtainPrize(this);

                    return true;
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
