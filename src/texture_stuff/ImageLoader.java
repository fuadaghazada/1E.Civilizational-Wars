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
    private Image[] player_walkingR, player_walkingL;
    private Image[] player_jumpingR, player_jumpingL;
    private Image[] player_still;

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
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_l.png").getImage();
        }
        else if(id.equals(ObjectID.Modern))
        {
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_l.png").getImage();
        }
        else if(id.equals(ObjectID.PostModern))
        {
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/robot/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/robot/test_player_l.png").getImage();
        }
        else if(id.equals(ObjectID.Weapon))
        {
            weapons = new Image[6];

            weapons[0] = new ImageIcon("src/resources/game_textures/weapons/rifle.png").getImage();
            weapons[1] = new ImageIcon("src/resources/game_textures/weapons/rifle_l.png").getImage();
            weapons[2] = new ImageIcon("src/resources/game_textures/weapons/rifle.png").getImage();
            weapons[3] = new ImageIcon("src/resources/game_textures/weapons/rifle_l.png").getImage();
            weapons[4] = new ImageIcon("src/resources/game_textures/weapons/laserGun.png").getImage();
            weapons[5] = new ImageIcon("src/resources/game_textures/weapons/laserGun_l.png").getImage();
        }
        else if(id.equals(ObjectID.Tile))
        {
            this.loadTiles();
        }

        this.loadPlayerWalking();
        this.loadPlayerJumping();
    }

    /**
     *  Loads the walking animation images
     */
    private void loadPlayerWalking()
    {
        if(id == ObjectID.Classic)
        {
            player_walkingR = new Image[9];

            for (int i = 0; i < player_walkingR.length; i++) {
                player_walkingR[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_walk" + (i + 1) + ".png").getImage();
            }

            player_walkingL = new Image[9];

            for (int i = 0; i < player_walkingL.length; i++) {
                player_walkingL[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.Modern)
        {
            player_walkingR = new Image[2];

            for (int i = 0; i < player_walkingR.length; i++) {
                player_walkingR[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_walk" + (i + 1) + ".png").getImage();
            }

            player_walkingL = new Image[2];

            for (int i = 0; i < player_walkingL.length; i++) {
                player_walkingL[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.PostModern)
        {
            player_walkingR = new Image[8];

            for (int i = 0; i < player_walkingR.length; i++) {
                player_walkingR[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_walk" + (i + 1) + ".png").getImage();
            }

            player_walkingL = new Image[8];

            for (int i = 0; i < player_walkingL.length; i++) {
                player_walkingL[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
    }

    /**
     *  Loads the jumping animation images
     */
    private void loadPlayerJumping()
    {
        if(id == ObjectID.Classic)
        {
            player_jumpingR = new Image[1];

            for (int i = 0; i < player_jumpingR.length; i++) {
                player_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_jump" + (i + 1) + ".png").getImage();
            }

            player_jumpingL = new Image[1];

            for (int i = 0; i < player_jumpingL.length; i++) {
                player_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.Modern)
        {
            player_jumpingR = new Image[1];

            for (int i = 0; i < player_jumpingR.length; i++) {
                player_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_jump" + (i + 1) + ".png").getImage();
            }

            player_jumpingL = new Image[1];

            for (int i = 0; i < player_jumpingL.length; i++) {
                player_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.PostModern)
        {
            player_jumpingR = new Image[9];

            for (int i = 0; i < player_jumpingR.length; i++) {
                player_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_jump" + (i + 1) + ".png").getImage();
            }

            player_jumpingL = new Image[9];

            for (int i = 0; i < player_jumpingL.length; i++) {
                player_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
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
    public Image[] getPlayer_walkingR()
    {
        return player_walkingR;
    }

    public Image[] getPlayer_jumpingR()
    {
        return player_jumpingR;
    }

    public Image[] getPlayer_walkingL()
    {
        return player_walkingL;
    }

    public Image[] getPlayer_jumpingL() { return player_jumpingL; }

    public Image[] getPlayer_still()
    {
        return player_still;
    }

    //tiles
    public Image[] getTiles() { return tiles; }

    //weapons
    public Image[] getWeapons () { return weapons; }
}
