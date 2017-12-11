package game_object.enemy;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.player.Character;
import game_object.weapon.Bullet;
import game_object.weapon.Rifle;
import game_object.weapon.Weapon;

import javax.swing.*;
import java.awt.*;

/**
 *  This class will represent the enemies of the game with its given parameters and methods.
 */

public class Enemy extends GameObject
{
    //TODO: it must be overriden in child classes
    private float healthLevel = 100;
    private float damage;

    private Weapon weapon;

    private boolean forceJump = false;

    private float[] moveAxisSpeeds = new float[2];
    private long jumpActivationTime;
    private final float FORCE_JUMP_DURATION = 250f;

    //These properties can be used in specific enemy types
    public static final float HORIZONTAL_SPEED = 4;


    private final float followRange  = (float) ((Math.random()* 800 ) + 100);
    private final float fightRange = 150;// (float) ((Math.random()* 450 ) + 50);
    private final float fireRange = fightRange + 100;
    //TODO: add one more range property to fire behind the fight range and follow range

    private long lastFire = System.currentTimeMillis();
    private final long fireRate = 750;



    public Enemy(double x, double y, ObjectID id){
        super(x, y, id);

        weapon = new Rifle(x, y, ObjectID.Weapon, this);

        setHeight(70);
        setWidth(60);
    }


    public boolean isDead()
    {
        if(healthLevel <= 0){
            return true;
        }
        else if(getY() >= 1000)
        {
            return true;
        }

        return  false;

    }


    public void fight()
    {
        //TODO: Fire at some interval when inside the range
        System.out.println("Entered Fight");
        weapon.fire(getDir());

    }

    @Override
    public void update() {

        super.update();
        this.checkCollision();
        weapon.update();
        //TODO: Move through the character
        Character player;
        Character player1 =  GameObjectHandler.getInstance().getCharacter(0);
        Character player2 =  GameObjectHandler.getInstance().getCharacter(1);

        if(player1 == null && player2 == null)
            return;
        else if (player2 == null)
        {
            player = player1;
        }
        else if (player1 == null)
        {
            player = player2;
        }
        else{
            //Calculate which one is closer
            if(Math.abs(this.x - player1.getX()) < Math.abs(this.x - player2.getX()))
                player = player1;
            else
                player = player2;

        }



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

        // check if the enemy is dead
        if(isDead())
        {
            GameObjectHandler.getInstance().removeGameObject(this);
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


        g.drawImage(new ImageIcon("src/resources/game_textures/enemy/modern/test_enemy.png").getImage(), (int) x, (int) (y), null);
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


        for (int i = 0; i < GameObjectHandler.getInstance().getBullets().size(); i++)
        {
            Bullet temp = GameObjectHandler.getInstance().getBullets().get(i);

            if(getBounds().intersects(temp.getBounds()))
            {
                //Need to override in child classes to check each enemy
                if(temp.getWeapon().getOwner().getId() == ObjectID.ModernSoldier)
                    continue;
                healthLevel -= temp.getDamage();

                GameObjectHandler.getInstance().removeBullet(temp);

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

    public float getHealthLevel() { return healthLevel; }

    public void setHealthLevel(float healthLevel) { this.healthLevel = healthLevel; }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}