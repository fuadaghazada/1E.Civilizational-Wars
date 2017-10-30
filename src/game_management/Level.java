package game_management;

import game_object.map.TileMap;

public class Level implements ILevelInterface {


    @Override
    public TileMap getLevelTile() {
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
}
