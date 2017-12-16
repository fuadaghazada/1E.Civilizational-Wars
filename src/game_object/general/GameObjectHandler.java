package game_object.general;

import game_object.bonus.SurpriseBox;
import game_object.enemy.Alien;
import game_object.enemy.ClassicSoldier;
import game_object.enemy.Enemy;
import game_object.enemy.ModernSoldier;
import game_object.enemy.boss.ClassicBoss;
import game_object.enemy.boss.ModernBoss;
import game_object.enemy.boss.PostmodernBoss;
import game_object.map.Tile;
import game_object.map.TileMap;
import game_object.player.Character;
import game_object.player.ClassicFighter;
import game_object.player.ModernFighter;
import game_object.player.Robot;
import game_object.weapon.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
    //private ArrayList<IUpdatable> clearList;

    private Character[] character;

    private TileMap tileMap;

    private boolean isMultiPlayer;

    public static GameObjectHandler getInstance()
    {
        return instance;
    }

    private GameObjectHandler()
    {
        game_objects = new ArrayList<>();
        updatables = new ArrayList<>();
        renderables = new ArrayList<>();
        bullets = new ArrayList<>();
        //clearList = new ArrayList<>();
        isMultiPlayer = false;
        character = new Character[2];
    }

    public void addGameObject(ObjectID objectID, Point [] points)
    {
        GameObject go;

        if(objectID == ObjectID.ClassicFighter)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ClassicFighter(points[i].getX(), points[i].getY(), objectID);

                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                    character[1] = (Character) go;
                else
                    return;

                System.out.println(Arrays.toString(character));
                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
            //generateCharacter(objectID);
        }
        else if(objectID == ObjectID.ModernFighter)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ModernFighter(points[i].getX(), points[i].getY(), objectID);

                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                {
                    character[1] = (Character) go;
                    isMultiPlayer = true;
                }

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
        }
        else if(objectID == ObjectID.Robot)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new Robot(points[i].getX(), points[i].getY(), objectID);

                if(character[0] == null)
                    character[0] = (Character) go;
                else if(character[1] == null)
                    character[1] = (Character) go;

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
        }
        else if( objectID == ObjectID.ClassicSoldier)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ClassicSoldier(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }

        }
        else if( objectID == ObjectID.ModernSoldier)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ModernSoldier(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
        }
        else if (objectID == ObjectID.Alien)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new Alien(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
        }
        // Surprise box
        else if(objectID == ObjectID.SurpriseBox)
        {
            for(int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new SurpriseBox(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }
        }

        //Bosses
        else if( objectID == ObjectID.ClassicBoss)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ClassicBoss(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }

        }
        else if( objectID == ObjectID.ModernBoss)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new ModernBoss(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }

        }
        else if( objectID == ObjectID.PostModernBoss)
        {
            for (int i = 0; i < points.length; i++)
            {
                if(points[i] == null)
                    continue;

                go = new PostmodernBoss(points[i].getX(), points[i].getY(), objectID);

                updatables.add(go);
                renderables.add(go);
                game_objects.add(go);
            }

        }
    }

    //To add the existing objects
    public void addGameObject(GameObject go)
    {
        if(!updatables.contains(go)) {
            updatables.add(go);
        }
        if(!renderables.contains(go)) {
            renderables.add(go);
        }
        if(!game_objects.contains(go)) {
            game_objects.add(go);
        }
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
        gameObject.setToBeRemoved(true);
        //updatables.remove(gameObject);
        //renderables.remove(gameObject);
        game_objects.remove(gameObject);
        //clearList.add(gameObject);
    }


    /**
     *  Removes the given bullet object from the list.
     *  @param bullet - given bullet object.
     */
    public void removeBullet(Bullet bullet)
    {
        bullet.setToBeRemoved(true);
    }

    /**
     *  Updates the all game objects in the list.
     */
    public void updateAll()
    {

        try {
            Iterator itr = updatables.iterator();
            while (itr.hasNext())
            {
                IUpdatable u = (IUpdatable) itr.next();
                u.update();
                if (u.isToBeRemoved())
                    itr.remove();
            }


            Iterator it = bullets.iterator();
            while (it.hasNext()) {
                Bullet b = (Bullet) it.next();
                b.update();
                if (b.isToBeRemoved())
                    it.remove();

            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return;
        }

        //updatables.removeAll(clearList);
        //clearList.clear();
    }

    /**
     *  Renders the all game objects in the list.
     */
    public void renderAll(Graphics g)
    {
        Iterator itr = renderables.iterator();
        while (itr.hasNext())
        {
            IRenderable r = (IRenderable) itr.next();
            r.render(g);
            if(r.isToBeRemoved())
                itr.remove();
        }

        Iterator it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = (Bullet) it.next();
            b.render(g);
            if(b.isToBeRemoved())
                it.remove();
        }
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

    public void setMultiPlayer(boolean multiPlayer) { isMultiPlayer = multiPlayer; }

    public void dispose()
    {
        game_objects = new ArrayList<>();
        bullets = new ArrayList<>();
        updatables = new ArrayList<>();
        renderables = new ArrayList<>();
        //clearList = new ArrayList<>();
        character = new Character[2];
        tileMap = null;
    }
}
