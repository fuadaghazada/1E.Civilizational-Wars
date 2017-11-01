package game_object;

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
    private GameObject currentObject;

    /**
     *  Adds the given game object to the list.
     *  @param gameObject - given game object.
     */
    public void addGameObject(GameObject gameObject)
    {
        game_objects.add(gameObject);
    }

    /**
     *  Removes the given game obeject from the list.
     *  @param gameObject - given game object.
     */
    public void removeGameObject(GameObject gameObject)
    {
        game_objects.remove(gameObject);
    }

    /**
     *  Updates the all game objects in the list.
     */
    public void updateAll()
    {
        for(int i = 0; i < game_objects.size(); i++)
        {
            currentObject = game_objects.get(i);

            currentObject.update(game_objects);
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
    }

    //ACCESS & MUTATE
    public ArrayList<GameObject> getGame_objects() { return game_objects; }
}
