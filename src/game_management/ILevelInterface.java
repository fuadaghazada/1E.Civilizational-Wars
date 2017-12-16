package game_management;

import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.map.TileMap;

import java.awt.*;

public interface ILevelInterface
{

    int TOTAL_LEVEL_COUNT = 3;
    int TOTAL_BONUS_COUNT = 3;

    String getLevelTileMap();
    String getName();
    ObjectID getEnemyType();
    ObjectID getCharacterType();
    ObjectID getBossType();

    int getEnemySize();

    Point [] bossPosition();
    Point [] getCharacterPositions();
    Point [] getEnemyPositions();
    Point [] getBoxPositions();

}
