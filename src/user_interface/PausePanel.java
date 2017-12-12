package user_interface;

import game_management.DataManager;
import game_management.GameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel {

    JButton btnResume, btnSaveGame, btnSettings, btnBackToMenu;
    private GameManager gameManager;
    public PausePanel(GameManager gameManager)
    {
        this.gameManager = gameManager;
        this.init();

    }

    private void init()
    {
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

        btnResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.setPaused(false);
            }
        });

        btnSaveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DataManager.getInstance().writeToFile();

                if(DataManager.getInstance().isSuccessfulWrite())
                    JOptionPane.showMessageDialog(ScreenManager.getInstance().getFrame(), "Game is Saved!");
            }
        });

        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().setCurrentPanel(new SettingsPanel());
            }
        });

        btnBackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().clearHistory();
                ScreenManager.getInstance().setCurrentPanel(new MainMenuPanel());
            }
        });

    }
}
