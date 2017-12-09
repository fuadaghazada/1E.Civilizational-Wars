package game_object.general;

import game_object.general.GameObject;

/**
 *  This class will be a simple camera so that it will be designed
 *  to follow a chosen gameObject by updating.
 *
 **/
public class Camera
{
    // Properties
    private double x, y;

    /**
     *  Constructs the camera with given coordinates.
     *  @param x - x coordinate of the given object.
     *  @param y - y coordinate of the given object.
     */
    public Camera(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     *  Updates the position of the camera in terms of the given object
     *  using tweaning algorithm.
     *  @param gameObject - given game object.
     */
    public void update(GameObject gameObject)
    {
        //tweaning algorithm
        x = gameObject.getX() - 350;
    }

    // ACCESS & MUTATE METHODS

    public double getX() { return x; }

    public void setX(double x) { this.x = x; }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }


}
