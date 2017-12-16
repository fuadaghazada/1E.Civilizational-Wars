package game_object.bonus;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.player.Character;
import texture_stuff.Animation;
import texture_stuff.ImageLoader;

import java.awt.*;

public class SurpriseBox extends GameObject
{
    // Constants
    public static final int PRIZES = 2;
    public static final int HEALTH = 1;
    public static final int LIVE   = 0;

    // Properties
    private ImageLoader imageLoader;
    private Animation box_animation;

    private int randomPrize;
    private boolean isCollected;

    private int whoTook = -1;


    /**
     * Constructing the game object with given parameters.
     *
     * @param x  - x coordinate of the game object.
     * @param y  - y coordinate of the game object.
     * @param id - object id defines the type of the objects.
     */
    public SurpriseBox(double x, double y, ObjectID id)
    {
        super(x, y, id);

        this.generatePrize();

        isCollected = false;

        System.out.println(randomPrize);

        this.setHeight(40);
        this.setWidth(40);

        imageLoader = new ImageLoader(id);

        box_animation = new Animation(5, imageLoader.getBoxes());
    }

    /**
     *  Collect the prize according to the choosen random number
     */
    public void obtainPrize(Character character)
    {
        switch (randomPrize)
        {
            case LIVE:
            {
                int lives = character.getLives();

                if (lives < character.MAX_LIVES)
                        character.setLives(lives + 1);

                break;
            }
            case HEALTH:
            {
                character.setHealthLevel(100.0f);
                break;
            }
        }
    }

    /**
     *  Generates random prizes :
     *  1 - health
     *  0 - live
     */
    private int generatePrize()
    {
        randomPrize = (int)(Math.random()*PRIZES);

        return randomPrize;
    }


    @Override
    public void update()
    {
        super.update();

        this.checkCollision();

        if(!isCollected)
            box_animation.runAnimation();
    }

    @Override
    public void render(Graphics g)
    {
        if(!isCollected)
            box_animation.drawAnimation(g, (int) x, (int) y, width, height);
    }

    @Override
    protected boolean checkCollision()
    {
        for(int i = 0; i < GameObjectHandler.getInstance().getGame_objects().size(); i++)
        {
            // To keep the game objects in a temp variable - for simplicity
            GameObject tempObject = GameObjectHandler.getInstance().getGame_objects().get(i);

            //checking collision with the tiles.
            if(tempObject.getId() == ObjectID.Tile)
            {
                // Bottom Collision
                if(this.getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    setVelY(0);
                    setFall(false);
                    setJump(false);
                }
                else
                {
                    setFall(true);
                }
            }
        }

        return false;
    }

    private Rectangle getBoundsBottom()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, width, height);
    }

    // ACCESS and MUTATE

    public void setCollected(boolean collected)
    {
        isCollected = collected;
    }

    public boolean isCollected()
    {
        return isCollected;
    }

    public int getWhoTook() { return whoTook; }

    public void setWhoTook(int whoTook) { this.whoTook = whoTook; }
}
