package game_management;

public class LevelManager
{

    // Properties
    public static ILevelInterface currentLevel = null;

    /**
     * Constructs the level manager
     */
    public LevelManager(int level)
    {
        switch (level)
        {
            case 0:
                currentLevel = new ClassicLevel();
                break;
            case 1:
                currentLevel = new ModernLevel();
                break;

            case 2:
                currentLevel = new PostModernLevel();
                break;
        }
    }

    public void setLevel(ILevelInterface level)
    {
        level = currentLevel;
    }

    // ACCESS
/*
    public ILevelInterface getCurrentLevel() {
        return currentLevel;
    }
    */
}
