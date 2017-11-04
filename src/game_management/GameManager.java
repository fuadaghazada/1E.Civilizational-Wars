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
        LevelManager.currentLevel.gameObjects().updateAll();
    }

    public void render(Graphics g)
    {
        LevelManager.currentLevel.gameObjects().renderAll(g);
    }

    // ACCESS

    public LevelManager getLevelManager()
    {
        return levelManager;
    }
}
