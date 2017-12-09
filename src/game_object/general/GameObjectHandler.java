package game_object.general;

import game_object.enemy.ClassicSoldier;
import game_object.enemy.Enemy;
import game_object.general.GameObject;
import game_object.general.ObjectID;
import game_object.map.TileMap;
import game_object.player.Character;
import game_object.weapon.Bullet;
import game_object.weapon.Weapon;
import user_interface.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 *  This class will keep all the game objects in a list so that it will be
 *      able to update and render all together.
 *
 */
public class GameObjectHandler
{

    private static GameObjectHandler instance = new GameObjectHandler();

    // Properties
    private ArrayList<GameObject> game_objects;
    private ArrayList<Bullet> bullets;

    private ArrayList<IUpdatable> updatables;
    private ArrayList<IRenderable> renderables;

    private Character[] character;

    private TileMap tileMap;

    private boolean isMultiPlayer;

    public static GameObjectHandler getInstance()
    {
        return instance;
    }

    private GameObjectHandler() {
        game_objects = new ArrayList<>();
        updatables = new ArrayList<>();
        renderables = new ArrayList<>();
        bullets = new ArrayList<>();
        isMultiPlayer = false;
        character = new Character[2];
    }

    public void addGameObject(ObjectID objectID, int amount, Point [] points)
    {
        GameObject go = null;
        if(objectID == ObjectID.ClassicFighter)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Character(points[i].getX(), points[i].getY(), objectID);
                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                    character[1] = (Character) go;

            }
            //generateCharacter(objectID);
        }
        else if(objectID == ObjectID.ModernFighter)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Character(points[i].getX(), points[i].getY(), objectID);
                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                {
                    character[1] = (Character) go;
                    isMultiPlayer = true;
                }
            }
        }
        else if(objectID == ObjectID.Robot)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Character(points[i].getX(), points[i].getY(), objectID);
                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                    character[1] = (Character) go;
            }
        }
        else if (objectID == ObjectID.Alien)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Enemy(points[i].getX(), points[i].getY(), objectID);
            }
        }
        else if( objectID == ObjectID.ClassicSoldier)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Enemy(points[i].getX(), points[i].getY(), objectID);
            }
        }
        else if( objectID == ObjectID.ModernSoldier)
        {
            for (int i = 0; i < amount; i++)
            {
                go = new Enemy(points[i].getX(), points[i].getY(), objectID);
            }
        }

        //Add the game object ot render and update
        updatables.add(go);
        renderables.add(go);
        game_objects.add(go);

    }

    //To add the existing objects
    public void addGameObject(GameObject go)
    {
        if(!updatables.contains(go))
            updatables.add(go);
        if(!renderables.contains(go))
            renderables.add(go);
        game_objects.add(go);
    }

    public void addTile(String fileName)
    {
        tileMap = new TileMap(fileName);

        renderables.add(tileMap);
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
        for (IUpdatable go : updatables)
            go.update();

    }

    /**
     *  Renders the all game objects in the list.
     */
    public void renderAll(Graphics g)
    {
        for (IRenderable i : renderables)
            i.render(g);
    }

    //ACCESS & MUTATE
    public ArrayList<GameObject> getGame_objects() { return game_objects; }

    public ArrayList<Enemy> getEnemies()
    {
        ArrayList<Enemy> enemies = new ArrayList<>();
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

    public Character getCharacter(int index)
    {
        return character[index];
    }

    public int getSize() { return getGame_objects().size(); }

    public boolean isMultiPlayer() {
        return isMultiPlayer;
    }
}
