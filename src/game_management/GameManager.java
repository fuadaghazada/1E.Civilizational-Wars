package game_management;


import game_object.general.Camera;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import java.awt.*;

public class GameManager
{
    public static final int PLAYING = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    public static final int PAUSED = 3;

    private boolean isGamePaused = false;
    //Properties
    private InputManager inputManager;
    private Camera camera = null;
    public GameManager()
    {
        inputManager = new InputManager(this);

        this.generateCharacter();
        this.generateEnemies();
        this.generateTiles();
    }

    /**
     *  Generates the character according to the current level
     */
    public void generateCharacter()
    {
        GameObjectHandler.getInstance().addGameObject(ObjectID.ClassicFighter, LevelManager.getInstance().getCurrentLevel().getCharacterPositions());
    }

    /**
     *  Generates the enemies according to the current level
     */
    public void generateEnemies()
    {
        GameObjectHandler.getInstance().addGameObject(ObjectID.ModernSoldier, LevelManager.getInstance().getCurrentLevel().getEnemyPositions());
    }

    /**
     *  Generates the tiles according to the current level
     */
    public void generateTiles()
    {
        GameObjectHandler.getInstance().addTile(LevelManager.getInstance().getCurrentLevel().getLevelTileMap());
    }

    /**
     *
     */
    public void update()
    {
        //TODO: update the camera by giving the object to follow
        camera.update(GameObjectHandler.getInstance().getCharacter(0));
        GameObjectHandler.getInstance().updateAll();
    }

    /**
     *
     */
    public void render(Graphics g)
    {
        GameObjectHandler.getInstance().renderAll(g);
    }

    public int getGameState()
    {
        if(isGamePaused)
            return PAUSED;

        if(GameObjectHandler.getInstance().getEnemies().size() == 0)
            return WON;
        else if(GameObjectHandler.getInstance().getCharacter(0) == null && GameObjectHandler.getInstance().getCharacter(1) == null)
            return LOST;
        else
            return PLAYING;
    }

    // Access
    public InputManager getInputManager()
    {
        return inputManager;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setPaused(boolean paused)
    {
        isGamePaused = paused;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }
}
