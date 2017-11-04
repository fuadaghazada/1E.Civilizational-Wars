package game_object.map;

/**
 *  This class create a tile for the map which contain of the tails.
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends GameObject
{
    //Constants

    // Properties
    private float x, y;
    private static int tileWidth, tileHeight;
    private int type;

    //textures
    private ImageLoader imageLoader;

    /**
     *  Constructs a tile with the given parameters.
     *  @param x - x coordinate of the Tile
     *  @param y - y coordinate of the Tile
     */
    public Tile(float x, float y)
    {
        super(x, y, ObjectID.Tile);

        this.x = x;
        this.y = y;

        tileWidth = 700 / 15;
        tileHeight = tileWidth;

        //textures
        imageLoader = new ImageLoader(ObjectID.Tile);
    }

    /**
     *	Updating the tile properties.
     */
    public void update()
    {

    }

    @Override
    public void update(GameObjectHandler gameObjectHandler) {

    }

    /**
     * Rendering the tile in terms of its properties.
     */
    public void render(Graphics g)
    {
        g.drawImage(imageLoader.getTiles()[type - 1], (int) x, (int) (y), null);
    }

    @Override
    protected boolean checkCollision(GameObjectHandler gameObjectHandler) { return false; }


    // ACCESS & MUTATE

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, tileWidth, tileHeight);
    }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public static int getTileSize()
    {
        return tileWidth;
    }

    @Override
    public int getWidth()
    {
        return tileWidth;
    }
}

