package game_management;


import java.awt.*;

public class GameManager
{
    // Properties
    private LevelManager levelManager;

    /**
     *  Constrcuts the game manager
     */
    public GameManager()
    {
        levelManager = new LevelManager(0);
    }

    public void update()
    {
        levelManager.getCurrentLevel().gameObjects().updateAll();
    }

    public void render(Graphics g)
    {
        levelManager.getCurrentLevel().gameObjects().renderAll(g);
    }

    // ACCESS

    public LevelManager getLevelManager()
    {
        return levelManager;
    }
}
