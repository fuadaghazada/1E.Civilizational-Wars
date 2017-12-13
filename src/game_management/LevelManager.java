package game_management;


import java.util.ArrayList;

public class LevelManager
{
    // Constants
    public static final int MAX_LEVEL = 3;

    // Properties
    private ILevelInterface currentLevel;

    public static LevelManager levelManager = new LevelManager();

    private ArrayList<ILevelInterface> levels;

    /**
     * Constructs the level manager
     */
    private LevelManager()
    {
        levels = new ArrayList<>();

        levels.add(new ModernLevel());
        levels.add(new ClassicLevel());
        levels.add(new PostModernLevel());

        currentLevel = levels.get(0);
    }

    /**
     *  To change the level
     */
    public void changeLevel(int level)
    {
        if(level > 0 && level <= MAX_LEVEL)
            this.currentLevel = levels.get(level-1);
    }

    /**
     *  Singleton access to the level manager
     */
    public static LevelManager getInstance()
    {
        return levelManager;
    }

    // ACCESS
    public ILevelInterface getCurrentLevel()
    {
        return currentLevel;
    }

    public int getCurrentLevelNo() { return levels.indexOf(currentLevel) + 1; }
}
