package texture_stuff;

import game_object.general.ObjectID;

import javax.swing.*;
import java.awt.*;
/**
 *  This class will be used to load the all game images.
 */

public class ImageLoader
{
    // Properties
    private ObjectID id;

    // player
    private Image[] player_walking;
    private Image[] player_jumping;
    private Image player_still;

    //weapon
    private Image [] weapons;

    // tiles
    private Image [] tiles;

    /**
     *  Constructs an image loader which loads all the game images.
     */
    public ImageLoader(ObjectID id)
    {
        this.id  = id;

        if (id.equals(ObjectID.Classic))
        {
            player_still = new ImageIcon("src/resources/game_textures/player/test_player.png").getImage();

            this.loadPlayerWalking();
            this.loadPlayerJumping();
        }
        else if(id.equals(ObjectID.Weapon))
        {
            weapons = new Image[3];

            weapons[0] = new ImageIcon("src/resources/game_textures/weapons/rifle.png").getImage();
            weapons[1] = new ImageIcon("src/resources/game_textures/weapons/rifle.png").getImage();
            weapons[2] = new ImageIcon("src/resources/game_textures/weapons/laserGun.png").getImage();
        }
        else if(id.equals(ObjectID.Tile))
        {
            this.loadTiles();
        }
    }

    /**
     *  Loads the walking animation images
     */
    private void loadPlayerWalking()
    {
        player_walking = new Image[2];

        for(int i = 0; i < player_walking.length; i++)
        {
            player_walking[i] = new ImageIcon("src/resources/game_textures/player/test_player_walk" + (i + 1) + ".png").getImage();
        }
    }

    /**
     *  Loads the jumping animation images
     */
    private void loadPlayerJumping()
    {
        player_jumping = new Image[1];

        for(int i = 0; i < player_jumping.length; i++)
        {
            player_jumping[i] = new ImageIcon("src/resources/game_textures/player/test_player_jump" + (i + 1) + ".png").getImage();
        }
    }

    /**
     *  Loads the tile textures
     */
    private void loadTiles()
    {
        tiles = new Image[2];

        tiles[0] = new ImageIcon("src/resources/game_textures/tiles/brickBrown.png").getImage();
        tiles[1] = new ImageIcon("src/resources/game_textures/tiles/brickGrey.png").getImage();
    }

    //ACCESS

    //player
    public Image[] getPlayer_walking()
    {
        return player_walking;
    }

    public Image[] getPlayer_jumping()
    {
        return player_jumping;
    }

    public Image getPlayer_still()
    {
        return player_still;
    }

    //tiles
    public Image[] getTiles() { return tiles; }

    //weapons
    public Image[] getWeapons () { return weapons; }
}
