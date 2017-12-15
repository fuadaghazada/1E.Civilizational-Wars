package game_object.map;

import game_management.LevelManager;
import texture_stuff.ImageLoader;

import java.awt.*;

public class Background
{
    //properties
    private double x, y;
    private int width, height;

    private int map_width, map_height;

    private ImageLoader imageLoader;

    /**
     *  Constructs the background (image) according to the given level
     */
    public Background(int map_width, int map_height)
    {
        this.x = 0;
        this.y = 0;

        this.width = 700;
        this.height = 700;

        this.map_width = map_width;
        this.map_height = map_height;

        imageLoader = new ImageLoader(LevelManager.getInstance().getCurrentLevel().getCharacterType());
    }

    /**
     *  Renders the graphics of the background images
     */
    public void render(Graphics g)
    {
        for(int i = -1; i < map_width / width + 2; i++)
            g.drawImage(imageLoader.getBackground(), i * 700, (int)y, width, height, null);
    }
}
