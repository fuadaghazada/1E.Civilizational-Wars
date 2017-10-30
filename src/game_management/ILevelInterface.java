package game_management;

import game_object.map.TileMap;

public interface ILevelInterface {

    TileMap getLevelTile();
    int getEnemyType();
    String getName();


}
