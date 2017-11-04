package game_management;

public class LevelManager
{

    // Properties
    private ILevelInterface currentLevel;

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

    // ACCESS

    public ILevelInterface getCurrentLevel() {
        return currentLevel;
    }
}
