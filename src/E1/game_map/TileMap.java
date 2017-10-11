package E1.game_map;

import E1.game_main.GameFrame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *  This class create a tile map using tileset textures for the game levels
 *
 *  @authors:
 *      Fuad Aghazada
 *
 *
 *
 *  @version - 1.00
 */

public class TileMap
{
    //Constants

    //Properties
    private float x;
    private float y;

    private int map[][];

    private int mapWidth;
    private int mapHeight;

    private int size;

    /**
     *  Constructs a tile map
     */
    public TileMap (String fileName)
    {
        try
        {
            //for reading the fil which contains the tile types
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            //width & height in string format taken from the text
            String mapWidthString  = bufferedReader.readLine();
            String mapHeightString = bufferedReader.readLine();

            //parsing to integer
            this.mapWidth  = Integer.parseInt(mapWidthString);
            this.mapHeight = Integer.parseInt(mapHeightString);

            //tile size
            size = GameFrame.FRAME_HEIGHT/mapHeight;

            //initializing 2D array for map tiles
            map = new int[mapHeight][mapWidth];

            //filling the array with the data from the map file
            for (int i = 0; i < mapHeight; i ++)
            {
                //splitting the tile data in a line
                String [] tokens = bufferedReader.readLine().split(" ");

                System.out.println(tokens.length);

                //filling process
                for (int j = 0; j < mapWidth; j++)
                {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            exception.printStackTrace();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     *  Updates the tile map
     */
    public void update()
    {

    }

    /**
     *  Draws the tiled map using the data from the file
     */
    public void render(Graphics2D g)
    {
        for(int i = 0; i < mapHeight; i++)
        {
            for(int j = 0; j < mapWidth; j++)
            {
                if(map[i][j] == 1)
                {
                    g.setColor(Color.BLACK);
                }
                else
                {
                    g.setColor(Color.WHITE);
                }
                g.fillRect((int)x+ j * size, (int)y + i * size, size, size);
            }
        }
    }

    //just for testing
    public void printFileData()
    {

        for(int i = 0; i < mapWidth; i++)
        {
            for(int j = 0; j < mapHeight; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ACCESS & MUTATE
    public int getMapTile(int row, int column)
    {
        return map[row][column];
    }

    public int getRowTile(int x)
    {
        return x / size;
    }

    public int getColumnTile(int y)
    {
        return y /size;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getMapWidth()
    {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight()
    {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        this.mapHeight = mapHeight;
    }
}
