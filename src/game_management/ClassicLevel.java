package game_management;

import game_object.map.TileMap;

public class ClassicLevel implements ILevelInterface {


    private String name;
    private TileMap tileMap;
    private int enemyType;
    private int weaponType;
    private int characterType;

    public ClassicLevel() {
        name = "Classic";
        tileMap = new TileMap("src/resources/map_files/map_level_1.txt");
        enemyType = 0;
        weaponType = 0;
        characterType = 0;

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


}
