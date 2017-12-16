package game_object.enemy.boss;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import java.awt.*;

public class BossAttackObject extends GameObject
{
    //Properties
    private ImageLoader imageLoader;

    private int type;

    private float damage;

    /**
     * Constructing the boss attack object with given parameters.
     *
     * @param x  - x coordinate of the game object.
     * @param y  - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public BossAttackObject(double x, double y, ObjectID id, int type)
    {
        super(x, y, id);

        imageLoader = new ImageLoader(id);

        this.width = 40;
        this.height = 40;

        this.type = type;

        damage = 50;

        this.velY = 5;
    }

    @Override
    public void render(Graphics g)
    {
        switch (type)
        {
            case 1:
            {
                g.drawImage(imageLoader.getBoss_attack_object()[0], (int) x, (int) y, width, height, null);
                break;
            }
            case 2:
            {
                g.drawImage(imageLoader.getBoss_attack_object()[1], (int) x, (int) y, width, height, null);
                break;
            }
            case 3:
            {
                g.drawImage(imageLoader.getBoss_attack_object()[2], (int) x, (int) y, width, height, null);
                break;
            }
        }
    }

    @Override
    public void update()
    {
        this.checkCollision();

        if(y <= 1000)
        {
            y += velY;
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
                // Bottom Collision
                if(this.getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    GameObjectHandler.getInstance().removeBossObject(this);
                }
                else
                {
                    setFall(true);
                }
            }
        }

        return false;
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, width, height);
    }

    private Rectangle getBoundsBottom()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }

    // ACCESS
    public float getDamage()
    {
        return damage;
    }
}
