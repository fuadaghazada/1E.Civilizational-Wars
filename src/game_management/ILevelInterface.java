package game_management;

import game_object.general.GameObjectHandler;
import game_object.map.TileMap;

public interface ILevelInterface {

    TileMap getLevelTileMap();
    String getName();
    int getEnemyType();
    int getWeaponType();
    int getCharacterType();
    int getCurrentEnemy();
}
