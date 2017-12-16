package game_object.general;

public interface IUpdatable
{
    void update();
    boolean isToBeRemoved();
    void setToBeRemoved(boolean b);
}
