package game_object.enemy.boss;

import game_object.enemy.Enemy;
import game_object.general.ObjectID;

import java.awt.*;


public class Boss extends Enemy {


    public Boss(double x, double y, ObjectID id)
    {
        super(x, y, id);

        setHealthLevel(1000);
    }

    @Override
    public void render(Graphics g)
    {
        // HealthBar
        g.setColor(Color.GRAY);
        g.drawRect((int) x - 20,(int) y - 10, 5, 10);

        if(this.getHealthLevel() <= 20)
        {
            g.setColor(Color.RED);
        }
        else
        {
            g.setColor(Color.GREEN);
        }
        g.fillRect((int) x - 20,(int) y - 10, (int) this.getHealthLevel()/10, 10);
    }

    @Override
    public void update()
    {
        super.update();
    }


}
