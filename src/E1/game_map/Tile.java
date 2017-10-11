package E1.game_map;

import java.awt.image.BufferedImage;

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

public class Tile
{
    //Constants

    //Properties
    private BufferedImage tileImage;

    /**
     *  Constructs a tile with the given parameter of the image
     *  @param tileImage - given image for the tile
     */
    public Tile(BufferedImage tileImage)
    {
        this.tileImage = tileImage;
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
}
