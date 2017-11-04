package game_object.weapon;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import java.awt.*;

/**
 *  This class will represent the Laser gun object as a weapon with its properties.
 *
 */

public class LaserGun extends Weapon
{
    //Textures
    private ImageLoader imageLoader;
    private GameObject owner;
    /**
     * Constructing the game object weapon with given parameters.
     *
     * @param x  - x coordinate of the game object.
     * @param y  - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public LaserGun(float x, float y, ObjectID id, GameObject owner)
    {
        super(x, y, id);

        this.setHeight(20);
        this.setWidth(40);

        this.owner = owner;

        //textures
        imageLoader =  new ImageLoader(ObjectID.Weapon);
    }

    /**
     *  Fires the rifle
     *  @param gameObjectHandler - all game objects
     */
    public void fire(GameObjectHandler gameObjectHandler, int dir)
    {
        gameObjectHandler.addBullet(new Bullet(x + width,
                y + height/2,
                ObjectID.Bullet,
<<<<<<< HEAD
                dir * 15, gameObjectHandler, this));
=======
                gameObjectHandler.getCharacter().getDir() * 15,
                gameObjectHandler, 1));
>>>>>>> 59bdba8043faf655c640db0ee92334e12018a1a7
    }

    @Override
    public void update(GameObjectHandler gameObjectHandler)
    {
        this.setX(gameObjectHandler.getCharacter().getX() + gameObjectHandler.getCharacter().getWidth()/4);
        this.setY(gameObjectHandler.getCharacter().getY() + gameObjectHandler.getCharacter().getHeight()/2 + 8);

        this.clearBulletList(gameObjectHandler);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.drawImage(imageLoader.getWeapons()[2], (int)x , (int)y, null);
    }

    @Override
    protected boolean checkCollision(GameObjectHandler gameObjectHandler)
    {
        return false;
    }

    /**
     *  Clears the bullets when it hits tiles.
     *  @param gameObjectHandler - game objects
     */
    public void clearBulletList(GameObjectHandler gameObjectHandler)
    {
        for(Bullet bullet : gameObjectHandler.getBullets())
        {
            if(bullet.checkCollision(gameObjectHandler))
            {
                gameObjectHandler.removeBullet(bullet);
                break;
            }
        }
    }

    @Override
    public Rectangle getBounds()
    {
        return null;
    }

    @Override
    public GameObject getOwner() {
        return owner;
    }

    public void setOwner(GameObject owner) {
        this.owner = owner;
    }
}
