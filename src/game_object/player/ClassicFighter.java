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
 *  This class represents the first period of character.
 *
 */
public class ClassicFighter extends Character
{
    // Properties
    private Rifle rifle;

    // textures
    private ImageLoader imageLoader;
    private Animation walkingAnimation, jumpingAnimation;

    /**
     * Constructing the character with given parameters.
     *
     * @param x                 - x coordinate of the character.
     * @param y                 - y coordinate of the character.
     * @param id                - id of the character as a game object.
     * @param gameObjectHandler - all game objects to check the collisions.
     */
    public ClassicFighter(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler)
    {
        super(x, y, id, gameObjectHandler);

        this.init();
        this.setWeapon(rifle);

        // loads the necessary images
        imageLoader = new ImageLoader(ObjectID.Classic);

        // initiating the player animations
        walkingAnimation = new Animation(5,imageLoader.getPlayer_walking());
        jumpingAnimation = new Animation(5,imageLoader.getPlayer_jumping());
    }

    /**
     *  Initializes the properties of the classic fighter
     */
    private void init()
    {
        rifle = new Rifle(x, y, ObjectID.Weapon);
    }

    @Override
    public void render(Graphics g)
    {
        //rendering the player
        if(velX != 0 && velY == 0)
            walkingAnimation.drawAnimation(g, (int) x, (int) y, width, height);
        else if(velY != 0)
            jumpingAnimation.drawAnimation(g, (int) x, (int) y, width, height);
        else
            g.drawImage(imageLoader.getPlayer_still(), (int) x, (int) (y), null);

        // rendering the weapon /rifle
        rifle.render(g);
    }

    @Override
    public void update(GameObjectHandler gameObjectHandler)
    {
        super.update(gameObjectHandler);

        // update the position of the rifle
        rifle.update(gameObjectHandler);

        // running the animations
        walkingAnimation.runAnimation();
        jumpingAnimation.runAnimation();
    }
}
