package game_management;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameData {

    int level;
    private Point[] characterPositions;
    private Point[] enemyPositions;
    private Point[] boxPositions;
    private Point[] bossPositions;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Point[] getCharacterPositions() {
        return characterPositions;
    }

    public void setCharacterPositions(Point[] characterPositions) {
        this.characterPositions = characterPositions;
    }

    public Point[] getEnemyPositions() {
        return enemyPositions;
    }

    public void setEnemyPositions(Point[] enemyPositions) {
        this.enemyPositions = enemyPositions;
    }

    public Point[] getBoxPositions() {
        return boxPositions;
    }

    public void setBoxPositions(Point[] boxPositions) {
        this.boxPositions = boxPositions;
    }

    public Point[] getBossPositions() {
        return bossPositions;
    }

    public void setBossPositions(Point[] bossPositions) {
        this.bossPositions = bossPositions;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "level=" + level +
                ", characterPositions=" + Arrays.toString(characterPositions) +
                ", enemyPositions=" + Arrays.toString(enemyPositions) +
                ", boxPositions=" + Arrays.toString(boxPositions) +
                ", bossPositions=" + Arrays.toString(bossPositions) +
                '}';
    }
}
