package game_object;

public class Camera
{
    private float x, y;

    public Camera(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void update(GameObject gameObject)
    {
        //tweaning algo
        x = gameObject.getX() - 350;
        //x++;
    }

    // ACCESS & MUTATE
    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }


}
