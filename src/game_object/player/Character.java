package game_object.player;

import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.ObjectID;

import java.awt.*;
import java.util.ArrayList;

/**
 *  This class will represent the Character of the game with its given parameters and methods.
 */
public class Character extends GameObject
{
    // Constants
    private final float MAX_SPEED = 5;

    // Properties
    private float gravity = 0.3f;

    private GameObjectHandler gameObjectHandler;

    /**
     * Constructing the character with given parameters.
     *
     * @param x - x coordinate of the character.
     * @param y - y coordinate of the character.
     * @param id - id of the character as a game object.
     */
    public Character(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler)
    {
        super(x, y, id);

        this.gameObjectHandler = gameObjectHandler;

        this.setHeight(70);
        this.setWidth(30);
    }

    @Override
    public void update(ArrayList<GameObject> gameObjects)
    {
        super.update(gameObjects);

        this.checkCollision(gameObjects);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLUE);

        g.fillRect((int)x, (int)y, width, height);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.YELLOW);

        g2.draw(getBoundsTop());
        g2.draw(getBoundsRight());
        g2.draw(getBoundsLeft());
        g2.draw(getBounds());

    }

    /**
     *  To handle the collision between the character and the other game objects.
     *  @param gameObjects - all game objects.
     */
    private void checkCollision(ArrayList<GameObject> gameObjects)
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            // To keep the game objects in a temp variable - for simplicity
            GameObject tempObject = gameObjectHandler.getGame_objects().get(i);

            //checking collision with the tiles.
            if(tempObject.getId() == ObjectID.Tile)
            {
                // Top collision
                if(this.getBoundsTop().intersects(tempObject.getBounds()))
                {
                    setVelY(0);
                }

                // Right collision
                if(this.getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() - getWidth());
                }

                // Left collision
                if(this.getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    setX(tempObject.getX() + 2 * getWidth() );
                }

                // Bottom Collision
                if(this.getBounds().intersects(tempObject.getBounds()))
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
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }

    // ACCESS to different bounds of the character

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y+5, 5, height - 10);
    }

    public Rectangle getBoundsTop() { return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2); }
}
