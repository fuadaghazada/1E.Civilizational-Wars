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

import java.awt.*;

public class ModernLevel implements ILevelInterface
{
    //constants
    public final int ENEMY_NUM = 7;

    // Properties
    private String name;
    private String tileMap;
    private ObjectID enemyType;
    private ObjectID characterType;

    // character positions
    private Point [] characterPositions;

    // enemy positions
    private Point [] enemyPositions;

    // surprise box positions
    private Point [] boxPositions;

    /**
     *  Constructs modern the level
     */
    public ModernLevel()
    {
        name = "Modern Period";

        tileMap = "src/resources/map_files/map_level_2.txt";

        enemyType = ObjectID.ModernSoldier;
        characterType = ObjectID.ModernFighter;

        characterPositions = new Point[2];
        enemyPositions = new Point[ENEMY_NUM];
        boxPositions = new Point[TOTAL_BONUS_COUNT];

        characterPositions[0] = new Point(50,50);

        // Enemy positions
        for (int i = 0; i < enemyPositions.length; i++)
        {
            int enX = (int) ((Math.random() * 600) + 200);
            int enY = (int) ((Math.random() * 600) + 200);
            enemyPositions[i] = new Point(enX, 50);
        }

        // Surprise boxes
        for (int i = 0; i < boxPositions.length; i++)
        {
            int bX = (int) ((Math.random() * 1000) + 200);

            boxPositions[i] = new Point(bX, 70);
        }
    }


    @Override
    public String getLevelTileMap() {
        return tileMap;
    }

    @Override
    public ObjectID getEnemyType() {
        return enemyType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ObjectID getCharacterType() {
        return characterType;
    }

    @Override
    public Point[] getCharacterPositions(){ return characterPositions; }

    @Override
    public Point [] getEnemyPositions() { return enemyPositions; }

    @Override
    public Point [] getBoxPositions() {  return boxPositions; }

    @Override
    public int getEnemySize() {return ENEMY_NUM; }
}
