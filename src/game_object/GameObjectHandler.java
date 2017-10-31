package game_object;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectHandler
{
    public ArrayList<GameObject> game_objects = new ArrayList<>();

    private GameObject currentObject;

    public void addGameObject(GameObject gameObject)
    {
        game_objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject)
    {
        game_objects.remove(gameObject);
    }

    public void updateAll()
    {
        for(int i = 0; i < game_objects.size(); i++)
        {
            currentObject = game_objects.get(i);

            currentObject.update(game_objects);
        }
    }

    public void renderAll(Graphics g)
    {
        for(int i = 0; i < game_objects.size(); i++)
        {
            currentObject = game_objects.get(i);

            currentObject.render(g);
        }
    }
}
