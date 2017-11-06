package game_management;

import game_object.enemy.Enemy;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.map.Tile;
import game_object.map.TileMap;
import game_object.player.ModernFighter;

public class ModernLevel implements ILevelInterface {
    //constants
    public static final int ENEMY_NUM = 30;

    // Properties
    private String name;
    private TileMap tileMap;
    private int enemyType;
    private int weaponType;
    private int characterType;
    private int currentEnemy;
    private InputManager inputManager;

    private GameObjectHandler gameObjectHandler;

    /**
     * Constructs the level
     */
    public ModernLevel() {
        name = "Modern";
        tileMap = new TileMap("src/resources/map_files/map_level_2.txt");
        enemyType = 0;
        weaponType = 0;
        characterType = 0;
        currentEnemy = ENEMY_NUM;
        gameObjectHandler = new GameObjectHandler();

        this.generateTiles();
        this.generateCharacter();
        this.generateEnemies();
        inputManager = new InputManager(gameObjectHandler);
    }

    /**
     * Generates the character
     */
    public void generateCharacter() {
        ModernFighter classicFighter = new ModernFighter(50, 100, ObjectID.Character, gameObjectHandler);

        gameObjectHandler.addGameObject(classicFighter);
    }

    /**
     * Generates the enemies
     */
    public void generateEnemies() {
        int horizontalRange = tileMap.getMapWidth() * Tile.getTileSize();
        int verticalRange = 200;

        for (int i = 0; i < ENEMY_NUM; i++) {
            float randX = (float) ((Math.random() * horizontalRange) + 100);
            float randY = verticalRange;

            Enemy enemy = new Enemy(randX, randY, ObjectID.Enemy, gameObjectHandler);

            gameObjectHandler.addGameObject(enemy);
        }
    }

    /**
     * Generates the tiles
     */
    public void generateTiles() {
        for (Tile tile : tileMap.getTiles()) {
            gameObjectHandler.addGameObject(tile);
        }
    }

    @Override
    public TileMap getLevelTileMap() {
        return null;
    }

    @Override
    public int getEnemyType() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getWeaponType() {
        return 0;
    }

    @Override
    public int getCharacterType() {
        return 0;
    }

    @Override
    public void enemyDied() {
        currentEnemy--;

    }

    @Override
    public int getCurrentEnemy() {
        return currentEnemy;
    }

    @Override
    public void levelFinished(int state) {

        int count = 0;
        for(GameObject o : gameObjectHandler.getGame_objects())
        {
            if(o instanceof Enemy)
                count++;
        }

        if (count <= 0)
        {
            System.out.println("YOU WON");
            LevelManager.currentLevel = new PostModernLevel();
        }

    }

    @Override
    public GameObjectHandler gameObjects() {
        return gameObjectHandler;
    }

    @Override
    public InputManager getInputManager() {
        return inputManager;
    }

}