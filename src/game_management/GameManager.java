package game_management;


import game_object.general.Camera;
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

    public static final int BEGINNING = 1;
    public static final int SAVED_GAME = 2;
    public static final int NEXT_LEVEL = 3;

    private boolean isGamePaused = false;

    private InputManager inputManager;

    private Camera camera = null;

    public GameManager(int state)
    {
        GameObjectHandler.getInstance().dispose();
        inputManager = new InputManager(this);

        if (state == BEGINNING)
        {
            LevelManager.getInstance().changeLevel(1);
            this.generateTiles();
            this.generateCharacter(LevelManager.getInstance().getCurrentLevel().getCharacterPositions());
            this.generateEnemies(LevelManager.getInstance().getCurrentLevel().getEnemyPositions());
            this.generateSurpriseBoxes(LevelManager.getInstance().getCurrentLevel().getBoxPositions());
            this.generateBoss(LevelManager.getInstance().getCurrentLevel().bossPosition());
        }
        else if(state == NEXT_LEVEL)
        {
            LevelManager.getInstance().changeLevel(LevelManager.getInstance().getCurrentLevelNo()  + 1);
            this.generateTiles();
            this.generateCharacter(LevelManager.getInstance().getCurrentLevel().getCharacterPositions());
            this.generateEnemies(LevelManager.getInstance().getCurrentLevel().getEnemyPositions());
            this.generateSurpriseBoxes(LevelManager.getInstance().getCurrentLevel().getBoxPositions());
            this.generateBoss(LevelManager.getInstance().getCurrentLevel().bossPosition());
        }
        else if (state == SAVED_GAME)
        {

            if(DataManager.getInstance().isSuccessfulRead()) {
                GameData data = DataManager.getInstance().loadGame();
                System.out.println(data);
                LevelManager.getInstance().changeLevel(data.getLevel());
                this.generateTiles();
                this.generateCharacter(data.getCharacterPositions());
                this.generateEnemies(data.getEnemyPositions());
                this.generateSurpriseBoxes(data.getBoxPositions());
                this.generateBoss(data.getBossPositions());

            }
        }

    }

    /**
     *  Generates the character according to the current level
     */
    public void generateCharacter(Point[] characterPositions)
    {
        GameObjectHandler.getInstance().addGameObject(LevelManager.getInstance().getCurrentLevel().getCharacterType(), characterPositions);
    }

    /**
     *  Generates the enemies according to the current level
     * @param enemyPositions
     */
    public void generateEnemies(Point[] enemyPositions)
    {
        GameObjectHandler.getInstance().addGameObject(LevelManager.getInstance().getCurrentLevel().getEnemyType(), enemyPositions);
    }

    /**
     *  Generates the boss according to the current level
     * @param points
     */
    public void generateBoss(Point[] points)
    {
        GameObjectHandler.getInstance().addGameObject(LevelManager.getInstance().getCurrentLevel().getBossType(), points);
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
     * @param boxPositions
     */
    public void generateSurpriseBoxes(Point[] boxPositions)
    {
        GameObjectHandler.getInstance().addGameObject(ObjectID.SurpriseBox, boxPositions);
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
