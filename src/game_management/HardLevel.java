package game_management;

public class HardLevel implements IDifficultyLevel {
    private float enemyHealth;
    private float characterHealth;
    private float enemySpeed;

    public HardLevel() {
        enemyHealth = 120;
        characterHealth = 100;
        enemySpeed = 4.8f;
    }

    @Override
    public float getEnemyHealth() {
        return enemyHealth;
    }

    @Override
    public float getCharacterHealth() {
        return characterHealth;
    }

    @Override
    public float getEnemySpeed() {
        return enemySpeed;
    }
}
