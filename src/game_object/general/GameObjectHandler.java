package game_object.general;

import game_object.enemy.Enemy;
import game_object.general.GameObject;
import game_object.general.ObjectID;
import game_object.player.Character;
import game_object.weapon.Bullet;
import game_object.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;

/**
 *  This class will keep all the game objects in a list so that it will be
 *      able to update and render all together.
 *
 */
public class GameObjectHandler
{
    // Properties
    private ArrayList<GameObject> game_objects = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private GameObject currentObject;
    private Character character;

    public GameObjectHandler() {

        bullets = new ArrayList<Bullet>();
    }

    /**
     *  Adds the given game object to the list.
     *  @param gameObject - given game object.
     */
    public void addGameObject(GameObject gameObject)
    {
        game_objects.add(gameObject);
        if(gameObject.getId() == ObjectID.Character)
            this.character = (Character) gameObject;
    }

    /**
     *  Adds a bullet to the bullet list.
     *  @param bullet
     */
    public void addBullet(Bullet bullet)
    {
        bullets.add(bullet);
    }

    /**
     *  Removes the given game object from the list.
     *  @param gameObject - given game object.
     */
    public void removeGameObject(GameObject gameObject)
    {
        game_objects.remove(gameObject);
    }

    /**
     *  Removes the given bullet object from the list.
     *  @param bullet - given bullet object.
     */
    public void removeBullet(Bullet bullet)
    {
        bullets.remove(bullet);
    }

    /**
     *  Updates the all game objects in the list.
     */
    public void updateAll()
    {
        for(int i = 0; i < game_objects.size(); i++)
        {
            currentObject = game_objects.get(i);

            currentObject.update(this);
        }

        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).update(this);
        }
    }

    /**
     *  Renders the all game objects in the list.
     */
    public void renderAll(Graphics g)
    {
        for(int i = 0; i < game_objects.size(); i++)
        {
            currentObject = game_objects.get(i);

            currentObject.render(g);
        }

        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).render(g);
        }
    }

    //ACCESS & MUTATE
    public ArrayList<GameObject> getGame_objects() { return game_objects; }

    public ArrayList<Enemy> getEnemies()
    {
        for(GameObject g : getGame_objects())
        {
            if(g instanceof Enemy)
            {
                enemies.add((Enemy)g);
            }
        }
        return enemies;
    }

    public ArrayList<Bullet> getBullets() { return bullets; }

    public Character getCharacter()
    {
        return character;
    }

    public int getSize() { return getGame_objects().size(); }
}
