package game_management;

import game_object.general.GameObjectHandler;
import game_object.map.TileMap;

import java.awt.*;

public interface ILevelInterface {

    String getLevelTileMap();
    String getName();
    int getEnemyType();
    int getCharacterType();

    int getEnemySize();

    Point [] getCharacterPositions();
    Point [] getEnemyPositions();

}
