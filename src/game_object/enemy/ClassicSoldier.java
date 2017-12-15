package game_object.enemy;

import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.weapon.Sword;
import texture_stuff.Animation;
import texture_stuff.ImageLoader;
import java.awt.*;

public class ClassicSoldier extends Enemy
{
    // Properties
    private ImageLoader imageLoader;

    private Animation fightAnimationL, fightAnimationR;
    private Sword sword;

    /**
     *  Constructs the classic enemy for period 1
     * @param x - x coordinate
     *  @param y - y coordinate
     * @param id - id of the object
     */
    public ClassicSoldier(double x, double y, ObjectID id)
    {
        super(x, y, id);

        this.init();

        this.setWeapon(sword);

        // textures
        imageLoader = new ImageLoader(ObjectID.ClassicSoldier);

        fightAnimationL = new Animation(1, imageLoader.getEnemy_fightingL());
        fightAnimationR = new Animation(15, imageLoader.getEnemy_fightingR());
    }

    /**
     *  Initializes the properties
     */
    private void init()
    {
        sword = new Sword(x, y, ObjectID.Weapon, this);
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g);

        if (getDir() == 1)
        {
            if(this.getWeapon().isUsed())
            {
                fightAnimationR.drawAnimation(g, (int) (x), (int) (y), width, height);
            }
            else
            {
                g.drawImage(imageLoader.getEnemy_still()[0], (int) x, (int) (y), width, height, null);
            }
        }
        else if (getDir() == -1)
        {
            if(this.getWeapon().isUsed())
            {
                fightAnimationL.drawAnimation(g, (int) (x), (int) (y), width, height);
            }
            else
            {
                g.drawImage(imageLoader.getEnemy_still()[1], (int) x, (int) (y), width, height, null);
            }
        }
    }

    @Override
    public void update()
    {
        super.update();

        sword.update();

        fightAnimationL.runAnimation();
        fightAnimationR.runAnimation();
    }
}
