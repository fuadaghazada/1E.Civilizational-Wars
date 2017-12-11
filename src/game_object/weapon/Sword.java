package game_object.weapon;

import game_object.enemy.Enemy;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import java.awt.*;

public class Sword extends Weapon
{
    // Properties
    private GameObject owner;

    /**
     * Constructing the game object weapon with given parameters.
     *  @param x  - x coordinate of the game object.
     * @param y  - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public Sword(double x, double y, ObjectID id, GameObject owner)
    {
        super(x, y, id);

        this.owner = owner;
    }

    @Override
    public void fire(int dir)
    {
        if(owner.getId() == ObjectID.ClassicFighter) {
            for (Enemy go : GameObjectHandler.getInstance().getEnemies())
            {
                if (go.getBounds().intersects(owner.getBounds()))
                {
                    System.out.println(go.getHealthLevel());
                    go.setHealthLevel(go.getHealthLevel() - 10);
                }

            }
        }
        else if(owner.getId() == ObjectID.ModernSoldier)
        {
            if (GameObjectHandler.getInstance().getCharacter(0).getBounds().intersects(owner.getBounds()))
            {
                GameObjectHandler.getInstance().getCharacter(0).setY(GameObjectHandler.getInstance().getCharacter(0).getY() - 50);
                GameObjectHandler.getInstance().getCharacter(0).setHealthLevel(GameObjectHandler.getInstance().getCharacter(1).getHealthLevel() - 10);
            }
            else if (GameObjectHandler.getInstance().getCharacter(1).getBounds().intersects(owner.getBounds()))
            {
                GameObjectHandler.getInstance().getCharacter(1).setY(GameObjectHandler.getInstance().getCharacter(1).getY() - 50);
                GameObjectHandler.getInstance().getCharacter(1).setHealthLevel(GameObjectHandler.getInstance().getCharacter(1).getHealthLevel() - 10);
            }
        }
    }

    @Override
    public void render(Graphics g)
    {

    }

    @Override
    protected boolean checkCollision()
    {
        return false;
    }

    @Override
    public Rectangle getBounds()
    {
        return null;
    }

    @Override
    public GameObject getOwner()
    {
        return null;
    }
}
