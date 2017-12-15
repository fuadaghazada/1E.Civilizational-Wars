package game_object.enemy.boss;

import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import java.awt.*;

/**
 * Created by bmmuradov on 15/12/2017.
 */
public class ModernBoss extends Boss{
    //Properties
    private ImageLoader imageLoader;


    public ModernBoss(double x, double y, ObjectID id)
    {
        super(x, y, id);
        imageLoader= new ImageLoader(ObjectID.ModernBoss);

        setWidth(150);
        setHeight(150);
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g);

        // Boss

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
    public void update (){
        super.update();
    }
}
