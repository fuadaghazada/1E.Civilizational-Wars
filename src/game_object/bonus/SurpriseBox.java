package game_object.bonus;

import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import texture_stuff.ImageLoader;

import java.awt.*;

public class SurpriseBox extends GameObject{

    //properties
    private ImageLoader imageLoader;

    private GameObjectHandler gameObjectHandler;
    //constructors
    public SurpriseBox (float x, float y, ObjectID id, GameObjectHandler gameObjectHandler) {

        super(x, y, id);

        this.gameObjectHandler=gameObjectHandler;

        //graphics&textures&stuff
        //imageLoader=new ImageLoader(ObjectID.SupriseBox);

    }

    //methods
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect((int) x,(int) y , 7, 7);
    }

    @Override
    public void update(GameObjectHandler gameObjectHandler) {

        super.update(gameObjectHandler);
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle((int) x, (int) y , 7, 7);
    }

    @Override
    protected boolean checkCollision(GameObjectHandler gameObjectHandler) {
        for(int i = 0; i < gameObjectHandler.getGame_objects().size(); i++)
        {
            // To keep the game objects in a temp variable - for simplicity
            GameObject tempObject = gameObjectHandler.getGame_objects().get(i);

            //checking collision with the tiles.

            if(tempObject.getId() == ObjectID.Tile) {
                if(this.getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    setFall(false);
                }

            }

            if(tempObject.getId() == ObjectID.Character||tempObject.getId() == ObjectID.Enemy) {
                // Top collision
                if (this.getBoundsTop().intersects(tempObject.getBounds())) {
                    tempObject.setVelY(0);
                }

                // Right collision
                if (this.getBoundsRight().intersects(tempObject.getBounds())) {
                    tempObject.setX(tempObject.getX() - 7);
                }

                // Left collision
                if (this.getBoundsLeft().intersects(tempObject.getBounds())) {
                    setX(tempObject.getX() + tempObject.getWidth());
                }
            }

            else
                {
                    setFall(true);
                }
            }
        return true;

    }

    

    public Rectangle getBoundsLeft()
    {

        return new Rectangle((int) x, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsRight()
    {

        return new Rectangle((int)(x + width - 5), (int) y+5, 5, height-10);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int) y, width/2, height/2);
    }


    public Rectangle getBoundsBottom() {
        return new Rectangle((int) (x + (width/2) - (width/2)/2), (int)( y + (height/2)), width/2, height/2);
    }


}

