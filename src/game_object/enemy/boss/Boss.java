package game_object.enemy.boss;

import game_object.enemy.Enemy;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import game_object.player.Character;

import java.awt.*;
import java.util.ArrayList;


public class Boss extends Enemy {

    //properties
    protected ArrayList<BossAttackObject> attackObjects;

    protected boolean startAttack;


    public Boss(double x, double y, ObjectID id)
    {
        super(x, y, id);
        fightRange = 20;
        fireRange = 40;
        fireRate = 1000;

        setHealthLevel(1000);

        attackObjects = new ArrayList<>();

        startAttack = false;
    }


    @Override
    public void render(Graphics g)
    {
        // HealthBar
        g.setColor(Color.GRAY);
        g.drawRect((int) x - 20,(int) y - 10, 5, 10);

        if(this.getHealthLevel() <= 20)
        {
            g.setColor(Color.RED);
        }
        else
        {
            g.setColor(Color.GREEN);
        }
        g.fillRect((int) x - 20,(int) y - 10, (int) this.getHealthLevel()/10, 10);
    }

    @Override
    public void update()
    {
        this.checkCollision();

        Character player;
        Character player1 =  GameObjectHandler.getInstance().getCharacter(0);
        Character player2 =  GameObjectHandler.getInstance().getCharacter(1);

        if(player1 == null && player2 == null)
            return;
        else if (player2 == null)
        {
            player = player1;
        }
        else if (player1 == null)
        {
            player = player2;
        }
        else{
            //Calculate which one is closer
            if(Math.abs(this.x - player1.getX()) < Math.abs(this.x - player2.getX()))
                player = player1;
            else
                player = player2;

        }

        if(Math.abs(this.x - player.getX()) < followRange) {
            if (Math.abs(this.x - player.getX()) > fightRange) {
                if (this.x - player.getX() < 0) {
                    this.x += speed;
                    setDir(1);
                }
                else {
                    this.x -= speed;
                    setDir(-1);
                }
            }
        }

        if(Math.abs(GameObjectHandler.getInstance().getCharacter(0).getX() - x) <= 100 ||
                (GameObjectHandler.getInstance().getCharacter(1) != null
                        && Math.abs(GameObjectHandler.getInstance().getCharacter(1).getX() - x) <= 100))
        {
            startAttack = true;
        }
        else
        {
            startAttack = false;
        }


        if(startAttack)
        {
            if(System.currentTimeMillis() - lastFire >= fireRate)
            {
                this.addAttackObject();

                lastFire = System.currentTimeMillis();
            }
        }

        // check if the enemy is dead
        if(isDead())
        {
            GameObjectHandler.getInstance().removeGameObject(this);
        }
    }

    public void addAttackObject()
    {
        double lo = x - 200;
        double hi = x + 200;

        double randX = (Math.random() * (hi - lo)) + lo;

        GameObjectHandler.getInstance().addBossObject(new BossAttackObject(randX, 20, ObjectID.BossAttackObject, 1));
    }

}
