package game_object.weapon;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

public abstract class Weapon extends GameObject
{
    /**
     * Constructing the game object weapon with given parameters.
     *
     * @param x  - x coordinate of the game object.
     * @param y  - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public Weapon(float x, float y, ObjectID id)
    {
        super(x, y, id);
    }

    public abstract void fire(GameObjectHandler gameObjectHandler, int dir);

    public abstract GameObject getOwner();

}
