package user_interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PausePanel extends JPanel {

    JButton btnResume, btnSaveGame, btnSettings, btnBackToMenu;

    public PausePanel()
    {
        this.init();
    }

    private void init() {

        setLayout(new GridLayout(5,1, 0,20));
        setBorder(new EmptyBorder(100,150,100,150));

        add(new JLabel("Game Paused", SwingConstants.CENTER));

        btnResume = new JButton("Resume");
        btnSaveGame = new JButton("Save Game");
        btnSettings = new JButton("Settings");
        btnBackToMenu = new JButton("Back to Menu");

        add(btnResume);
        add(btnSaveGame);
        add(btnSettings);
        add(btnBackToMenu);



    }
}
