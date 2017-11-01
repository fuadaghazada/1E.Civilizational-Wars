package game_object;

import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GameObject{

    // Constants
    public static final int WIDTH = 5;
    public static final int HEIGHT = 2;
    public static final int MAX_SPEED = 10;

    //Properties


    /**
     *  Constructs bullet object.
     */
    public Bullet(float x, float y)
    {
        super(x, y, ObjectID.Bullet);

        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
    }

    @Override
    public void update(ArrayList<GameObject> gameObjects)
    {

    }

    public void update()
    {
        x += 4;
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.GRAY);

        g.fillRect((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
