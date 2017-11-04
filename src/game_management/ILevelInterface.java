package game_management;

import game_object.map.TileMap;

public interface ILevelInterface {

    TileMap getLevelTileMap();
    int getEnemyType();
    String getName();
    int getWeaponType();
    int getCharacterType();


}
