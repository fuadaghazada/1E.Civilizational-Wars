package game_object.weapon;

import game_object.Bullet;
import game_object.player.Character;

import java.awt.*;
import java.util.ArrayList;


public class Weapon
{
    // Properties
    private float x, y;
    private int width, height;

    private ArrayList<Bullet> bullets;

    private int type;

    /**
     *  Constructs the weapon.
     */
    public Weapon(int  type)
    {
        this.type = type;

        this.width = 60;
        this.height = 10;

        if(type == 0)
            bullets = new ArrayList<>();
    }

    public void fire()
    {

    }

    public void update(Character character)
    {
        this.x = character.getX();
        this.y = character.getY() + character.getHeight()/3;

        for(Bullet bullet : bullets)
        {
            bullet.update();
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, width, height);

        for(Bullet bullet : bullets)
        {
            bullet.render(g);
        }
    }

}
