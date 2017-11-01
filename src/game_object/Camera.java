package game_object;

/**
 *  This class will be a simple camera so that it will be designed
 *  to follow a chosen gameObject by updating.
 *
 **/
public class Camera
{
    // Properties
    private float x, y;

    /**
     *  Constructs the camera with given coordinates.
     *  @param x - x coordinate of the given object.
     *  @param y - y coordinate of the given object.
     */
    public Camera(float x, float y)
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

    public float getX() { return x; }

    public void setX(float x) { this.x = x; }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }


}
