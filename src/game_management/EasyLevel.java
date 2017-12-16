package game_management;

public class EasyLevel implements IDifficultyLevel {


    private float enemyHealth;
    private float characterHealth;
    private float enemySpeed;

    public EasyLevel(){
        enemyHealth = 80;
        characterHealth = 100;
        enemySpeed = 3;
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
