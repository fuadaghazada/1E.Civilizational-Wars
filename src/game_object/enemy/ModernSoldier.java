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
     * @param x - x coordinate
     *  @param y - y coordinate
     * @param id - id of the object
     */
    public ModernSoldier(double x, double y, ObjectID id)
    {
        super(x, y, id);

        // textures
        imageLoader = new ImageLoader(ObjectID.ModernSoldier);
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g);

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
