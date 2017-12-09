package game_object.player;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.LaserGun;
import game_object.weapon.Rifle;
import texture_stuff.Animation;
import texture_stuff.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *  This class represents the third period of character.
 *
 */
public class Robot extends Character
{
    // Properties
    private LaserGun laserGun;

    // textures
    private ImageLoader imageLoader;
    private Animation walkingAnimationR, walkingAnimationL, jumpingAnimationR, jumpingAnimationL;

    /**
     * Constructing the character with given parameters.
     *
     * @param x                 - x coordinate of the character.
     * @param y                 - y coordinate of the character.
     * @param id                - id of the character as a game object.
     */
    public Robot (float x, float y, ObjectID id)
    {
        super(x, y, id);

        this.init();
        this.setWeapon(laserGun);

        // loads the necessary images
        imageLoader = new ImageLoader(ObjectID.Robot);

        // initiating the player animations
        walkingAnimationR = new Animation(5,imageLoader.getPlayer_walkingR());
        walkingAnimationL = new Animation(5,imageLoader.getPlayer_walkingL());
        jumpingAnimationR = new Animation(5,imageLoader.getPlayer_jumpingR());
        jumpingAnimationL = new Animation(5,imageLoader.getPlayer_jumpingL());
    }

    /**
     *  Initializes the properties of the classic fighter
     */
    private void init()
    {
        laserGun = new LaserGun(x, y, ObjectID.Weapon, this);
    }

    @Override
    public void render(Graphics g)
    {
        //rendering the player

        if(dir == 1)
            if(velX != 0 && velY == 0)
                walkingAnimationR.drawAnimation(g, (int) x, (int) y, width, height);
            else if(velY != 0)
                jumpingAnimationR.drawAnimation(g, (int) x, (int) y, width, height);
            else
                g.drawImage(imageLoader.getPlayer_still()[0], (int) x, (int) (y), width, height, null);
        else if(dir == -1)
            if(velX != 0 && velY == 0)
                walkingAnimationL.drawAnimation(g, (int) x, (int) y, width, height);
            else if(velY != 0)
                jumpingAnimationL.drawAnimation(g, (int) x, (int) y, width, height);
            else
                g.drawImage(imageLoader.getPlayer_still()[1], (int) x, (int) (y), width, height, null);

        // rendering the weapon /laserGun
        laserGun.render(g);
    }

    @Override
    public void update()
    {
        super.update();

        // update the position of the rifle
        laserGun.update();

        // running the animations
        walkingAnimationR.runAnimation();
        walkingAnimationL.runAnimation();
        jumpingAnimationR.runAnimation();
        jumpingAnimationL.runAnimation();
    }
}
