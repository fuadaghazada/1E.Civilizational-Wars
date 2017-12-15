package game_management;


import game_object.general.Camera;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import java.awt.*;

public class GameManager
{
    //Properties
    public static final int PLAYING = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    public static final int PAUSED = 3;

    private boolean isGamePaused = false;

    private InputManager inputManager;

    private Camera camera = null;

    public GameManager()
    {
        GameObjectHandler.getInstance().dispose();
        inputManager = new InputManager(this);

        this.generateTiles();

        if(!DataManager.getInstance().isLoadCalled())
        {
            this.generateCharacter();
            this.generateEnemies();
            this.generateSurpriseBoxes();
        }
        else
        {
            if(DataManager.getInstance().isSuccessfulRead()) {
                GameObjectHandler.getInstance().dispose();
                DataManager.getInstance().loadGame();
            }
        }

    }

    /**
     *  Generates the character according to the current level
     */
    public void generateCharacter()
    {
        GameObjectHandler.getInstance().addGameObject(LevelManager.getInstance().getCurrentLevel().getCharacterType(), LevelManager.getInstance().getCurrentLevel().getCharacterPositions());
    }

    /**
     *  Generates the enemies according to the current level
     */
    public void generateEnemies()
    {
        GameObjectHandler.getInstance().addGameObject(LevelManager.getInstance().getCurrentLevel().getEnemyType(), LevelManager.getInstance().getCurrentLevel().getEnemyPositions());
    }

    /**
     *  Generates the tiles according to the current level
     */
    public void generateTiles()
    {
        GameObjectHandler.getInstance().addTile(LevelManager.getInstance().getCurrentLevel().getLevelTileMap());
    }

    /**
     *  Generates the boxes according to the current level
     */
    public void generateSurpriseBoxes()
    {
        GameObjectHandler.getInstance().addGameObject(ObjectID.SurpriseBox, LevelManager.getInstance().getCurrentLevel().getBoxPositions());
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
        else if    ((GameObjectHandler.getInstance().getCharacter(0) == null && GameObjectHandler.getInstance().getCharacter(1) == null)
                || ((GameObjectHandler.getInstance().getCharacter(0) != null && GameObjectHandler.getInstance().getCharacter(1) == null) && GameObjectHandler.getInstance().getCharacter(0).getLives() <= 0)
                || ((GameObjectHandler.getInstance().getCharacter(0) == null && GameObjectHandler.getInstance().getCharacter(1) != null) && GameObjectHandler.getInstance().getCharacter(1).getLives() <= 0)
                || ((GameObjectHandler.getInstance().getCharacter(0) != null && GameObjectHandler.getInstance().getCharacter(1) != null) && GameObjectHandler.getInstance().getCharacter(0).getLives() + GameObjectHandler.getInstance().getCharacter(1).getLives() <=0 ))
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

    public boolean hasNextLevel(){
        return LevelManager.getInstance().getCurrentLevelNo() < ILevelInterface.TOTAL_LEVEL_COUNT;
    }
}
