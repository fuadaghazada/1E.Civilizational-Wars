package game_object.player;

import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import javax.swing.*;
import java.awt.*;

/**
 *  This class represents the first period of character.
 *
 */
public class ClassicFighter extends Character
{

    /**
     * Constructing the character with given parameters.
     *
     * @param x                 - x coordinate of the character.
     * @param y                 - y coordinate of the character.
     * @param id                - id of the character as a game object.
     * @param gameObjectHandler - all game objects to check the collisions.
     */
    public ClassicFighter(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler)
    {
        super(x, y, id, gameObjectHandler);
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(new ImageIcon("src/resources/game_textures/player/test_player.png").getImage(), (int) x, (int) (y), null);
    }
}
