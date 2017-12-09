package game_object.enemy;

import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;
import java.awt.*;

public class ModernSoldier extends Enemy
{
    // Properties
    private ImageLoader imageLoader;

    /**
     *  Constructs the modern enemy for period 1
     *  @param x - x coordinate
     *  @param y - y coordinate
     *  @param id - id of the object
     */
    public ModernSoldier(float x, float y, ObjectID id)
    {
        super(x, y, id);

        // textures
        imageLoader = new ImageLoader(ObjectID.Modern);
    }

    @Override
    public void render(Graphics g)
    {

        // HealthBar
        g.setColor(Color.GRAY);
        g.drawRect((int) x,(int) y - 10, 5, 10);

        if(this.getHealthLevel() <= 20)
        {
            g.setColor(Color.RED);
        }
        else
        {
            g.setColor(Color.GREEN);
        }
        g.fillRect((int) x,(int) y - 10, (int) this.getHealthLevel()/2,10);

        // Enemy

        if(getDir() == 1)
        {
            g.drawImage(imageLoader.getEnemy_still()[0], (int) x, (int) (y), width, height, null);
        }
        else if(getDir() == -1)
        {
            g.drawImage(imageLoader.getEnemy_still()[1], (int) x, (int) (y), width, height, null);
        }
    }

    @Override
    public void update()
    {
        super.update();
    }
}
