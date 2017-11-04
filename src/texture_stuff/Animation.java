package texture_stuff;

import java.awt.*;

/**
 *  Reference : https://www.youtube.com/watch?v=kzgNCEWUqUs&index=13&list=PLWms45O3n--54U-22GDqKMRGlXROOZtMx
 */

public class Animation
{
    // Properties
    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private Image[] images;
    private Image currentImage;

    /**
     *  Constructs an animation with given images
     *  @param speed - speed of the animation
     *  @param images - list of the animation images
     */
    public Animation(int speed, Image[] images)
    {
        this.speed = speed;
        this.images = images;

        frames = images.length;
    }

    /**
     *  Runs the animation images.
     */
    public void runAnimation()
    {
        index++;

        if(index > speed)
        {
            index = 0;
            nextFrame();
        }
    }

    /**
     *  Goes to next frame
     */
    private void nextFrame()
    {
        for(int i = 0; i < frames; i++)
        {
            if(count == i)
            {
                currentImage = images[i];
            }
        }

        count++;

        if(count >  frames)
            count = 0;
    }

    /**
     *  Draws the animation images - current images
     *  @param g - Graphics
     *  @param x - x coordinate
     *  @param y - y coordinate
     */
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)
    {
        g.drawImage(currentImage, x, y, scaleX, scaleY, null);
    }


}
