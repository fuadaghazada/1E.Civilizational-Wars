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
    private Image[] player_fightingR, player_fightingL;

    // enemy
    private Image[] enemy_walkingR, enemy_walkingL;
    private Image[] enemy_jumpingR, enemy_jumpingL;
    private Image[] enemy_still;
    private Image[] enemy_fightingR, enemy_fightingL;

    // boss
    private Image[] boss_still;

    //weapon
    private Image [] weapons;

    //surprise box
    private Image[] box_images;

    //tiles
    private Image [] tiles;

    //background
    private Image background;

    /**
     *  Constructs an image loader which loads all the game images.
     */
    public ImageLoader(ObjectID id)
    {
        this.id  = id;

        if (id.equals(ObjectID.ClassicFighter)) {
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_l.png").getImage();

        }
        else if(id.equals(ObjectID.ClassicSoldier))
        {
            enemy_still = new Image[2];
            enemy_still[0] = new ImageIcon("src/resources/game_textures/enemy/classic/test_enemy_r.png").getImage();
            enemy_still[1] = new ImageIcon("src/resources/game_textures/enemy/classic/test_enemy_l.png").getImage();
        }
        else if(id.equals(ObjectID.ModernFighter))
        {
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_l.png").getImage();
        }
        else if(id.equals(ObjectID.ModernSoldier))
        {
            enemy_still = new Image[2];
            enemy_still[0] = new ImageIcon("src/resources/game_textures/enemy/modern/test_enemy.png").getImage();
            enemy_still[1] = new ImageIcon("src/resources/game_textures/enemy/modern/test_enemy_l.png").getImage();
        }
        else if(id.equals(ObjectID.Robot))
        {
            player_still = new Image[2];
            player_still[0] = new ImageIcon("src/resources/game_textures/player/robot/test_player.png").getImage();
            player_still[1] = new ImageIcon("src/resources/game_textures/player/robot/test_player_l.png").getImage();
        }
        else if(id.equals(ObjectID.Alien))
        {
            enemy_still = new Image[2];
            enemy_still[0] = new ImageIcon("src/resources/game_textures/enemy/alien/test_enemy.png").getImage();
            enemy_still[1] = new ImageIcon("src/resources/game_textures/enemy/alien/test_enemy_l.png").getImage();
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
        else if(id.equals(ObjectID.ClassicBoss))
        {
            boss_still = new Image[2];

            boss_still[0] = new ImageIcon("src/resources/game_textures/enemy/bosses/classic_boss/classic_boss_r.png").getImage();
            boss_still[1] = new ImageIcon("src/resources/game_textures/enemy/bosses/classic_boss/classic_boss_l.png").getImage();
        }
        else if(id.equals(ObjectID.ModernBoss))
        {
            boss_still = new Image[2];

            boss_still[0] = new ImageIcon("src/resources/game_textures/enemy/bosses/modern_boss/modern_boss_r.png").getImage();
            boss_still[1] = new ImageIcon("src/resources/game_textures/enemy/bosses/modern_boss/modern_boss_l.png").getImage();
        }
        else if(id.equals(ObjectID.PostModernBoss))
        {
            boss_still = new Image[2];

            boss_still[0] = new ImageIcon("src/resources/game_textures/enemy/bosses/post_modern_boss/post_modern_boss_r.png").getImage();
            boss_still[1] = new ImageIcon("src/resources/game_textures/enemy/bosses/post_modern_boss/post_modern_boss_l.png").getImage();
        }

        else if(id.equals(ObjectID.Tile))
        {
            this.loadTiles();
        }
        else if(id.equals(ObjectID.SurpriseBox))
        {
            box_images = new Image[2];

            box_images[0] = new ImageIcon("src/resources/surprise_box/surprisebox_def.png").getImage();
            box_images[1] = new ImageIcon("src/resources/surprise_box/surprisebox_flip.png").getImage();
        }

        this.loadWalking();
        this.loadJumping();
        this.loadFighting();
        this.loadBgImages();
    }

    /**
     *  Loads the walking animation images
     */
    private void loadWalking()
    {
        if(id == ObjectID.ClassicFighter)
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
        else if(id == ObjectID.ClassicSoldier)
        {
            enemy_walkingR = new Image[0];

            for (int i = 0; i < enemy_walkingR.length; i++) {
                enemy_walkingR[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_walk" + (i + 1) + ".png").getImage();
            }

            enemy_walkingL = new Image[0];

            for (int i = 0; i < enemy_walkingL.length; i++) {
                enemy_walkingL[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.ModernFighter)
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
        else if(id == ObjectID.ModernSoldier)
        {
            enemy_walkingR = new Image[0];

            for (int i = 0; i < enemy_walkingR.length; i++) {
                enemy_walkingR[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_walk" + (i + 1) + ".png").getImage();
            }

            enemy_walkingL = new Image[0];

            for (int i = 0; i < enemy_walkingL.length; i++) {
                enemy_walkingL[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.Robot)
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
        else if(id == ObjectID.Alien)
        {
            enemy_walkingR = new Image[0];

            for (int i = 0; i < enemy_walkingR.length; i++) {
                enemy_walkingR[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_walk" + (i + 1) + ".png").getImage();
            }

            enemy_walkingL = new Image[0];

            for (int i = 0; i < enemy_walkingL.length; i++) {
                enemy_walkingL[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_walk" + (i + 1) + "_l.png").getImage();
            }
        }
    }

    /**
     *  Loads the jumping animation images
     */
    private void loadJumping()
    {
        if(id == ObjectID.ClassicFighter)
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
        else if(id == ObjectID.ClassicSoldier)
        {
            enemy_jumpingR = new Image[0];

            for (int i = 0; i < enemy_jumpingR.length; i++) {
                enemy_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_jump" + (i + 1) + ".png").getImage();
            }

            enemy_jumpingL = new Image[0];

            for (int i = 0; i < enemy_jumpingL.length; i++) {
                enemy_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.ModernFighter)
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
        else if(id == ObjectID.ModernSoldier)
        {
            enemy_jumpingR = new Image[0];

            for (int i = 0; i < enemy_jumpingR.length; i++) {
                enemy_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_jump" + (i + 1) + ".png").getImage();
            }

            enemy_jumpingL = new Image[0];

            for (int i = 0; i < enemy_jumpingL.length; i++) {
                enemy_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/modern_soldier/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.Robot)
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
        else if(id == ObjectID.Alien)
        {
            enemy_jumpingR = new Image[0];

            for (int i = 0; i < enemy_jumpingR.length; i++) {
                enemy_jumpingR[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_jump" + (i + 1) + ".png").getImage();
            }

            enemy_jumpingL = new Image[0];

            for (int i = 0; i < enemy_jumpingL.length; i++) {
                enemy_jumpingL[i] = new ImageIcon("src/resources/game_textures/player/robot/test_player_jump" + (i + 1) + "_l.png").getImage();
            }
        }
    }

    /**
     *  Loads background images
     */
    private void loadBgImages()
    {
        if(id == ObjectID.ClassicFighter)
        {
            background = new ImageIcon("src/resources/game_textures/background/bg1.png").getImage();
        }
        else if(id == ObjectID.ModernFighter)
        {
            background = new ImageIcon("src/resources/game_textures/background/bg2.png").getImage();
        }
        else if(id == ObjectID.Robot)
        {
            background = new ImageIcon("src/resources/game_textures/background/bg3.png").getImage();
        }
    }

    /**
     *  Loads fighting textures
     */
    private void loadFighting()
    {
        if(id == ObjectID.ClassicFighter)
        {
            player_fightingR = new Image[9];

            for (int i = 0; i < player_fightingR.length; i++) {
                player_fightingR[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_attack" + (i + 1) + ".png").getImage();
            }

            player_fightingL = new Image[9];

            for (int i = 0; i < player_fightingL.length; i++) {
                player_fightingL[i] = new ImageIcon("src/resources/game_textures/player/classic_soldier/test_player_attack" + (i + 1) + "_l.png").getImage();
            }
        }
        else if(id == ObjectID.ClassicSoldier)
        {
            enemy_fightingR = new Image[2];

            for (int i = 0; i < enemy_fightingR.length; i++) {
                enemy_fightingR[i] = new ImageIcon("src/resources/game_textures/enemy/classic/test_enemy_fight_r_" + (i + 1) + ".png").getImage();
            }

            enemy_fightingL = new Image[2];

            for (int i = 0; i < enemy_fightingL.length; i++) {
                enemy_fightingL[i] = new ImageIcon("src/resources/game_textures/enemy/classic/test_enemy_fight_l_" + (i + 1) + ".png").getImage();
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

    public Image[] getPlayer_fightingR() { return player_fightingR; }

    public Image[] getPlayer_fightingL() { return player_fightingL; }

    //enemy

    public Image[] getEnemy_walkingR() { return enemy_walkingR; }

    public Image[] getEnemy_walkingL() { return enemy_walkingL; }

    public Image[] getEnemy_jumpingR() { return enemy_jumpingR; }

    public Image[] getEnemy_jumpingL() { return enemy_jumpingL; }

    public Image[] getEnemy_still() { return enemy_still; }

    public Image[] getEnemy_fightingR() { return enemy_fightingR; }

    public Image[] getEnemy_fightingL() { return enemy_fightingL; }

    // boss
    public Image [] getBoss_still() { return boss_still; }

    //tiles
    public Image[] getTiles() { return tiles; }

    //weapons
    public Image[] getWeapons () { return weapons; }

    //surprise box
    public Image[] getBoxes() { return box_images; }

    // backgrounds
    public Image getBackground() { return background; }
}
