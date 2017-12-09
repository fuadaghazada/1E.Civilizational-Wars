package game_management;

import game_object.enemy.ClassicSoldier;
import game_object.enemy.Enemy;
import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.map.Tile;
import game_object.map.TileMap;
import game_object.player.ClassicFighter;
import main.CivilizationalWars;
import user_interface.GamePanel;

public class PostModernLevel implements ILevelInterface
{
    //constants
    public static final int ENEMY_NUM = 7;

    // Properties
    private String name;
    private TileMap tileMap;
    private ObjectID enemyType;
    private ObjectID characterType;

    private int currentEnemy;

    /**
     *  Constructs the post-modern level
     */
    public PostModernLevel()
    {
        name = "Post-Modern Period";
        tileMap = new TileMap("src/resources/map_files/map_level_3.txt");
        enemyType = ObjectID.PostModern;
        characterType = ObjectID.PostModern;
        currentEnemy = ENEMY_NUM;
    }


    @Override
    public int getCurrentEnemy() {
        return currentEnemy;
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
        return name;
    }

    @Override
    public int getWeaponType() {
        return 0;
    }

    @Override
    public int getCharacterType() {
        return 0;
    }

}
