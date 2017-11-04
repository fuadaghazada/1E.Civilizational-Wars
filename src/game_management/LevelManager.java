package game_management;

public class LevelManager {

    private ILevelInterface currentLevel;

    public LevelManager(int level)
    {
        switch (level)
        {
            case 0:
                currentLevel = new ClassicLevel();
                break;
            default:
                System.out.println("Not implemented level");
        }
    }

    public ILevelInterface getCurrentLevel() {
        return currentLevel;
    }



    //TODO: Responsible for creating enemies at their locations


}
