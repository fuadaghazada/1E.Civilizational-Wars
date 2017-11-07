package game_object.enemy;

import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;

import javax.swing.*;

import java.awt.*;
public class ClassicSoldier extends Enemy {

    public ClassicSoldier(float x, float y, ObjectID id, GameObjectHandler gameObjectHandler) {
        super(x, y, id, gameObjectHandler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);

        //g.fillRect((int)x, (int)y, width, height);


        g.drawImage(new ImageIcon("src/resources/game_textures/enemy/knight_sword_swing.png").getImage(), (int) x, (int) (y), null);

    }
}
