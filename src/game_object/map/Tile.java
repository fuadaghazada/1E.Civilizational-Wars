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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile
{
    //Constants

    //Properties
    private BufferedImage tileImage;

    private float x, y;
    private static int tileWidth, tileHeight;

    /**
     *  Constructs a tile with the given parameter of the image
     *  @param tileImage - given image for the tile
     */
    public Tile(float x, float y, BufferedImage tileImage)
    {
        this.tileImage = tileImage;
    }

    /*
     *	Test constructor
     */
    public Tile(float x, float y)
    {
        this.x = x;
        this.y = y;

        tileWidth = 700 / 15;
        tileHeight = tileWidth;
    }

    /**
     *	Updating the tile properties.
     */
    public void update()
    {

    }

    /**
     * Rendering the tile in terms of its properties.
     */
    public void render(Graphics2D g2)
    {
        g2.fillRect((int) x, (int) y, tileWidth, tileHeight);
    }

    // ACCESS & MUTATE

    public BufferedImage getTileImage()
    {
        return tileImage;
    }

    public void setTileImage(BufferedImage tileImage)
    {
        this.tileImage = tileImage;
    }

    public static int getTileSize()
    {
        return tileWidth;
    }
}

