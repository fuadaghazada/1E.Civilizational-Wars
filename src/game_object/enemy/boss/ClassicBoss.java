package game_object.enemy.boss;

import game_object.enemy.boss.*;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import java.awt.*;

/**
 * Created by bmmuradov on 15/12/2017.
 */
public class ClassicBoss extends Boss {

    //Properties

    private ImageLoader imageLoader;

    /**
     *  Constructs the boss for the classic level
     *  @param x - x coordinate of the classic boss
     *  @param y - y coordinate of the classic boss
     *  @param id - id of the classic boss
     */
    public ClassicBoss(double x, double y, ObjectID id)
    {
        super(x, y, id);

        setWidth(150);
        setHeight(150);

        imageLoader = new ImageLoader(ObjectID.ClassicBoss);
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g);

        // Boss

        if(getDir() == 1)
        {
            g.drawImage(imageLoader.getBoss_still()[0], (int) x, (int) (y), width, height, null);
        }
        else if(getDir() == -1)
        {
            g.drawImage(imageLoader.getBoss_still()[1], (int) x, (int) (y), width, height, null);
        }
    }

    @Override
    public void update()
    {
        super.update();
    }


}
