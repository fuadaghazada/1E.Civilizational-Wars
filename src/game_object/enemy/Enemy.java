package game_object.enemy;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.player.Character;
import game_object.weapon.Bullet;
import game_object.weapon.LaserGun;
import game_object.weapon.Rifle;
import game_object.weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *  This class will represent the enemies of the game with its given parameters and methods.
 */

public class Enemy extends GameObject
{
    //TODO: it must be overriden in child classes
    private float healthLevel = 100;
    private float damage;
    private GameObjectHandler gameObjectHandler;
    private Weapon weapon;

    private boolean forceJump = false;

    private float[] moveAxisSpeeds = new float[2];
    private long jumpActivationTime;
    private final float FORCE_JUMP_DURATION = 250f;

    //These properties can be used in specific enemy types
    public static final float HORIZONTAL_SPEED = 3;


    private final float followRange  = (float) ((Math.random()* 500 ) + 1000);
    private final float fightRange = (float) ((Math.random()* 100 ) + 300);
    private final float fireRange = fightRange + 50;
    //TODO: add one more range property to fire behind the fight range and follow range

    private long lastFire = System.currentTimeMillis();
    private final long fireRate = 750;



    public Enemy(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler){
        super(x, y, id);
        this.gameObjectHandler = gameObjectHandler;
        weapon = new Rifle(x, y, ObjectID.Weapon, this);
        System.out.println("Fight" + fightRange + ", Follow" + followRange);
        setHeight(70);
        setWidth(60);


    }



    public boolean isDead()
    {
        return healthLevel <= 0;
    }


    public void fight()
    {
        //TODO: Fire at some interval when inside the range
        weapon.fire(gameObjectHandler, getDir());

    }


    @Override
    public void update(GameObjectHandler gameObjectHandler) {

        super.update(gameObjectHandler);
        this.checkCollision(gameObjectHandler);
        weapon.update(gameObjectHandler);
        //TODO: Move through the character
        Character player =  gameObjectHandler.getCharacter();

        if(Math.abs(this.x - player.getX()) < followRange) {
            if (Math.abs(this.x - player.getX()) > fightRange) {
                if (this.x - player.getX() < 0) {
                    this.x += HORIZONTAL_SPEED;
                    setDir(1);
                }
                else {
                    this.x -= HORIZONTAL_SPEED;
                    setDir(-1);
                }
            }
        }

        if(forceJump)
        {
            this.y -= moveAxisSpeeds[1];
        }

        if(forceJump && ((System.currentTimeMillis() - jumpActivationTime)   >= FORCE_JUMP_DURATION))
        {
            forceJump = false;
        }

        if(Math.abs(this.x - player.getX()) < fireRange && (System.currentTimeMillis() - lastFire >= fireRate ) ) {
            fight();
            lastFire = System.currentTimeMillis();

        }


        if(isDead())
        {
            gameObjectHandler.getGame_objects().remove(this);
            System.out.println("ENEMY DIED");
        }


    }

    public void jumpRight()
    {
        jumpActivationTime = System.currentTimeMillis();
        moveAxisSpeeds[0] = HORIZONTAL_SPEED;
        moveAxisSpeeds[1] = 10;
        forceJump = true;
    }

    public void jumpLeft()
    {
        jumpActivationTime = System.currentTimeMillis();
        moveAxisSpeeds[0] = -HORIZONTAL_SPEED;
        moveAxisSpeeds[1] = 10;
        forceJump = true;
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);

        //g.fillRect((int)x, (int)y, width, height);


        g.drawImage(new ImageIcon("src/resources/game_textures/enemy/test_enemy.png").getImage(), (int) x, (int) (y), null);
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
                    setVelY(0);
                }

                // Right collision
                if(this.getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() - getWidth());
                    jumpRight();
                }

                // Left collision
                if(this.getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() + tempObject.getWidth());
                    jumpLeft();
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


        for (int i = 0; i < gameObjectHandler.getBullets().size(); i++)
        {
            Bullet temp = gameObjectHandler.getBullets().get(i);


            if(getBounds().intersects(temp.getBounds()))
            {
                if(temp.getWeapon().getOwner().getId() == ObjectID.Enemy)
                    continue;
                healthLevel -= temp.getDamage();



                System.out.println(temp.getDamage());


                gameObjectHandler.removeBullet(temp);
                i--;
                break;
            }

        }
        return true;

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y , width, height);
    }

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y+5, 5, height - 10);
    }

    public Rectangle getBoundsTop() { return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2); }

    public Rectangle getBoundsBottom() {return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);}

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}