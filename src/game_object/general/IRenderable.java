package game_object.general;

import java.awt.*;

public interface IRenderable
{
    void render(Graphics g);
    boolean isToBeRemoved();
    void setToBeRemoved(boolean b);
}
